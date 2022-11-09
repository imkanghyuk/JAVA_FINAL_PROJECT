package com.example.demo.seller.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.StoreOrderDto.SellerListDto;

@Mapper
public interface SstoreOrderDao {
	
	public List<SellerListDto> storeNowOrderlist(Integer sStoreNum); 
	
	public List<SellerListDto> storeIngOrderlist(Integer sStoreNum);
	
	public List<SellerListDto> storeClearOrderlist(Integer sStoreNum); 
	
	public Integer storeOrderStatusChange(Integer sStoreNum, Integer aOrderNum);
	
	public Integer storeOrderStatusdelivery(Integer sStoreNum, Integer aOrderNum);
	
	public Integer storeOrderStatusCansle(Integer sStoreNum, Integer aOrderNum);
}
