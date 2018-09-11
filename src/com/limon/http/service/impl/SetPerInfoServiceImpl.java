package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.SetPerInfoDao;
import com.limon.http.service.SetPerInfoService;

@Service("SetPerInfoService")
public class SetPerInfoServiceImpl implements SetPerInfoService{
	@Autowired
	private SetPerInfoDao setPerInfoDao;

	public void setPerInfo(Map<String, Object> map) {
		setPerInfoDao.setPerInfo(map);
	}

}
