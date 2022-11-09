package com.example.demo.consumer.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.consumer.dao.AreviewDao;
import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.dto.ReviewDto;
import com.example.demo.consumer.entity.Areview;

@Service
public class ReviewService {

	@Autowired
	AreviewDao revdao;
	
	@Autowired
	ConsumerDao condao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	private String S3Bucket = "iciasmartframe2/upload/consumer/review";
	
	public Areview cReviewWrtie(ReviewDto.SaveDto dto, String cId) {
		ConsumerDto.ConsumerRead readcon = condao.cMemberInforRead(cId).get();
		
		Areview areview = dto.toEntity();
		
		MultipartFile profileimg = dto.getAReviewImg();
		
		String imgName = "noimg.JPG";
		
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
		areview.addInfo(readcon.getCNickname(), imgName, cId); 
		System.out.println(areview);
		revdao.cReviewWrtie(areview);
		
		return areview; 
		
	}
	
	public List<ReviewDto.ListDto> cReviewRead(String cId) {
		
		return revdao.cReviewRead(cId);
	}
	
	public String delete(Integer aReviewNum, String cId) {
		revdao.cReviewDelete(aReviewNum, cId);
		revdao.deleteReply(aReviewNum);
		
		return "리뷰가 삭제되었습니다";
	}
	
	public Boolean checkreview(Integer aOrderNum, String cId) {
		return revdao.reviewCheck(aOrderNum, cId);
	}
}
