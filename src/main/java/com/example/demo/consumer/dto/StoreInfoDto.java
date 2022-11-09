package com.example.demo.consumer.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreInfoDto {

	@Data
	public static class ReadCategoryDto {
		private Integer sCategoryNum;
		private String  sCategoryName;
	}
	
	@Data
	public static class StoreListDto {
		private String sStoreName;
		private Integer sMinDelevery;
		private String sStoreLogo;
		private Integer sStoreNum;
		private Integer sReviewCount;
		private Integer sReviewAvg;
	}
	
	@Data
	public static class StoreInfoDetailDto {
		private String sGroupName;
		private Integer sGroupNum;
	}
	
	@Data
	public static class StoreInfoMenuListDto { 
		private Integer sGroupNum;
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}
	
	@Data
	public static class MenuDetailDto {
		private Integer sStoreNum;
		private Integer sGroupNum;
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}
	
	@Data
	public static class GetReviewDto {
		private String aReview;
		private String aNickname;
		private LocalDate aWriteDate;
		private Integer aScore;
		private String aReviewImg;
	}
	
	@Data
	public static class StoreInfoGet {
		private String sStoreName;
		private String sStoreAddress;
		private String sStoreLogo;
		private Integer sMinDeleVery;
		private String sStoreTime;
		private String sStoreIntro;
		
		private List<InfoImg> sStoreImg;
	}
	
	@Data
	public static class InfoImg {
		private String sInfoImg;
		private Integer sImgNum; 
	}
}
