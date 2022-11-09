package com.example.demo.seller.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.SellerDto;
import com.example.demo.seller.entity.Sseller;

@Mapper
public interface sSellerDao {
	
	public Integer sSellerJoin(Sseller seller);
	
	public Optional<Sseller> sellerAllread(String sId);
	
	public Integer sSellerUpdate(Sseller seller);
	
	public Integer sSellerDelete(String sId);
	
	public Optional<SellerDto.ReadDto> sellerread(String sId);
	
	public Optional<SellerDto.LoginDto> loginseller(String sId);

	public Optional<Boolean> sellerBuischeck(String sBuisnessNum);
	
}
