package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetPerInfoDao;
import com.limon.http.model.PerInfo;
import com.limon.http.service.GetPerInfoService;

@Service("GetPerInfoService")
public class GetPerInfoServiceImpl implements GetPerInfoService{
	@Autowired
	private GetPerInfoDao gePerInfoDao;

	public PerInfo getPerInfoById(Integer uid) {
		return gePerInfoDao.getPerInfoById(uid);
	}

}
