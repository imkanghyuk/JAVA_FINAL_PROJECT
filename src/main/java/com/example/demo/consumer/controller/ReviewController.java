package com.example.demo.consumer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.dto.ReviewDto;
import com.example.demo.consumer.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	ReviewService service;
	
	@PostMapping(value = "/consumer/review/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> save(ReviewDto.SaveDto dto, Principal principal) {
		System.out.println(dto);
		return ResponseEntity.ok(new ResponseDto("리뷰작성이 완료되었습니다", service.cReviewWrtie(dto, principal.getName())));
	}
	
	@GetMapping(value = "/consumer/review/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> read(Principal principal) {
		return ResponseEntity.ok(new ResponseDto("사용자의 리뷰 출력", service.cReviewRead(principal.getName())));
	}
	
	@DeleteMapping(value = "/consumer/review/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> delete(Integer aReviewNum, Principal principal) {
		return ResponseEntity.ok(new ResponseDto(service.delete(aReviewNum, principal.getName()), "댓글을 삭제했습니다"));
	}
	
	@GetMapping(value = "/consumer/review/existence", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> existence(Integer aOrderNum, Principal principal) {
		return ResponseEntity.ok(new ResponseDto("해당 주문의 리뷰존재", service.checkreview(aOrderNum, principal.getName())));
	}
}
