package com.example.demo.consumer.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.consumer.entity.Areview;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDto {

	@Data
	public static class SaveDto {
		private String aReview;
		private Integer aScore;
		private MultipartFile aReviewImg;
		private Integer sStoreNum;
		private Integer aOrderNum;	
		
		public Areview toEntity() {
			return Areview.builder().aReview(aReview).aScore(aScore).sStoreNum(sStoreNum).aOrderNum(aOrderNum).build();
		}
	}
	
	@Data
	public static class ListDto {
		private Integer aReviewNum;
		private String aReview;
		private String aNickname;
		private LocalDate aWriteDate;
		private Integer aScore;
		private String aReviewImg;
		private Integer sStoreNum;
		private String aReplyContent;
		private LocalDate aReplyDate;
		
		private List<String> sMenuName;
	}
	
	@Data
	@Builder
	public static class UpdateDto {
		private Integer aReviewNum;
		private String aReview;
		
		public Areview toEntity() {
			return Areview.builder().aReviewNum(aReviewNum).aReview(aReview).build();
		}
	}
	
}
