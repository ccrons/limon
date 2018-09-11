package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.DelCarAllDao;
import com.limon.http.service.DelCarAllService;

@Service("DelCarAllService")
public class DelCarAllServiceImpl implements DelCarAllService{
	@Autowired
	private DelCarAllDao delCarAllDao;

	@Override
	public Integer delCarAll(Integer userid) {
		return delCarAllDao.delCarAll(userid);
	}
}
