package com.example.demo.seller.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Sstore {
	private Integer sStoreNum;
	private String sStoreName;
	private String sStoreAddress;
	private String sStoreLogo;
	private Integer sMinDeleVery;
	private String sStoreTime;
	private String sStoreIntro;
	private Integer sStoreStatus;
	private String sStoreReview;
	private Integer sCategoryNum;
	private Integer sLocationCode;
	private String sId;
	
	public void addinfo(String imgName, String sId) {
		this.sStoreLogo = imgName;
		this.sId = sId;
	}	

}
