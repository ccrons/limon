package com.limon.http.dao;

import java.util.Map;

import com.limon.http.model.AppUser;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface LoginDao {
	public AppUser getUserByUserName(Map<String, String> map);
	public AppUser getUserByUserNameAndPassword(Map<String, String> map);
	public Integer saveAppUser(AppUser user);
}