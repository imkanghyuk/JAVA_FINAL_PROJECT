package com.example.demo.consumer.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.ConsumerLevel;
import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.dao.OrderDao;
import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.entity.Consumer;
import com.example.demo.consumer.exception.ConsumerJobFailException;
import com.example.demo.consumer.util.MailUtil;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerDao cDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Autowired
	private MailUtil mailUtil;
	
	private String S3Bucket = "iciasmartframe2/upload/consumer/profile";
	
	
	public Consumer join(ConsumerDto.ConsumerSave dto) {

		Consumer consumer = dto.toEntity();

		String imgName = "default.JPG";

		MultipartFile profileimg = dto.getCProfileImg();

		if (profileimg != null && profileimg.isEmpty() == false) {

			try {
				
				imgName = System.currentTimeMillis() + "_" + profileimg.getOriginalFilename();
				
				long size = profileimg.getSize(); // 파일 크기
				
				ObjectMetadata objectMetaData = new ObjectMetadata();
				objectMetaData.setContentType(profileimg.getContentType());
				objectMetaData.setContentLength(size);
				
				// S3에 업로드
				amazonS3Client.putObject(
					new PutObjectRequest(S3Bucket, imgName, profileimg.getInputStream(), objectMetaData)
						.withCannedAcl(CannedAccessControlList.PublicRead)
				);
				
			}

			catch (IllegalStateException e) {
				e.printStackTrace();
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}

		LocalDate date = LocalDate.parse(dto.getCBirth());

		String encodedPw = passwordEncoder.encode(dto.getCPassword());

		consumer.addInfo(imgName, ConsumerLevel.Bronze, date, encodedPw);

		cDao.cMemberJoin(consumer);

		return consumer;
	}

	public Boolean idCheck(String cId) {
		return cDao.cIdOverlap(cId);
	}

	public Boolean nickNameCheck(String cNickname) {
		return cDao.cNickOverlap(cNickname);
	}

	public Boolean emailCheck(String cEmail) {
		return cDao.cEmailOverlap(cEmail);
	}

	public String findById(String cEmail) {
		String cid =  cDao.cFindById(cEmail).get();
		if(cid == null) {
			return "해당 메일로 가입된 아이디가 없습니다";
		} else {
			mailUtil.sendFindIdMail("admin@icia.com", cEmail, cid);
			return "입력하신로 메일 아이디 발송되었습니다.";
		}
	}

	public ConsumerDto.ConsumerRead updateConsumer(ConsumerDto.ConsumerUpdate dto, String cId) {

		if(dto.getCProfileImg() !=null && dto.getCEmail() == null && dto.getCNickname() == null && dto.getCPassword() == null) {
			
			Consumer cdata = cDao.userdataall(cId).get();
		
			MultipartFile profileimg = dto.getCProfileImg();
			
			String imgName = cdata.getCProfileImg();
		
			if (profileimg != null && profileimg.isEmpty() == false) {
				
				try {
					
					imgName = System.currentTimeMillis() + "_" + profileimg.getOriginalFilename();
					long size = profileimg.getSize(); // 파일 크기
					
					ObjectMetadata objectMetaData = new ObjectMetadata();
					objectMetaData.setContentType(profileimg.getContentType());
					objectMetaData.setContentLength(size);
					
					// S3에 업로드
					amazonS3Client.putObject(
							new PutObjectRequest(S3Bucket, imgName, profileimg.getInputStream(), objectMetaData)
							.withCannedAcl(CannedAccessControlList.PublicRead)
							);
					
				cdata.updateimg(imgName);
				cDao.cMemberUpdate(cdata);
				}
				
				catch (IllegalStateException e) {
					e.printStackTrace();
				}
				
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			return cDao.cMemberInforRead(cId).get();
		}

		if(dto.getCEmail() != null && dto.getCProfileImg() == null && dto.getCNickname() == null && dto.getCPassword() == null) {

			Consumer cdata = cDao.userdataall(cId).get();
			
			cdata.updateemail(dto.getCEmail());
			
			cDao.cMemberUpdate(cdata);
			return cDao.cMemberInforRead(cId).get();
		}
		
		if(dto.getCNickname() != null && dto.getCProfileImg() == null && dto.getCEmail() == null && dto.getCPassword() == null) {

			Consumer cdata = cDao.userdataall(cId).get();
			
			cdata.updatecNickname(dto.getCNickname());
			
			cDao.cMemberUpdate(cdata);
			return cDao.cMemberInforRead(cId).get();
		}
		
		if(dto.getCPassword() != null && dto.getCProfileImg() == null && dto.getCEmail() == null && dto.getCNickname() == null) {

			Consumer cdata = cDao.userdataall(cId).get();
			
			String newpassword = passwordEncoder.encode(dto.getCPassword());
			
			cdata.updatepassword(newpassword);
			
			cDao.cMemberUpdate(cdata);
			return cDao.cMemberInforRead(cId).get();
		}

		return cDao.cMemberInforRead(cId).get();

	}

	public Boolean deleteConsumer(String cId , String cPassword) {
		if(cDao.scCheck(cId).get().getCId().equals(cId) && passwordEncoder.matches(cPassword, cDao.scCheck(cId).get().getCPassword())) { 
			if(orderDao.cOrderStatusCheck(cId)==0) {
					orderDao.deleteUserChange("delete_user_"+cId, cId);
					cDao.cMemberClear(cId);
					return true;
			}
			throw new ConsumerJobFailException("주문중인 내용이 있습니다.");
		}
		return false;
	}

	public Optional<ConsumerDto.ConsumerRead> readConsumer(String id) {
		
		Optional<ConsumerDto.ConsumerRead> dto = cDao.cMemberInforRead(id);
		
		String user = dto.get().getCId();
		
		ConsumerDto.dateDto datedto = cDao.findDate().get();
		
		Integer prevCount = cDao.myordercount(user, datedto.getStartdate(), datedto.getEnddate()).get();
		
		if(prevCount >= 0) {
			cDao.levelupdate(user, ConsumerLevel.Bronze);
		}
		if(prevCount > 5) {
			cDao.levelupdate(user, ConsumerLevel.Gold);
		}
		if(prevCount > 10) {
			cDao.levelupdate(user, ConsumerLevel.Diamond);
		}
		if(prevCount > 15) {
			cDao.levelupdate(user, ConsumerLevel.GrandMaster);
		}
		if(prevCount > 20) {
			cDao.levelupdate(user, ConsumerLevel.Challenger);
		}
		
		return cDao.cMemberInforRead(user);
	}
	
	public String changepw(String cEmail, String cId){
		
		Consumer cdata = cDao.userdataall(cId).get();
		
		if(cdata != null) {
			String newpassword = RandomStringUtils.randomAlphabetic(15);
			String imsipw = passwordEncoder.encode(newpassword);
		
			cdata.updatepassword(imsipw);
			cDao.cMemberUpdate(cdata);
			mailUtil.sendFindPwMail("admin@icia.com", cEmail, newpassword);
			
			return "입력하신 이메일로 임시비밀번호를 발급했습니다.";
		} else {
			return "계정 정보가 조회되지 않습니다.";
		}
		
	}
}
