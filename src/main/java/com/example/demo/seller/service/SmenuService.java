package com.example.demo.seller.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.seller.dao.SmenuDao;
import com.example.demo.seller.dto.SmenuDto;
import com.example.demo.seller.dto.SmenuDto.saveMenuDto;
import com.example.demo.seller.entity.Smenu;

@Service
public class SmenuService {

	@Autowired
	private SmenuDao menudao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	private String S3Bucket = "iciasmartframe2/upload/seller/menuimg";
	
	public String addMenu(saveMenuDto dto) {
		
		Smenu smenu = dto.toentity();
		
		String imgName = "default.JPG";

		MultipartFile profileimg = dto.getSMenuImg();
		if (profileimg != null && profileimg.isEmpty() == false) {

			try {
				
				imgName = System.currentTimeMillis() + "_" + profileimg.getOriginalFilename();
				
				long size = profileimg.getSize();
				
				ObjectMetadata objectMetaData = new ObjectMetadata();
				objectMetaData.setContentType(profileimg.getContentType());
				objectMetaData.setContentLength(size);
				
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
		smenu.addimg(imgName);
		menudao.menuAdd(smenu);
		
		return "메뉴 등록 완료";
	}
	
	public String deleteMenu(Integer sMenuCode) {
		
		if(menudao.deleteMenu(sMenuCode) == 1) {
			return "메뉴 삭제 완료";
		}
		
		return "메뉴 삭제 실패";
	}
	
	public String updateMenu(SmenuDto.updateMenuDto dto, Integer sMenuCode) {
		
		if(dto.getSMenuImg() != null && dto.getSMenuInfo() == null && dto.getSMenuName() == null && dto.getSMenuPrice() == null) {
			
			Smenu menu = menudao.readMenu(sMenuCode).get();
			
			String imgName = menu.getSMenuImg();
			
			MultipartFile profileimg = dto.getSMenuImg();

			if (profileimg != null && profileimg.isEmpty() == false) {

				try {
					
					imgName = System.currentTimeMillis() + "_" + profileimg.getOriginalFilename();
					
					long size = profileimg.getSize();
					
					ObjectMetadata objectMetaData = new ObjectMetadata();
					objectMetaData.setContentType(profileimg.getContentType());
					objectMetaData.setContentLength(size);
					
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
			
			menu.addimg(imgName);
			menudao.updateMenu(menu);
			
			return "메뉴 이미지 변경 완료";
		}
		
		if( dto.getSMenuInfo() != null && dto.getSMenuImg() == null && dto.getSMenuName() == null && dto.getSMenuPrice() == null) {
			Smenu menu = menudao.readMenu(sMenuCode).get();
			menu.addmenuinfo(dto.getSMenuInfo());
			menudao.updateMenu(menu);
			return "메뉴 소개 변경 완료";
		}
		
		if( dto.getSMenuName() != null && dto.getSMenuImg() == null && dto.getSMenuInfo() == null && dto.getSMenuPrice() == null) {
			
			Smenu menu = menudao.readMenu(sMenuCode).get();
			menu.addmenuname(dto.getSMenuName());
			menudao.updateMenu(menu);
			return "메뉴 이름 변경 완료";
		}

		if( dto.getSMenuPrice() != null && dto.getSMenuImg() == null && dto.getSMenuInfo() == null && dto.getSMenuName() == null) {
			Smenu menu = menudao.readMenu(sMenuCode).get();
			menu.addmenuprice(dto.getSMenuPrice());
			menudao.updateMenu(menu);
			return "메뉴 가격 변경 완료";
		}		
		
		return "메뉴 내용 수정 실패";
	}
	
	public List<SmenuDto.readMenuDto> groupmenuread(Integer sGroupNum) {
		
		return menudao.groupmenuread(sGroupNum);
	}
}
