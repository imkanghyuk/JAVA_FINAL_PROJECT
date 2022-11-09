package com.example.demo.consumer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.OrderDto;
import com.example.demo.consumer.entity.Aorder;

@Mapper
public interface OrderDao {
	
	// 주문하기 
	public Integer cOrder(Aorder aorder);
	
	// 주문 상태 확인
	public Integer cOrderStatusCheck(String cId);
	
	// 주문 내역 출력 
	public List<OrderDto.ListDto> cOrderNowlist(String cId);
	
	// 주문 상세 내역 출력
	public List<OrderDto.ListAllDto> cOrderBeforelist(String cId);

	// 주문 삭제
	public Integer cOrderCancel(String cId);	
	
	// 총합 업데이트
	public Integer totalPriceUpdate(Integer aOrderNum, Integer aTotalPrice, String cId);
	
	// 주문번호 찾기
	public Boolean findByOrderNum(Integer aOrderNum, String cId);
	
	// 삭제시 삭제된 아이디 계정 바꾸기
	public Integer deleteUserChange(String newCid, String cId);
	
	// 결제완료 내역 출력 
	public List<OrderDto.ListAllDto> cOrderNowListRead(String cId, Integer aOrderNum);
}
