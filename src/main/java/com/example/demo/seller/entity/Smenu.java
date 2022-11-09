package com.example.demo.seller.entity;

import lombok.*;

@Builder
@ToString
@Getter
public class Smenu {
	private Integer sGroupNum;
	private Integer sMenuCode;
	private String sMenuName;
	private String sMenuInfo;
	private String sMenuImg;
	private Integer sMenuPrice;
	
	public void addimg(String imgName) {
		this.sMenuImg = imgName;
	}

	public void addmenuinfo(String sMenuInfo) {
		this.sMenuInfo = sMenuInfo;
	}

	public void addmenuname(String sMenuName) {
		this.sMenuName = sMenuName;
	}

	public void addmenuprice(Integer sMenuPrice) {
		this.sMenuPrice = sMenuPrice;
	}
}
