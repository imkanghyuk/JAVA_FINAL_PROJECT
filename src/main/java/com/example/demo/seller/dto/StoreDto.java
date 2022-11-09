package com.example.demo.seller.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.seller.entity.Sstore;
import com.example.demo.seller.entity.StoreReply;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreDto {
	
	@Data
	public static class LocationDto {
		private Integer sLocationCode;
		private String sLocationName;
	}
	
	@Data
	public static class CategoryDto {
		private Integer sCategoryNum;
		private String sCategoryName;
	}
	
	@Data
	public static class StoreSaveDto {
		private String sStoreName;
		private String sStoreAddress;
		private MultipartFile sStoreLogo;
		private Integer sMinDeleVery;
		private String sStoreTime;
		private Integer sCategoryNum;
		private Integer sLocationCode;
		private String sStoreIntro;
		
		public Sstore toEntity() {
			return Sstore.builder().sStoreName(sStoreName).sStoreAddress(sStoreAddress).sMinDeleVery(sMinDeleVery)
					.sStoreTime(sStoreTime).sLocationCode(sLocationCode).sStoreIntro(sStoreIntro).sCategoryNum(sCategoryNum).build();
		}
	}
	
	@Data
	public static class StoreRead {
		private Integer sStoreNum;
		private String sStoreName;
		private String sStoreAddress;
		private String sStoreLogo;
		private Integer sMinDeleVery;
		private String sStoreTime;
		private Integer sStoreStatus;
		private String sId;
		private String sStoreIntro;
	}
	
	@Data
	public static class StoreReview {
		private Integer aReviewNum;
		private String aReview;
		private String aNickname;
		private LocalDate aWriteDate;
		private Integer aScore;
		private String aReviewImg;
		private Integer aOrderNum;
		private Integer sStoreNum;
		
		private List<StoreReplyDto> reply;
	}
	
	@Data
	public static class StoreReplyDto {
		private Integer aReplyNum;
		private Integer aReviewNum;
		private String aReplyContent;
		
		public StoreReply toentity() {
			return StoreReply.builder().aReviewNum(aReviewNum).aReplyContent(aReplyContent).build();
		}
	}
	
	@Data
	public static class addInfoImgDto {
		private Integer sImgNum;
		private Integer sStoreNum;
		private String sInfoImg;
	}
	
	@Data
	public static class htmlInfoImgDto {
		private Integer sStoreNum;
		private List<MultipartFile> imginfo;
	}
	
	@Data
	public static class readIntroImg {
		private String sInfoImg;
		private Integer sImgNum;
	}
}
