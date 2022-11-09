package com.example.demo.consumer.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.ReviewDto;
import com.example.demo.consumer.entity.Areview;

@Mapper
public interface AreviewDao {
	// 리뷰 작성
	public Integer cReviewWrtie(Areview areview);
	
	// 리뷰 리스트 출력
	public List<ReviewDto.ListDto> cReviewRead(String cId);
	
	// 리뷰 삭제
	public Integer cReviewDelete(Integer aReviewNum, String cId);
		
	// 답글 삭제 (리뷰 삭제시)
	public Integer deleteReply(Integer aReviewNum);
	
	// 리뷰작성 확인
	public Boolean reviewCheck(Integer aOrderNum, String cId);
	
}

