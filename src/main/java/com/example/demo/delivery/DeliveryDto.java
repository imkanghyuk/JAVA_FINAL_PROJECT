package com.example.demo.delivery;

import java.time.LocalDate;

import com.example.demo.OrderStatus;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryDto {
	
	@Data
	@ToString
	public static class DelireadDto {
		private String sStoreName;
		private String sStoreAddress;
		
		private Integer aOrderNum;
		private LocalDate aOrderDate;
		private String aOrderStatus;
		private String cId;
		private String aDeleveryAddress;
		private String aDetailAddress;
		private Integer sStoreNum; 
		private Integer aTotalPrice;
	}
	
}
