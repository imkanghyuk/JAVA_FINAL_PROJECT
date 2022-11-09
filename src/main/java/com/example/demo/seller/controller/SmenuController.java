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
import com.example.demo.seller.dto.SmenuDto;
import com.example.demo.seller.service.SmenuService;


@RestController
public class SmenuController {

	@Autowired
	private SmenuService menuservice;
	
	@PostMapping(value = "/seller/menuadd", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SellResponseDto> menuadd(SmenuDto.saveMenuDto dto) {
		return ResponseEntity.ok(new SellResponseDto("메뉴 등록 결과", menuservice.addMenu(dto)));
	}
	
	@PutMapping(value = "/seller/menuupdate", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SellResponseDto> menuupdate(SmenuDto.updateMenuDto dto, Integer sMenuCode) {
		
		return ResponseEntity.ok(new SellResponseDto("메뉴 수정 결과", menuservice.updateMenu(dto, sMenuCode)));
	}
	
	@DeleteMapping(value = "/seller/menudelete", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SellResponseDto> menudelete(Integer sMenuCode) {
		
		return ResponseEntity.ok(new SellResponseDto("메뉴 삭제 결과", menuservice.deleteMenu(sMenuCode)));
	}
	
	@GetMapping(value = "/seller/menulist", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SellResponseDto> menulist(Integer sGroupNum) {
		
		return ResponseEntity.ok(new SellResponseDto("메뉴 리스트", menuservice.groupmenuread(sGroupNum)));
	}
}
