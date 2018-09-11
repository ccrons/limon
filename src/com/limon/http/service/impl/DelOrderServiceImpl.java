package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.DelOrderDao;
import com.limon.http.service.DelOrderService;

@Service("DelOrderService")
public class DelOrderServiceImpl implements DelOrderService{
	@Autowired
	private DelOrderDao delOrderDao;

	public Integer delOrder(String oid) {
		return delOrderDao.delOrder(oid);
	}
	
}
