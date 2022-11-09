package com.example.demo.seller.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.entity.SmenuGroup;

@Mapper
public interface SmenuGroupDao {
	
	public Integer menugroupadd(SmenuGroup smenuGroup);
	
	public Integer menugroupupdate(SmenuGroup smenuGroup);
	
	public Integer menugroupdelete(Integer sGroupNum);
	
	public Optional<SmenuGroup> readGroup(Integer sGroupNum);
	
	public List<SmenuGroup> readGroupList(Integer sStoreNum);
	
}
