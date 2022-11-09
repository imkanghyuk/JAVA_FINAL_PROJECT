package com.example.demo.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.SmenuDao;
import com.example.demo.seller.dao.SmenuGroupDao;
import com.example.demo.seller.dto.SgroupDto;
import com.example.demo.seller.entity.SmenuGroup;

@Service
public class SsgroupService {
	
	@Autowired
	private SmenuGroupDao sgdao;
	@Autowired
	private SmenuDao smdao;
	
	public String addmenugroup(SgroupDto.SaveDto dto) {
		SmenuGroup smg = dto.toentity();
		if(sgdao.menugroupadd(smg) == 1) {
			return "생성 완료";
		}
		return "생성 실패";
	}
	
	public SmenuGroup readMenuGroup(Integer sGroupNum) {
		
		return sgdao.readGroup(sGroupNum).get();
	}
	
	public SmenuGroup updateMenuGroup(Integer sGroupNum, String sGroupName) {
		SmenuGroup smg = sgdao.readGroup(sGroupNum).get();
		smg.updateGroupName(sGroupName);
		sgdao.menugroupupdate(smg);
		return sgdao.readGroup(sGroupNum).get();
	}
	
	public List<SmenuGroup> readGroupList(Integer sStoreNum) {
		
		if(sgdao.readGroupList(sStoreNum).size()==0) {
			return null;
		}
		
		return sgdao.readGroupList(sStoreNum);
	}
	
	public String deleteMenuGroup(Integer sGroupNum) {
		smdao.deleteByGroup(sGroupNum);
		if ( sgdao.menugroupdelete(sGroupNum) == 1 ) {
			return "메뉴 그룹 삭제 완료";
		}
		return "메뉴 그룹 삭제 실패";
	}
}
