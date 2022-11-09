package com.example.demo.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.SstoreOrderDao;
import com.example.demo.seller.dto.StoreOrderDto.SellerListDto;

@Service
public class SstoreOrderSerivce {

	@Autowired
	private SstoreOrderDao orderdao;
	
	public List<SellerListDto> storeNowOrderlist(Integer sStoreNum) {
		return orderdao.storeNowOrderlist(sStoreNum);
	}
	
	public List<SellerListDto> storeIngOrderlist(Integer sStoreNum) {
		return orderdao.storeIngOrderlist(sStoreNum);
	}
	
	public List<SellerListDto> storeClearOrderlist(Integer sStoreNum) {
		return orderdao.storeClearOrderlist(sStoreNum);
	}
	
	public String changeStatus(Integer sStoreNum, Integer aOrderNum) {
		if(orderdao.storeOrderStatusChange(sStoreNum, aOrderNum)==1) {
			return "주문접수 완료";
		}
		return "상태 변경 실패";
	}
	
	public String deliveryStatus(Integer sStoreNum, Integer aOrderNum) {
		if(orderdao.storeOrderStatusdelivery(sStoreNum, aOrderNum)==1) {
			return "배달 시작";
		}
		return "상태 변경 실패";
	}
	
	public String CansleStatus(Integer sStoreNum, Integer aOrderNum) {
		if(orderdao.storeOrderStatusCansle(sStoreNum, aOrderNum)==1) {
			return "주문 취소 완료";
		}
		return "상태 변경 실패";
	}
}
