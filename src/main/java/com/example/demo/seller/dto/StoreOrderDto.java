package com.example.demo.seller.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.OrderStatus;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreOrderDto {

	@Data
	public static class SellerListDto {
		private Integer aOrderNum;
		private LocalDate aOrderDate;
		private OrderStatus aOrderStatus;
		private String aDeleveryAddress;
		private String aDetailAddress;
		private Integer aTotalPrice;
		private Integer sStoreNum;
		private List<SellerReadDetailDto> readDetail;
	}
	
	@Data
	public static class SellerReadDetailDto{
		private Integer aDetailCode;
		private String sMenuName;
		private Integer aCount;
	}
}
