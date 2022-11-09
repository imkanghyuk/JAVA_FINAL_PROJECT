package com.example.demo.consumer.entity;

import java.time.LocalDate;

import com.example.demo.ConsumerLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Consumer {
	private String cId;
	private String cPassword;
	private String cNickname;
	private String cName;
	private LocalDate cBirth;
	private LocalDate cJoinday;
	private String cPhone;
	private String cEmail;
	private Integer cBuyCount;
	private String cProfileImg;
	private ConsumerLevel cLevel;
	
	public void addInfo(String imgName, ConsumerLevel cLevel, LocalDate date, String encodedPw) {
		this.cProfileImg = imgName;
		this.cLevel = cLevel;
		this.cBirth = date;
		this.cPassword = encodedPw;
	}

	public void updateimg(String imgName) {
		this.cProfileImg = imgName;
	}
	
	public void updateemail(String cEmail) {
		this.cEmail = cEmail;
	}
	
	public void updatepassword(String cPassword) {
		this.cPassword = cPassword;
	}
	
	public void updatecNickname(String cNickname) {
		this.cNickname = cNickname;
	}
}
