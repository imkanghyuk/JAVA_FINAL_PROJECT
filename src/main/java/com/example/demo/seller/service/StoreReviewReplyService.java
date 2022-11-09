package com.example.demo.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.StoreReviewReply;
import com.example.demo.seller.dto.StoreDto;
import com.example.demo.seller.dto.StoreDto.StoreReplyDto;
import com.example.demo.seller.entity.StoreReply;

@Service
public class StoreReviewReplyService {
	
	@Autowired
	StoreReviewReply rrdao;
	
	public List<StoreDto.StoreReview> readReview(Integer sStoreNum){
		return  rrdao.readReview(sStoreNum);
	}
	
	public String savereply(StoreReplyDto dto) {
		StoreReply sreply = dto.toentity();
		
		if(rrdao.savereply(sreply)==1) {
			return "답글 작성 완료";
		}
		return "답글 작성 실패";
	}
	
	public String deletereply(Integer aReplyNum) {
		if(rrdao.deletereply(aReplyNum) == 1 ) {
			return "답글 삭제 완료";
		}
		return "답글 삭제 실패";
	}
}