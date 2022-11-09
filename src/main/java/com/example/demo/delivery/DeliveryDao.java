package com.example.demo.delivery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.delivery.DeliveryDto.DelireadDto;

@Mapper
public interface DeliveryDao {
	
	public List<DelireadDto> readorder(Integer sLocationCode);
	
	public Integer updatedeliveryStatus(Integer updatedeliveryStatus);
}
