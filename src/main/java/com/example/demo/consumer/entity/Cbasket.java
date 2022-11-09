package com.example.demo.consumer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Cbasket {
	private Integer cBasketNum;
	
	private Integer sMenuCode;
	
	private Integer sStoreNum;
	private Integer sGroupNum;
	private Integer cMenuCount;
	private String cId;
	
}
