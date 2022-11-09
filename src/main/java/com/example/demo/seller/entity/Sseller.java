package com.example.demo.seller.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Sseller {
	private String sId;
	private String sBuisnessNum;
	private String sPassword;
	private String sName;
	private LocalDate sBirth;
	private LocalDate sJoinDay;
	private String sPhone;
	private String sEmail;
	private Integer sStoreNum;
	
	public void addinfo(LocalDate date, String encodedPw) {
		this.sBirth = date;
		this.sPassword = encodedPw;
	}

	public void addStoreNum(Integer storenum) {
		this.sStoreNum = storenum;
	}

	public void update(String sEmail, String sPhone) {
		this.sEmail = sEmail;
		this.sPhone = sPhone;
	}
}
