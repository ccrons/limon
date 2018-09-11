package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetHpInfoDao;
import com.limon.http.service.GetHpInfoService;

@Service("GetHpInfoService")
public class GetHpInfoServiceImpl implements GetHpInfoService{
	@Autowired
	private GetHpInfoDao getHpInfoDao;

}
