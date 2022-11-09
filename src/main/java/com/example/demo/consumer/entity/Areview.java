package com.example.demo.consumer.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Areview {
	private Integer aReviewNum;
	private String aReview;
	private String aNickname;
	private LocalDate aWriteDate;
	private Integer aScore;
	private String aReviewImg;
	private String cId;
	private Integer aOrderNum;
	private Integer sStoreNum;
	
	
	public void addInfo(String aNickname, String aReviewImg, String cId) {
		this.aNickname = aNickname;
		this.aReviewImg = aReviewImg;
		this.cId = cId;
	}
	
}
