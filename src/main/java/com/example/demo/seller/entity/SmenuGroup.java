package com.example.demo.seller.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SmenuGroup {
	private Integer sGroupNum;
	private String sGroupName;
	private Integer sStoreNum;
	
	public void updateGroupName(String sGroupName) {
		this.sGroupName = sGroupName;
	}
}
