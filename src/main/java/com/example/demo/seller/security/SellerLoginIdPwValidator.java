package com.example.demo.seller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.sSellerDao;
import com.example.demo.seller.dto.SellerDto;

@Service
public class SellerLoginIdPwValidator implements UserDetailsService {

	@Autowired
	private sSellerDao dao;

	@Override
	public UserDetails loadUserByUsername(String sId) throws UsernameNotFoundException {
		SellerDto.LoginDto user = dao.loginseller(sId).get();
		user.setRole("SELLER");
		
		if (user.getSId() != null) {
			return new SellerUserDetail(user);
		}

		return null;

	}
}
