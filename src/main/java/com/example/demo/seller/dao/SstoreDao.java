package com.example.demo.seller.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.StoreDto;
import com.example.demo.seller.entity.Sstore;

@Mapper
public interface SstoreDao {
	public Integer sStoreAdd(Sstore sstore);
	
	public List<StoreDto.LocationDto> getLocation();
	
	public List<StoreDto.CategoryDto> getCategory();
	
	public Optional<StoreDto.StoreRead> sStoreRead(String sId);
	
	public Integer changeStatus(Integer sStoreNum, Integer sStoreStatus, String sId);

	public Integer deleteStore(Integer sStoreNum, String sId);
	
	public Integer addInfoImg(StoreDto.addInfoImgDto dto);
	
	public List<StoreDto.readIntroImg> readinfoimg(Integer sStoreNum);
	
	public Integer deleteInfoImg(Integer sStoreNum);
}
