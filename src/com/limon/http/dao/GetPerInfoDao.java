package com.limon.http.dao;

import com.limon.http.model.PerInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetPerInfoDao {
	public PerInfo getPerInfoById(Integer uid);
}