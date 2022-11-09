package com.example.demo.consumer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.consumer.dto.OrderDto;
import com.example.demo.consumer.dto.OrderDto.OrderReadDto;
import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.exception.ListNotFoundException;
import com.example.demo.consumer.service.OrderService;


@Controller
public class OrderController {

	@Autowired
	OrderService service;
	
	@PostMapping(value = "/consumer/order/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> order(OrderDto.OrderSave orderdto, Principal principal) {
		
		if(principal == null) {
			return ResponseEntity.ok(new ResponseDto("로그인 오류", "로그인이 필요합니다"));
		}
		
		return ResponseEntity.ok(new ResponseDto("상품 주문 완료 입니다", service.cOrder(orderdto, principal.getName())));
	}
	
	@GetMapping(value = "/consumer/order/inglist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> ingorderread(Principal principal) {
		
		if(principal == null) {
			return ResponseEntity.ok(new ResponseDto("로그인 오류", "로그인이 필요합니다"));
		}
		
		return ResponseEntity.ok(new ResponseDto("진행 주문내역", service.cOrderNowlist(principal.getName())));
	}
	
	@GetMapping(value = "/consumer/order/beforelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> beforeorderread(Principal principal) {
		
		if(principal == null) {
			return ResponseEntity.ok(new ResponseDto("로그인 오류", "로그인이 필요합니다"));
		}
		
		return ResponseEntity.ok(new ResponseDto("주문내역 출력", service.cOrderBeforelist(principal.getName())));
	}
	
	@GetMapping(value = "/consumer/order/numcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> findOrderNum(Integer aOrderNum, Principal principal){
		
		if(principal == null) {
			return ResponseEntity.ok(new ResponseDto("로그인 오류", "로그인이 필요합니다"));
		}

		return ResponseEntity.ok(new ResponseDto("확인 결과", service.findOrderNum(aOrderNum, principal.getName())));
	}
	
	@GetMapping(value = "/consumer/order/nowlist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> readnow(Principal principal, Integer aOrderNum) {
		
		if(principal == null) {
			return ResponseEntity.ok(new ResponseDto("로그인 오류", "로그인이 필요합니다"));
		}
		
		return ResponseEntity.ok(new ResponseDto("주문신청 내역 출력", service.cOrderNowListRead(principal.getName(), aOrderNum)));
	}
}
