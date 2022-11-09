package com.example.demo.consumer.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.apache.tomcat.jni.Local;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ConsumerLevel;
import com.example.demo.consumer.entity.Cbasket;
import com.example.demo.consumer.entity.Consumer;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerDto {

	@Data
	@ToString
	public static class ConsumerRead {
		private String cId;
		private String cNickname;
		private String cName;
		private LocalDate cBirth;
		private LocalDate cJoinday;
		private String cPhone;
		private String cEmail;
		private String cProfileImg;
		private ConsumerLevel cLevel;
	}
	
	@Data
	@Builder
	public static class ConsumerSave {
		@NotEmpty
		private String cId;
		@NotEmpty
		private String cPassword;
		@NotEmpty
		private String cNickname;
		@NotEmpty
		private String cName;
		@NotEmpty
		private String cBirth;
		@NotEmpty
		private String cPhone;
		@NotEmpty
		private String cEmail;
		private MultipartFile cProfileImg;
		
		public Consumer toEntity() {
			return Consumer.builder().cId(cId).cPassword(cPassword).cNickname(cNickname).cName(cName).cPhone(cPhone).cEmail(cEmail).build();
		}
	}

	@Data
	@Builder
	public static class ConsumerUpdate {
		private String cPassword;
		private String cNickname;
		private String cEmail;
		private MultipartFile cProfileImg;
		
		public Consumer toEntity() {
			return Consumer.builder().cPassword(cPassword).cNickname(cNickname).cEmail(cEmail).build();
		}
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserDto {
		private String cId;
		private String role;
		private String cPassword;
	}
	
	@Data
	public static class dateDto {
		private LocalDate startdate;
		private LocalDate enddate;
	}
	
	@Data
	public static class pwChangeDto {
		private String cId;
		private String cEmail;
	}
}
