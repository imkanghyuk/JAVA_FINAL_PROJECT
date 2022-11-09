package com.example.demo.consumer.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.consumer.dao.BasketDao;
import com.example.demo.consumer.dto.BasketDto;
import com.example.demo.consumer.entity.Cbasket;

@Service
public class BasketService {

	@Autowired
	BasketDao dao;
	
	public String cBasketAdd(BasketDto.Save dto, String username) {
		dto.adduserId(username);
		if(dao.cBasketListSize(dto.getCId()) == 0) {
			Cbasket newcb =  dto.toEntity();
			dao.cBasketAdd(newcb);
			
			return "장바구니에 상품을 추가했습니다.";			
		}
		
		if (dto.getSStoreNum() != dao.cFindByStore(dao.cBasketListSize(dto.getCId()) , dto.getCId()).get()) {
			dao.cBasketListDelete(dto.getCId(), null);
			
			Cbasket newcb =  dto.toEntity();
			dao.cBasketAdd(newcb);
			
			return "장바구니에 새로운 가게 상품이 담겼습니다";
		}
		
		if(dao.cBasketDuplicateCheck(dto.getSMenuCode(), dto.getCId())) {
			
			dao.cBasketPlusCount(dto.getSMenuCode(), dto.getCMenuCount(), dto.getCId());
			
			return "기존 추가된 상품의 수량이 변경 되었습니다.";
		}
		
		else {
		Cbasket newcb =  dto.toEntity();
		dao.cBasketAdd(newcb);
		
		return "장바구니에 상품을 추가했습니다.";
		}
	}
	
	public List<BasketDto.Read> cBasketListRead(String cId) {
		
		return dao.cBasketListRead(cId);
	}
	
	public String UpdateCount(BasketDto.CouontUpdate dto) {
		
		if(dto.getCountVal()) { // true 일 경우 + false 일 경우 -
			dao.cBasketCountUpdatePlus(dto.getSMenuCode(), dto.getCId());
			return "수량이 추가되었습니다";
		}
		else {
			dao.cBasketCountUpdateMinus(dto.getSMenuCode(), dto.getCId());
			
			if(dao.cBasketCount(dto.getSMenuCode(), dto.getCId()) == 0) {
				
				dao.cBasketListDelete(dto.getCId(), dao.cFindByBasketNum(dto.getSMenuCode(), dto.getCId()).get());
				
				return "상품이 삭제되었습니다";
			}
			
			return "수량이 감소되었습니다";
		}
		

	}
	
	public void Delete(String cId, Integer cBasketNum) {
		
		dao.cBasketListDelete(cId, cBasketNum);
		
	}
	
	public Boolean priceCheck(Integer sStoreNum, Integer totalPrice) {
		if(totalPrice >= dao.mindelevery(sStoreNum).get()) {
			return true;
		}
		return false;
	}
	
}
