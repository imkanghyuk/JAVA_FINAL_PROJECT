package com.example.demo.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.seller.dto.SellResponseDto;
import com.example.demo.seller.service.SstoreOrderSerivce;

@RestController
public class StoreOrderController {
	
	@Autowired
	private SstoreOrderSerivce odservice;
	
	@GetMapping(value = "/storeorder/now", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> nowOrder(Integer sStoreNum) {
		return ResponseEntity.ok(new SellResponseDto("주문 요청내역", odservice.storeNowOrderlist(sStoreNum)));
	}
	
	@GetMapping(value = "/storeorder/ing", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> ingOrder(Integer sStoreNum) {
		return ResponseEntity.ok(new SellResponseDto("진행중인 내역", odservice.storeIngOrderlist(sStoreNum)));
	}
	
	@GetMapping(value = "/storeorder/clear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> clearOrder(Integer sStoreNum) {
		return ResponseEntity.ok(new SellResponseDto("완료된 내역", odservice.storeClearOrderlist(sStoreNum)));
	}
	
	@PutMapping(value = "/storeorder/change", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> changeStatus(Integer sStoreNum, Integer aOrderNum) {
		return ResponseEntity.ok(new SellResponseDto("상품 준비", odservice.changeStatus(sStoreNum, aOrderNum)));
	}
	
	@PutMapping(value = "/storeorder/delivery", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> deliveryStatus(Integer sStoreNum, Integer aOrderNum) {
		return ResponseEntity.ok(new SellResponseDto("상품 준비", odservice.deliveryStatus(sStoreNum, aOrderNum)));
	}

	@PutMapping(value = "/storeorder/cansle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> cansleStatus(Integer sStoreNum, Integer aOrderNum) {
		return ResponseEntity.ok(new SellResponseDto("접수 취소", odservice.CansleStatus(sStoreNum, aOrderNum)));
	}	
	
}
