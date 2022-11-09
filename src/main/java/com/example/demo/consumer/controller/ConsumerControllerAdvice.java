package com.example.demo.consumer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;

import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.exception.ConsumerJobFailException;
import com.example.demo.consumer.exception.ListNotFoundException;

@ControllerAdvice
public class ConsumerControllerAdvice {
	
	@ExceptionHandler(ConsumerJobFailException.class)
	public ResponseEntity<ResponseDto> jFHandler(ConsumerJobFailException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDto("인증되지 않은 사용자", e.getMessage()));
	}
	
	@ExceptionHandler(ListNotFoundException.class)
	public ResponseEntity<ResponseDto> LNFHandler() {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto("인증되지 않은 사용자", "주문내역을 찾을 수 없습니다"));
	}
	
}
