package com.limon.http.dao;

import com.limon.http.model.AppUser;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface RegisterDao {
	public Integer saveAppUser(AppUser user);
	public Integer getAppUser(String username);
}