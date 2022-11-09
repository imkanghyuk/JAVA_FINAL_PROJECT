package com.example.demo.seller.dto;

import com.example.demo.seller.entity.SmenuGroup;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SgroupDto {
	
	@Data
	public static class SaveDto {
		private String sGroupName;
		private Integer sStoreNum;
		
		public SmenuGroup toentity() {
			return SmenuGroup.builder().sGroupName(sGroupName).sStoreNum(sStoreNum).build();
		}
	}
	
	
}
