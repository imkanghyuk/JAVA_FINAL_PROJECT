package com.example.demo.seller.controller;

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
import com.example.demo.seller.dto.SgroupDto;
import com.example.demo.seller.service.SsgroupService;

@RestController
public class SmenuGroupController {

	@Autowired
	private SsgroupService smgservice;
	
	@PostMapping(value = "/seller/menugroup/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> save(SgroupDto.SaveDto dto) {
		
		return ResponseEntity.ok(new SellResponseDto("작업 결과", smgservice.addmenugroup(dto)));
	}
	
	@GetMapping(value = "/seller/menugroup/storemglist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> storemglist(Integer sStoreNum) {
		
		return ResponseEntity.ok(new SellResponseDto("작업 결과", smgservice.readGroupList(sStoreNum)));
	}
	
	@PutMapping(value = "/seller/menugroup/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> updateName(Integer sGroupNum, String sGroupName) {
		if (smgservice.updateMenuGroup(sGroupNum, sGroupName)==null) {
			return ResponseEntity.ok(new SellResponseDto("작업 결과", "내용 수정 실패"));
		}
		return ResponseEntity.ok(new SellResponseDto("작업 결과", "내용 수정 성공"));
	}
	
	@DeleteMapping(value = "/seller/menugroup/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellResponseDto> deleteGroup(Integer sGroupNum) {
		return ResponseEntity.ok(new SellResponseDto("작업 결과", smgservice.deleteMenuGroup(sGroupNum)));
	}		
}
