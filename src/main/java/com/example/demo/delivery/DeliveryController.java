package com.example.demo.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.delivery.DeliveryDto.DelireadDto;

@Controller
public class DeliveryController {
	@Autowired
	DeliveryService dlserv;
	
	@GetMapping(value = "/delivery/changeorder")
	public void deliveryview () {
		
	}
	
	@GetMapping(value = "/delivery/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DelireadDto>> listread(Integer sLocationCode){
		return ResponseEntity.ok(dlserv.ReadOrder(sLocationCode));
	}
	
	@PutMapping(value = "/delivery/chage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changestatus(Integer aOrderNum){
		return ResponseEntity.ok(dlserv.chagestatus(aOrderNum)); 
	}
	
	
	
}
