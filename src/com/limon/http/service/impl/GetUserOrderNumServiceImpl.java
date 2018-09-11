package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetUserOrderNumDao;
import com.limon.http.service.GetUserOrderNumService;

@Service("GetUserOrderNumService")
public class GetUserOrderNumServiceImpl implements GetUserOrderNumService{
	@Autowired
	private GetUserOrderNumDao getUserOrderNumDao;

	@Override
	public Integer GetUserOrderNum(Map<String, Object> map) {
		return getUserOrderNumDao.GetUserOrderNum(map);
	}
}
