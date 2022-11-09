package com.example.demo.consumer.dto;

import com.example.demo.consumer.entity.Cbasket;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketDto {

	@Builder
	@Data
	public static class Save {
		private Integer sMenuCode;
		private Integer sGroupNum;
		private Integer sStoreNum;
		private Integer cMenuCount;
		private String cId;
		
		public Cbasket toEntity() {
			return Cbasket.builder().sMenuCode(sMenuCode).sGroupNum(sGroupNum).sStoreNum(sStoreNum).cMenuCount(cMenuCount).cId(cId).build();
		}

		public void adduserId(String cId) {
			this.cId = cId;
		}
		
	}

	@Data
	public static class List {	
		private Integer sMenuCode;			
		private Integer sGroupNum;
		private Integer cMenuCount;
		private String sMenuName;
	}

	@Data
	@ToString
	public static class Read {
		private Integer cBasketNum;	
		private Integer sMenuCode;		
		private Integer sStoreNum;
		private Integer sGroupNum;
		private Integer cMenuCount;
		private String sStoreName;
		private String sGroupName;
		private String sMenuName;
		private Integer sMenuPrice;
		private String sMenuImg;
	}
	
	@Builder
	@Data
	public static class CouontUpdate {
		private Integer sMenuCode;
		private String cId;
		private Boolean countVal;
		
		public void addLoginId(String name) {
			this.cId = name;
		}
	}
}
