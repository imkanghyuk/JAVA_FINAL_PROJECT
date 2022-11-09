package com.example.demo.consumer.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.StoreInfoDto;
import com.example.demo.consumer.dto.StoreInfoDto.GetReviewDto;
import com.example.demo.consumer.dto.StoreInfoDto.StoreInfoGet;

@Mapper
public interface StoreInfoDao {

	// 가게 리스트 출력
	public List<StoreInfoDto.StoreListDto> storelist(Integer sCategoryNum, Integer sLocationCode);
	// 가게 정보 출력
	public List<StoreInfoDto.StoreInfoDetailDto> storeinfo(Integer sStoreNum, Integer sLocationCode);
	// 메뉴 상세 리스트 출력
	public List<StoreInfoDto.StoreInfoMenuListDto> menulist(Integer sGroupNum, Integer sLocationCode);
	// 메뉴 상세 정보
	public StoreInfoDto.MenuDetailDto menudetail(Integer sGroupNum, Integer sMenuCode,Integer sLocationCode);	
	// 카테고리 리스트 읽어오기
	public List<StoreInfoDto.ReadCategoryDto> foodCategoryRead();
	
	// 위치정보 읽어오기
	public Optional<Integer> locationInfo(String nowLocation);
	// 가게 번호로 이름 가져오기
	public Optional<String> getStoreName(Integer sStoreNum);
	
	// 가게 리뷰 출력
	public List<GetReviewDto> getReview(Integer sStoreNum);
	
	// 가게 정보 출력
	public Optional<StoreInfoGet> getStoreInfo(Integer sStoreNum);
	
}
