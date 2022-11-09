package com.example.demo.seller.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.StoreDto;
import com.example.demo.seller.entity.StoreReply;

@Mapper
public interface StoreReviewReply {
	
	public List<StoreDto.StoreReview> readReview(Integer sStoreNum);
	
	public Integer savereply(StoreReply storeReply);
	
	public Integer deletereply(Integer aReplyNum);
}
