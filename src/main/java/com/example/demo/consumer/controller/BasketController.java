package com.example.demo.consumer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consumer.dto.BasketDto;
import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.exception.ConsumerJobFailException;
import com.example.demo.consumer.service.BasketService;

@RestController
public class BasketController {
	
	@Autowired
	BasketService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/consumer/basket/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> save(BasketDto.Save dto, Principal principal) {
		if(principal == null) {
			throw new ConsumerJobFailException("로그인이 필요합니다");
		} else {
			dto.adduserId(principal.getName());
			return ResponseEntity.ok(new ResponseDto(service.cBasketAdd(dto, principal.getName()),service.cBasketListRead(principal.getName())));
		}
	}
	
	@GetMapping(value = "/consumer/basket/listread", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> list(Principal principal){
		if(principal == null) {
			return ResponseEntity.ok(new ResponseDto("장바구니 리스트 출력", "비로그인"));
		} else {
			return ResponseEntity.ok(new ResponseDto("장바구니 리스트 출력", service.cBasketListRead(principal.getName())));
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@PatchMapping(value = "/consumer/basket/countupdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> update(BasketDto.CouontUpdate dto, Principal principal){
		dto.addLoginId(principal.getName());
		return ResponseEntity.ok(new ResponseDto(service.UpdateCount(dto), service.cBasketListRead(dto.getCId())));
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "/consumer/basket/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> delete(Principal principal, Integer cBasketNum){
		service.Delete(principal.getName(), cBasketNum);
		return ResponseEntity.ok(new ResponseDto("상품이 삭제되었습니다", service.cBasketListRead(principal.getName())));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/consumer/basket/pricecheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> minOrderPrice(Integer sStoreNum, Integer totalPrice, Principal principal){
		if(service.cBasketListRead(principal.getName()).size() == 0) {
			return ResponseEntity.ok(new ResponseDto("장바구니가 비어있습니다.", "상품없음"));
		}
		if(sStoreNum == null || totalPrice == null) {
			return ResponseEntity.ok(new ResponseDto("확인 결과", null));
		}
		return ResponseEntity.ok(new ResponseDto("확인 결과", service.priceCheck(sStoreNum, totalPrice)));
	}
}
