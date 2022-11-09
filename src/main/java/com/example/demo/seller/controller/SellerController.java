package com.example.demo.seller.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.seller.dto.SellResponseDto;
import com.example.demo.seller.dto.SellerDto;
import com.example.demo.seller.service.SsellerService;

@RestController
public class SellerController {

	@Autowired
	SsellerService sService;
	
	@PostMapping(value = "/seller/sellerjoin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> save(SellerDto.SaveDto dto) {
		return ResponseEntity.ok(new SellResponseDto("회원 가입 성공", sService.sellerJoin(dto)));
	}
	
	@GetMapping(value = "/seller/idcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> selleridcheck(String sId) {
		if(sService.selleridcheck(sId)==true) {
			return ResponseEntity.ok(new SellResponseDto("아이디 중복 여부", "사용 가능합니다"));
		} else {
		return ResponseEntity.ok(new SellResponseDto("아이디 중복 여부", "중복되는 아이디 입니다"));
		}
	}
	
	@GetMapping(value = "/seller/sellerBuischeck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> sellerBuisCheck(String sBuisnessNum) {
		if(sService.sellerBuischeck(sBuisnessNum)==true) {
			return ResponseEntity.ok(new SellResponseDto("중복되지 않은 사업자 번호입니다", true));
		} else {
		return ResponseEntity.ok(new SellResponseDto("중복되는 사업자 번호입니다", false));
		}
	}
	
	@GetMapping(value = "/sellerinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> sellerinfo(Principal principal) {
		if (principal == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("sStoreNum", 0);
			map.put("sId",  "비로그인");
			return ResponseEntity.ok(new SellResponseDto("사용자 이름", map));
		}
		Integer storenum = sService.sellerRead(principal.getName()).getSStoreNum();
		Map<String, Object> map = new HashMap<>();
		map.put("sStoreNum", storenum);
		map.put("sId", principal.getName());
		return ResponseEntity.ok(new SellResponseDto("사용자 이름", map));
	}
	
	@GetMapping(value = "/seller/seller/inforead", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> sellerinforead(Principal principal) {
		if (principal == null) {
			return ResponseEntity.ok(new SellResponseDto("사용자 이름", "비로그인"));
		}
		return ResponseEntity.ok(new SellResponseDto("사용자 이름", sService.sellerRead(principal.getName())));
	}
	
	@PutMapping(value = "/seller/seller/infoupdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> update(SellerDto.UpdateDto dto ,Principal principal){
		return ResponseEntity.ok(new SellResponseDto("정보 수정 진행", sService.sellerUpdate(dto, principal.getName())));
	}
	
}
