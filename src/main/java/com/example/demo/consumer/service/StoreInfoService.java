package com.example.demo.consumer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.consumer.dao.StoreInfoDao;
import com.example.demo.consumer.dto.StoreInfoDto;
import com.example.demo.consumer.dto.StoreInfoDto.GetReviewDto;
import com.example.demo.consumer.dto.StoreInfoDto.StoreInfoGet;

@Service
public class StoreInfoService {

	@Autowired
	StoreInfoDao storeinfo;
	
	public List<StoreInfoDto.ReadCategoryDto> categoryRead(){
		return storeinfo.foodCategoryRead();
	}
	
	public List<StoreInfoDto.StoreListDto> storelist(Integer sCategoryNum, String sLocation) {
		Integer sLocationCode = storeinfo.locationInfo(sLocation).get();
		
		return storeinfo.storelist(sCategoryNum, sLocationCode);
	}
	
	public List<StoreInfoDto.StoreInfoDetailDto> storeinfo(Integer sStoreNum, String sLocation) {
		Integer sLocationCode = storeinfo.locationInfo(sLocation).get();
			
		return storeinfo.storeinfo(sStoreNum, sLocationCode);
	}
	
	public List<StoreInfoDto.StoreInfoMenuListDto> menulist(Integer sGroupNum, String sLocation) {
		Integer sLocationCode = storeinfo.locationInfo(sLocation).get();
		
		return storeinfo.menulist(sGroupNum, sLocationCode);
	}
	
	public StoreInfoDto.MenuDetailDto menudetail(Integer sGroupNum, Integer sMenuCode,String sLocation) {
		Integer sLocationCode = storeinfo.locationInfo(sLocation).get();		
		
		return storeinfo.menudetail(sGroupNum, sMenuCode, sLocationCode);
	};
	
	public Optional<String> getStoreName(Integer sStoreNum){
		return storeinfo.getStoreName(sStoreNum);
	}
	
	public List<GetReviewDto> getReview(Integer sStoreNum){
		return storeinfo.getReview(sStoreNum);
	}
	
	public StoreInfoGet getInfoData(Integer sStoreNum) {
		return storeinfo.getStoreInfo(sStoreNum).get();
	}
}