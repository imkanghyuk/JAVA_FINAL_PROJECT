package com.example.demo.consumer.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.BasketDto;
import com.example.demo.consumer.dto.OrderDetailDto;
import com.example.demo.consumer.entity.AorderDetail;

@Mapper
public interface OrderDetailDao {
	// 상품 추가
	public Integer cOrderDetailSave(AorderDetail aorderDetail);
	
	// 주문 내역 삭제
	public Integer cOrderDetailDelete(String cId);
	
	// 장바구니 목록 가져오기
	public List<BasketDto.List> basketListGet(String cId);
	
	// 상품 가격 수량 가져오기
	public List<OrderDetailDto.PriceDto> menuPriceCountGet(Integer aOrderNum, String cId);
	
	// 주문번호 목록 가져오기
	public Optional<Integer> OrderDetailByOrder(Integer aOrderNum);
	
}
