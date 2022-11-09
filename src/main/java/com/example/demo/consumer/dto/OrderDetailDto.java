package com.example.demo.consumer.dto;

import com.example.demo.consumer.entity.AorderDetail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailDto {
	
	@Data
	public static class DetailListDto {
		private Integer sGroupNum;
		private Integer sMenuCode;		
		private Integer aCount;
	}
	
	@Data
	@Builder
	public static class SaveDto {
		private Integer aOrderNum;
		private Integer sMenuCode;
		private Integer sGroupNum;
		private Integer aCount;
		
		public AorderDetail toEntity() {
			return AorderDetail.builder().aOrderNum(aOrderNum).sGroupNum(sGroupNum).sMenuCode(sMenuCode).aCount(aCount).build();
		}
	}
	
	@Data
	public static class PriceDto {
		private Integer sMenuCode;
		private Integer sMenuPrice;
		private Integer aCount;
	}
}
