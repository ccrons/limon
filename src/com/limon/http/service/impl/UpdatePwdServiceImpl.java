package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.UpdatePwdDao;
import com.limon.http.service.UpdatePwdService;

@Service("UpdatePwdService")
public class UpdatePwdServiceImpl implements UpdatePwdService{
	@Autowired
	private UpdatePwdDao updatePwdDao;

	public Integer checkOldPwd(Map<String,Object> map) {
		return updatePwdDao.checkOldPwd(map);
	}

	public Integer updateAppUserPwd(Map<String,Object> map) {
		 return updatePwdDao.updateAppUserPwd(map);
	}

}
