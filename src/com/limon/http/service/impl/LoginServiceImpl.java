package com.limon.http.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.LoginDao;
import com.limon.http.model.AppUser;
import com.limon.http.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	public AppUser getUserByUserName(String username) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("username", username);
		return loginDao.getUserByUserName(map);
	}

	public AppUser getUserByUserNameAndPassword(String username, String password) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		return loginDao.getUserByUserNameAndPassword(map);
	}

	public Integer saveAppUser(AppUser user) {
		return loginDao.saveAppUser(user);
	}

}
