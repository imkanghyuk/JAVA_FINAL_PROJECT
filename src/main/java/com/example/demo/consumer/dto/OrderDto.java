package com.example.demo.consumer.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.OrderStatus;
import com.example.demo.consumer.entity.Aorder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {
	
	@Data
	@Builder
	public static class OrderSave {
		private String cId;
		private String aDeleveryAddress;
		private String aDetailAddress; 
		private Integer sStoreNum;
		
		public void addCID(String cId) {
			this.cId = cId;
		}
		
		public Aorder toentity() {
			return Aorder.builder().cId(cId).aDeleveryAddress(aDeleveryAddress).aDetailAddress(aDetailAddress).sStoreNum(sStoreNum).build();
		}
	}
	
	@Data
	@Builder
	public static class OrderReadDto {
		private Integer aOrderNum;
		private String cId;
		private OrderStatus aOrderStatus;
	}
	
	@Data
	public static class ListDto {
		private Integer aOrderNum;
		private LocalDate aOrderDate;
		private OrderStatus aOrderStatus;
		private String aDeleveryAddress;
		private String aDetailAddress;
		private Integer aTotalPrice;
		private Integer sStoreNum;
		private List<ReadDetailDto> readDetail;
	}
	
	@Data
	public static class ListAllDto {
		
		private Integer aOrderNum;
		private LocalDate aOrderDate;
		private OrderStatus aOrderStatus;

		private String aDeleveryAddress;
		private String aDetailAddress;
		private Integer aTotalPrice;
		private Integer sStoreNum;
		
		private List<OrderDetailListDto> detailList;
		
	}
	
	@Data
	public static class ReadDetailDto{
		private Integer aDetailCode;
		private String sMenuName;
		private Integer aCount;
	}
	
	@Data
	public static class OrderDetailListDto{
		private Integer aDetailCode;
		private Integer sGroupNum;
		private Integer sMenuCode;		
		private Integer aCount;
		private String sGroupName;
		private String sMenuName;
	}
}
