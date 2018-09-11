package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.DelAddressDao;
import com.limon.http.service.DelAddressService;

@Service("DelAddressService")
public class DelAddressServiceImpl implements DelAddressService{
	@Autowired
	private DelAddressDao delAddressDao;

	public Integer delUserAddr(Integer aid) {
		return delAddressDao.delUserAddr(aid);
	}
	
}
