package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.DelCarDao;
import com.limon.http.service.DelCarService;

@Service("DelCarService")
public class DelCarServiceImpl implements DelCarService{
	@Autowired
	private DelCarDao delCarDao;

	public Integer delCar(Map<String,Object> map) {
		return delCarDao.delCar(map);
	}
	
}
