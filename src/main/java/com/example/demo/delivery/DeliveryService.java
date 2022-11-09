package com.example.demo.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.delivery.DeliveryDto.DelireadDto;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryDao ddao;
	
	public List<DelireadDto> ReadOrder(Integer sLocationCode) {
		return ddao.readorder(sLocationCode);
	}
	
	public String chagestatus(Integer aOrderNum) {
		ddao.updatedeliveryStatus(aOrderNum);
		return "변경완료";
	}
}
