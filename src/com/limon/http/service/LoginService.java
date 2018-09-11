package com.limon.http.service;

import com.limon.http.model.AppUser;

public interface LoginService {
	public AppUser getUserByUserName(String username);
	public AppUser getUserByUserNameAndPassword(String username,String password);
	public Integer saveAppUser(AppUser user);
}
