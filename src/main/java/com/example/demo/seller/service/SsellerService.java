package com.example.demo.seller.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.demo.seller.dao.sSellerDao;
import com.example.demo.seller.dto.SellerDto;
import com.example.demo.seller.entity.Sseller;

@Service
public class SsellerService {

	@Autowired
	private sSellerDao sDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SellerDto.ReadDto sellerJoin(SellerDto.SaveDto dto){
		Sseller seller = dto.toEntity();
		LocalDate date = LocalDate.parse(dto.getSBirth());
		String encodedPw = passwordEncoder.encode(dto.getSPassword());
		seller.addinfo(date, encodedPw);
		sDao.sSellerJoin(seller);
		return sDao.sellerread(dto.getSId()).get();
	}
	
	public SellerDto.ReadDto sellerRead(String sId) {
		return sDao.sellerread(sId).get();
	}
	
	public Sseller sellerAllRead(String sId) {
		return sDao.sellerAllread(sId).get();
	}
	
	public SellerDto.ReadDto sellerUpdate(SellerDto.UpdateDto dto, String sId){
		Sseller seller = sDao.sellerAllread(sId).get();

		seller.update(dto.getSEmail(),dto.getSPhone());
		sDao.sSellerUpdate(seller);
		
		return sDao.sellerread(seller.getSId()).get();
	}
	
	public Integer sellerDelete(String sId) {
		return sDao.sSellerDelete(sId);
	}
	
	public SellerDto.LoginDto loginSeller(String sId) {
		return sDao.loginseller(sId).get();
	}
	
	public Boolean selleridcheck(String sId) {
		if(sDao.sellerread(sId).isEmpty()) {
			return true;
		} else {
			return false;			
		}
	}

	public Boolean sellerBuischeck(String sBuisnessNum) {
		if(sDao.sellerBuischeck(sBuisnessNum).get()) {
			return false;
		} else {
			return true;
		}
	}
	
}

