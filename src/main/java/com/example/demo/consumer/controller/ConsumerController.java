package com.example.demo.consumer.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.exception.ConsumerJobFailException;
import com.example.demo.consumer.service.ConsumerService;

@Validated
@RestController
public class ConsumerController {

	@Autowired
	ConsumerService cService;
	
	@PostMapping(value="/consumer/join", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> join(@Valid ConsumerDto.ConsumerSave dto, BindingResult bindingResult) {
		System.out.println(dto.getCProfileImg());
		if(cService.idCheck(dto.getCId()) || cService.emailCheck(dto.getCEmail()) || cService.nickNameCheck(dto.getCNickname())) {
			return ResponseEntity.ok(new ResponseDto("회원 가입 오류", null));
		}
		return ResponseEntity.ok(new ResponseDto("회원 가입 완료", cService.join(dto)));
	}
	
	@GetMapping(value = "/consumer/idcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> idCheck(String cId) {
		
		if(cService.idCheck(cId)) {
			return ResponseEntity.ok(new ResponseDto("사용중인 아이디 입니다", false));
		}
		
		return ResponseEntity.ok(new ResponseDto("사용 가능합니다", true));
	}
	
	@GetMapping(value = "/consumer/emailcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> emailCheck(String cEmail) {
		if(cService.emailCheck(cEmail)) {
			return ResponseEntity.ok(new ResponseDto("사용중인 이메일 입니다", false));
		}
		return ResponseEntity.ok(new ResponseDto("사용 가능합니다", true));
	}
	
	@GetMapping(value = "/consumer/nicknamecheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> nicknameCheck(String cNickname) {
		if(cService.nickNameCheck(cNickname)) {
			return ResponseEntity.ok(new ResponseDto("사용중인 닉네임 입니다", false));
		}
		return ResponseEntity.ok(new ResponseDto("사용 가능합니다", true));
	}
	
	@GetMapping(value = "/consumer/readconsumer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> readConsumer(Principal principal) {
		if(principal.getName() == null) {
			throw new ConsumerJobFailException("비로그인");
		}
		return ResponseEntity.ok(new ResponseDto("계정 정보", cService.readConsumer(principal.getName()).get()));
	}
	
	@PutMapping(value = "/consumer/update")
	public ResponseEntity<ResponseDto> updateConsumer(ConsumerDto.ConsumerUpdate dto, Principal principal) {
		
		return ResponseEntity.ok(new ResponseDto("계정 정보 수정", cService.updateConsumer(dto, principal.getName())));
	}
	
	@DeleteMapping(value = "/consumer/delete")
	public ResponseEntity<ResponseDto> deleteConsumer(String cId, String cPassword) {
		SecurityContextHolder.clearContext();
		if(cService.deleteConsumer(cId, cPassword)) {
			return ResponseEntity.ok(new ResponseDto("계정 정보 삭제", "그동안 감사했습니다"));
		}
		throw new ConsumerJobFailException("아이디와 비밀번호를 확인해주세요");
	}

	@GetMapping(value = "/consumer/findbyid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> findbyid(String cEmail) {
		
		return ResponseEntity.ok(new ResponseDto("아이디 결과 조회", cService.findById(cEmail)));
	}

	@PutMapping(value = "/consumer/changenewpw")
	public ResponseEntity<ResponseDto> newpassword(ConsumerDto.pwChangeDto dto) {
		
		return ResponseEntity.ok(new ResponseDto("임시 비밀번호 발급", cService.changepw(dto.getCEmail(), dto.getCId())));
	}
	
	@GetMapping(value = "/consumer/getloginuserinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> userlogininfo(Principal principal) {
		String id = "비회원";
		if(principal != null) {
			id = principal.getName();
		}
		return ResponseEntity.ok(new ResponseDto("로그인 계정",id));
	}
}
