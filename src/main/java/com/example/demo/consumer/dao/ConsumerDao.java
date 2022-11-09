package com.example.demo.consumer.dao;

import java.time.LocalDate;
import java.util.Optional;


import org.apache.ibatis.annotations.Mapper;

import com.example.demo.ConsumerLevel;
import com.example.demo.OrderStatus;
import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.entity.Consumer;

@Mapper
public interface ConsumerDao {
	// 회원 가입
	public Integer cMemberJoin(Consumer consumer);
	
	// 회원 정보 수정
	public Integer cMemberUpdate(Consumer consumer);
	
	// 구매 횟수 가져오기
	public Optional<Integer> BuyCount(String cId, OrderStatus aOrderStatus);
	
	// 회원 정보 출력
	public Optional<ConsumerDto.ConsumerRead> cMemberInforRead(String cId);
	
	// 아이디 중복 검사
	public Boolean cIdOverlap (String cId);
	
	// 닉네임 중복 검사
	public Boolean cNickOverlap (String cNickname);
	
	// 이메일 중복 검사
	public Boolean cEmailOverlap (String cEmail);
	
	// 아이디 찾기
	public Optional<String> cFindById(String cEmail);
	
	// 회원 탈퇴
	public Integer cMemberClear(String cId);
	
	// 로그인 정보 가져오기
	public Optional<ConsumerDto.UserDto> scCheck(String cId);
	
	// 전월의 처음과 끝일 가져오기
	public Optional<ConsumerDto.dateDto> findDate();
	
	// 전월 주문완료 내역 가져오기
	public Optional<Integer> myordercount(String cId, LocalDate startdate, LocalDate enddate);
	
	// 등급 업데이트
	public Integer levelupdate(String cId, ConsumerLevel cLevel);
	
	// 업데이트를 위한 전체내용 가져오기
	public Optional<Consumer> userdataall(String cId);
}
