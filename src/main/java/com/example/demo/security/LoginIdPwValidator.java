package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.dto.ConsumerDto;

// 시큐리티 설정에서 form 요청시 자동으로 자동으로 해당 메소드 실행 loadUserByUsername
@Service
public class LoginIdPwValidator implements UserDetailsService {

	@Autowired
	private ConsumerDao dao;

	// return시 시큐리티 세션 authentication
	// 시큐리티세션(Authentication(UserDetails))
	@Override
	public UserDetails loadUserByUsername(String cId) throws UsernameNotFoundException {
		ConsumerDto.UserDto user = dao.scCheck(cId).get();
		user.setRole("CONSUMER");
		
		if (user.getCId() != null) {
			return new UserDetail(user);
		}

		return null;

	}
}
