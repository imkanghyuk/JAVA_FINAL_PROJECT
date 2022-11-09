package com.example.demo.consumer.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.BasketDto;
import com.example.demo.consumer.entity.Cbasket;

@Mapper
public interface BasketDao {

	public Integer cBasketAdd(Cbasket cb);
	
	public List<BasketDto.Read> cBasketListRead(String cId);
	
	public Optional<Integer> cFindByStore(Integer listSize, String cId);
	
	public Optional<Integer> cFindByMenu(Integer cBasketNum, String cId);
	
	public Integer cBasketCountUpdatePlus(Integer sMenuCode, String cId);
	
	public Integer cBasketCountUpdateMinus(Integer sMenuCode, String cId);
	
	public Integer cBasketListDelete(String cId, Integer cBasketNum);
	
	public Integer cBasketListSize(String cId);
	
	public Integer cBasketPlusCount(Integer sMenuCode, Integer cMenuCount, String cId);
	
	public Boolean cBasketDuplicateCheck(Integer sMenuCode, String cId);
	
	public Integer cBasketCount(Integer sMenuCode, String cId);
	
	public Optional<Integer> cFindByBasketNum(Integer sMenuCode, String cId);
	
	
	// 최소주문금액 확인
	public Optional<Integer> mindelevery(Integer sStoreNum);
}