package com.example.demo.consumer.entity;

import java.time.LocalDate;

import com.example.demo.OrderStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Aorder {
	private Integer aOrderNum;
	private LocalDate aOrderDate;
	private OrderStatus aOrderStatus;
	private String cId;
	private String aDeleveryAddress;
	private String aDetailAddress;
	private Integer sStoreNum; 
	private Integer aTotalPrice;
	
	
	public void addTotalPrice(int totalPrice) {
		this.aTotalPrice = totalPrice;
	}

	public void addinfo(OrderStatus status, Integer aTotalPrice) {
		this.aOrderStatus = status;
		this.aTotalPrice = aTotalPrice;
	}
	
}
