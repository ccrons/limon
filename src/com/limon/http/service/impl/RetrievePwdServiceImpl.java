package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.RetrievePwdDao;
import com.limon.http.service.RetrievePwdService;

@Service("RetrievePwdService")
public class RetrievePwdServiceImpl implements RetrievePwdService{
	@Autowired
	private RetrievePwdDao retrievePwdDao;

	public Integer checkOldPwd(Map<String,Object> map) {
		return retrievePwdDao.checkOldPwd(map);
	}

	public Integer updateAppUserPwd(Map<String,Object> map) {
		 return retrievePwdDao.updateAppUserPwd(map);
	}

}
