package com.example.demo.seller.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.seller.dto.SellResponseDto;
import com.example.demo.seller.dto.StoreDto;
import com.example.demo.seller.dto.StoreDto.StoreSaveDto;
import com.example.demo.seller.service.SstoreService;

@RestController
public class StoreController {

	@Autowired
	SstoreService storeservice;
	
	@PostMapping(value = "/seller/storeadd", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SellResponseDto> storeadd(StoreSaveDto dto, Principal principal) {
		storeservice.storeAdd(dto, principal.getName());
		
		return ResponseEntity.ok(new SellResponseDto("가게 생성 완료", storeservice.storeRead(principal.getName())));
	}
	
	@GetMapping(value = "/seller/location", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> readLcation() {
		return ResponseEntity.ok(new SellResponseDto("지역 정보", storeservice.locationinfo()));
	}

	@GetMapping(value = "/seller/category", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> readCategory() {
		return ResponseEntity.ok(new SellResponseDto("카테고리 정보", storeservice.categoryinfo()));
	}
	
	@GetMapping(value = "/seller/storeread", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> readstore(Principal principal) {
		return ResponseEntity.ok(new SellResponseDto("가게 정보", storeservice.storeRead(principal.getName())));
	}
	
	@PutMapping(value = "/seller/changestatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> statusChange(Integer sStoreNum, Integer sStoreStatus, Principal principal) {
		
		return ResponseEntity.ok(new SellResponseDto("가게 상태 변경정보", storeservice.chagestatus(sStoreNum, sStoreStatus, principal.getName())));
	}
	
	@DeleteMapping(value = "/seller/storedelete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> storedelete(Integer sStoreNum, Principal principal) {
		
		return ResponseEntity.ok(new SellResponseDto("가게 삭제", storeservice.storedelete(sStoreNum, principal.getName())));
	}
	
	@PostMapping(value = "/seller/store/addimg", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SellResponseDto> storeinfoimgadd(StoreDto.htmlInfoImgDto dto) {
		
		return ResponseEntity.ok(new SellResponseDto("가게 생성 완료", storeservice.addImgInfo(dto)));
	}
	
	@GetMapping(value = "/seller/store/readintroimg", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> readIntroImg(Integer sStoreNum){
		return ResponseEntity.ok(new SellResponseDto("가게 이미지 출력", storeservice.readIntroImg(sStoreNum)));
	}
	
	@DeleteMapping(value = "/seller/store/deleteintroimg", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> deleteIntroImg(Integer sStoreNum){
		return ResponseEntity.ok(new SellResponseDto("처리 결과", storeservice.deleteIntroImg(sStoreNum)));
	}
}
