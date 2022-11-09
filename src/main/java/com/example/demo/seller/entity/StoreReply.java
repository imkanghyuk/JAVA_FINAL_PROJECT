package com.example.demo.seller.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class StoreReply {
	private Integer aReplyNum;
	private String aReplyContent;
	private LocalDate aReplyDate;
	private Integer aReviewNum;
	
}
