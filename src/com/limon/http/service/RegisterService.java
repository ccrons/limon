package com.limon.http.service;

import com.limon.http.model.AppUser;


public interface RegisterService {
	public Integer saveAppUser(AppUser user);
	public Integer getAppUser(String username);
}
