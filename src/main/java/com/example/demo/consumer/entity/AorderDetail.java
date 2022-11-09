package com.example.demo.consumer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AorderDetail {
	private Integer aDetailCode;
	private Integer aOrderNum;
	private Integer sMenuCode;
	private Integer sGroupNum;
	private Integer aCount;
	private String sMenuName;
	
	public void nameAdd(String sMenuName) {
		this.sMenuName = sMenuName;
	}
}
