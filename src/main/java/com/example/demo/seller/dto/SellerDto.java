package com.example.demo.seller.dto;

import java.time.LocalDate;

import com.example.demo.seller.entity.Sseller;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerDto {
	
	@Data
	@ToString
	public static class SaveDto {
		private String sId;
		private String sBuisnessNum;
		private String sPassword;
		private String sName;
		private String sBirth;
		private String sPhone;
		private String sEmail;
		
		public Sseller toEntity() {
			return Sseller.builder().sId(sId).sBuisnessNum(sBuisnessNum).sName(sName).sPhone(sPhone).sEmail(sEmail).build();
		}
	}
	
	@Data
	public static class ReadDto {
		private String sId;
		private String sBuisnessNum;
		private String sName;
		private LocalDate sBirth;
		private LocalDate sJoinDay;
		private String sPhone;
		private String sEmail;
		private Integer sStoreNum;
	}
	
	@Data
	public static class LoginDto {
		private String sId;
		private String sPassword;
		private String role;
	}
	
	@Data
	public static class UpdateDto {
		private String sPassword;
		private String sPhone;
		private String sEmail;
		private Integer sStoreNum;
		
		public Sseller toEntity() {
			return Sseller.builder().sPassword(sPassword).sPhone(sPhone).sEmail(sEmail).sStoreNum(sStoreNum).build();
		}
	}
}
