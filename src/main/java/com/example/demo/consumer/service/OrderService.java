package com.example.demo.consumer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.OrderStatus;
import com.example.demo.consumer.dao.BasketDao;
import com.example.demo.consumer.dao.OrderDao;
import com.example.demo.consumer.dao.OrderDetailDao;
import com.example.demo.consumer.dto.OrderDetailDto;
import com.example.demo.consumer.dto.OrderDto;
import com.example.demo.consumer.dto.OrderDto.OrderReadDto;
import com.example.demo.consumer.entity.Aorder;
import com.example.demo.consumer.entity.AorderDetail;

@Service
public class OrderService {
	
	@Autowired
	OrderDao aorderDao;	
	
	@Autowired
	OrderDetailDao aorderDetailDao;
	
	@Autowired
	BasketDao basketDao;
	
	public Aorder cOrder(OrderDto.OrderSave orderdto, String username) {
		orderdto.addCID(username);	
		Aorder aor = orderdto.toentity();
		aor.addinfo(OrderStatus.접수대기, 0);
		aorderDao.cOrder(aor);
		
		for(int i=0; i<basketDao.cBasketListSize(orderdto.getCId());i++) {
			OrderDetailDto.SaveDto dto = OrderDetailDto.SaveDto.builder().aOrderNum(aor.getAOrderNum()).sMenuCode(aorderDetailDao.basketListGet(orderdto.getCId()).get(i).getSMenuCode())
			.sGroupNum(aorderDetailDao.basketListGet(orderdto.getCId()).get(i).getSGroupNum()).aCount(aorderDetailDao.basketListGet(orderdto.getCId()).get(i).getCMenuCount()).build();
			
			AorderDetail result = dto.toEntity();
			aorderDetailDao.cOrderDetailSave(result);
		}
		
		List<Integer> total = new ArrayList<>();
		int totalPirce = 0; 		
		List<OrderDetailDto.PriceDto> value = aorderDetailDao.menuPriceCountGet(aor.getAOrderNum(), aor.getCId());
		
		for(int i=0; i< aorderDetailDao.OrderDetailByOrder(aor.getAOrderNum()).get(); i++) {
			int count = value.get(i).getACount();
			int price = value.get(i).getSMenuPrice();
			
			total.add(i, count * price);
		}
		
		for(int i=0; i<total.size(); i++) {
			totalPirce = totalPirce + total.get(i);
		}
		
		aor.addTotalPrice(totalPirce);
		
		aorderDao.totalPriceUpdate(aor.getAOrderNum(),aor.getATotalPrice(),aor.getCId());
		
		basketDao.cBasketListDelete(orderdto.getCId(), null);

		return aor;
	}

	public List<OrderDto.ListAllDto> cOrderBeforelist(String cId) {	 
		
		return aorderDao.cOrderBeforelist(cId);
	}

	public List<OrderDto.ListDto> cOrderNowlist(String username) {
		return aorderDao.cOrderNowlist(username);
	}
	
	public Boolean findOrderNum(Integer aOrderNum, String cId) {
		if(aorderDao.findByOrderNum(aOrderNum, cId)) {
			return true;
		}
		return false;
	}
	
	public List<OrderDto.ListAllDto> cOrderNowListRead(String username, Integer aOrderNum) {
		return aorderDao.cOrderNowListRead(username, aOrderNum);
	}

}
