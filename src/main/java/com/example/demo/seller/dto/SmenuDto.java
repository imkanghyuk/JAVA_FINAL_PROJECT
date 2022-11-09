package com.example.demo.seller.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.seller.entity.Smenu;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmenuDto {

	@Data
	public static class saveMenuDto {
		private Integer sGroupNum;
		private String sMenuName;
		private String sMenuInfo;
		private MultipartFile sMenuImg;
		private Integer sMenuPrice;
	
		public Smenu toentity() {
			return Smenu.builder().sGroupNum(sGroupNum).sMenuName(sMenuName).sMenuInfo(sMenuInfo).sMenuPrice(sMenuPrice).build();
		}
	}
	
	@Data
	public static class updateMenuDto {
		private String sMenuName;
		private String sMenuInfo;
		private MultipartFile sMenuImg;
		private Integer sMenuPrice;
	}
	
	@Data
	public static class readMenuDto {
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}
}
