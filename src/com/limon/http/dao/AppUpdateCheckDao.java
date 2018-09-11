package com.limon.http.dao;

import java.util.Map;

import com.limon.http.model.Version;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface AppUpdateCheckDao {
	public Version getNewVersion(Map<String, Object> map);
}