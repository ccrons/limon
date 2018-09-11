package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.SetHeadImgDao;
import com.limon.http.service.SetHeadImgService;

@Service("SetHeadImgService")
public class SetHeadImgServiceImpl implements SetHeadImgService{
	@Autowired
	private SetHeadImgDao setHeadImgDao;

	public void updatePerInfo(Map<String, Object> map) {
		setHeadImgDao.updatePerInfo(map);
	}

}
