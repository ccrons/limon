package com.limon.http.dao;

import java.util.Map;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface UpdatePwdDao {
	public Integer updateAppUserPwd(Map<String,Object> map);
	public Integer checkOldPwd(Map<String,Object> map);
}