package com.example.demo.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.seller.dto.SellResponseDto;
import com.example.demo.seller.dto.StoreDto.StoreReplyDto;
import com.example.demo.seller.service.StoreReviewReplyService;

@RestController
public class StoreReviewReplyController {
	
	@Autowired
	StoreReviewReplyService srrservice;
	
	@GetMapping(value = "/seller/store/reviewread", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> readreview(Integer sStoreNum) {
		return ResponseEntity.ok(new SellResponseDto("가게 리뷰 목록", srrservice.readReview(sStoreNum)));
	}
	
	@PostMapping(value = "/seller/store/replyadd", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> addreply(StoreReplyDto dto) {
		return ResponseEntity.ok(new SellResponseDto("처리 결과", srrservice.savereply(dto)));
	}
	
	@DeleteMapping(value = "/seller/store/replydelete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> deletereply(Integer aReplyNum) {
		return ResponseEntity.ok(new SellResponseDto("처리 결과", srrservice.deletereply(aReplyNum)));
	}
}
