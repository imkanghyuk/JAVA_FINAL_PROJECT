package com.example.demo.seller.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.SmenuDto;
import com.example.demo.seller.entity.Smenu;

@Mapper
public interface SmenuDao {

	public Integer menuAdd(Smenu smenu);
	
	public Integer updateMenu(Smenu smenu);
	
	public Integer deleteMenu(Integer sMenuCode);
	
	public Integer deleteByGroup(Integer sGroupNum);
	
	public Optional<Smenu> readMenu(Integer sMenuCode);
	
	public List<SmenuDto.readMenuDto> groupmenuread(Integer sGroupNum);
	
}
