package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.RegisterDao;
import com.limon.http.model.AppUser;
import com.limon.http.service.RegisterService;

@Service("RegisterService")
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	private RegisterDao registerDao;

	public Integer saveAppUser(AppUser user) {
		return registerDao.saveAppUser(user);
	}

	public Integer getAppUser(String username) {
		return registerDao.getAppUser(username);
	}
}
