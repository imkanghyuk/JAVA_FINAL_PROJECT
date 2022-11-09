package com.example.demo.seller.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.seller.dao.SstoreDao;
import com.example.demo.seller.dao.sSellerDao;
import com.example.demo.seller.dto.StoreDto;
import com.example.demo.seller.dto.StoreDto.CategoryDto;
import com.example.demo.seller.dto.StoreDto.LocationDto;
import com.example.demo.seller.dto.StoreDto.StoreRead;
import com.example.demo.seller.dto.StoreDto.StoreSaveDto;
import com.example.demo.seller.entity.Sseller;
import com.example.demo.seller.entity.Sstore;

@Service
public class SstoreService {
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Autowired
	private SstoreDao storedao;
	@Autowired
	private sSellerDao seldao;
	
	
	public StoreRead storeAdd(StoreSaveDto dto, String sId) {
		
		String S3Bucket = "iciasmartframe2/upload/seller/storelogo";

		Sstore store = dto.toEntity();
		
		String imgName = "default.JPG";

		MultipartFile profileimg = dto.getSStoreLogo();

		if (profileimg != null && profileimg.isEmpty() == false) {

			try {
				
				imgName = System.currentTimeMillis() + "_" + profileimg.getOriginalFilename();
				
				long size = profileimg.getSize(); // 파일 크기
				
				ObjectMetadata objectMetaData = new ObjectMetadata();
				objectMetaData.setContentType(profileimg.getContentType());
				objectMetaData.setContentLength(size);
				
				// S3에 업로드
				amazonS3Client.putObject(
					new PutObjectRequest(S3Bucket, imgName, profileimg.getInputStream(), objectMetaData)
						.withCannedAcl(CannedAccessControlList.PublicRead)
				);
				
			}

			catch (IllegalStateException e) {
				e.printStackTrace();
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}
		store.addinfo(imgName, sId);
		storedao.sStoreAdd(store);
		
		Integer storenum = storedao.sStoreRead(sId).get().getSStoreNum();
		Sseller entity = seldao.sellerAllread(sId).get();
		
		entity.addStoreNum(storenum);
		seldao.sSellerUpdate(entity);
		return storedao.sStoreRead(sId).get();
	}
	
	public List<LocationDto> locationinfo() {
		return storedao.getLocation();
	}
	
	public List<CategoryDto> categoryinfo() {
		return storedao.getCategory();
	}
	
	public StoreRead storeRead(String sId) {
		return storedao.sStoreRead(sId).get();
	}

	public StoreRead chagestatus(Integer sStoreNum, Integer sStoreStatus, String sId) {
		if(sStoreStatus == 0) {
			sStoreStatus = 1;
			storedao.changeStatus(sStoreNum, sStoreStatus, sId);
			return storedao.sStoreRead(sId).get();
		} else {
			sStoreStatus = 0;
			storedao.changeStatus(sStoreNum, sStoreStatus, sId);
			return storedao.sStoreRead(sId).get();
		}
	}
	
	public String storedelete (Integer sStoreNum, String sId) {
		// 가게 소개 이미지 삭제
		// 가게 답글 삭제
		// 가게 리뷰 삭제
		// 가게 주문 내역 삭제
		// 가게 주문 디테일 삭제
		// 가게 메뉴 전체 삭제
		// 가게 메뉴 그룹 삭제
		
		storedao.deleteStore(sStoreNum, sId);

		Sseller entity = seldao.sellerAllread(sId).get();
		entity.addStoreNum(0);
		seldao.sSellerUpdate(entity);
		
		return "가게 삭제 완료";
	}
	
	public String addImgInfo(StoreDto.htmlInfoImgDto dto) {
		
		String S3Bucket = "iciasmartframe2/upload/seller/storeintro";
		
		for(MultipartFile multipartFile : dto.getImginfo()) {
			String imgName = "default.JPG";

			if (multipartFile != null && multipartFile.isEmpty() == false) {

			try {
				
				imgName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
				
				long size = multipartFile.getSize(); // 파일 크기
				
				ObjectMetadata objectMetaData = new ObjectMetadata();
				objectMetaData.setContentType(multipartFile.getContentType());
				objectMetaData.setContentLength(size);
				
				amazonS3Client.putObject(
					new PutObjectRequest(S3Bucket, imgName, multipartFile.getInputStream(), objectMetaData)
						.withCannedAcl(CannedAccessControlList.PublicRead)
				);
				
			}

			catch (IllegalStateException e) {
				e.printStackTrace();
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}
		StoreDto.addInfoImgDto imgDto = new StoreDto.addInfoImgDto();
		imgDto.setSInfoImg(imgName);
		imgDto.setSStoreNum(dto.getSStoreNum());
		
		storedao.addInfoImg(imgDto);
		
	}
		return "사진 등록 완료";
	}
	
	public List<StoreDto.readIntroImg> readIntroImg(Integer sStoreNum) {
		if(storedao.readinfoimg(sStoreNum).size() == 0) {
			return null;
		}
		return storedao.readinfoimg(sStoreNum);
	}
	
	public String deleteIntroImg(Integer sStoreNum) {
		if(storedao.readinfoimg(sStoreNum).size() == storedao.deleteInfoImg(sStoreNum)) {
			return "가게 소개 이미지 삭제 완료";
		}
		return "가게 소개 이미지 삭제 실패";
	}
}

