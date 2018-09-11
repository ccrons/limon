package com.limon.http.service;

import java.util.Map;

import com.limon.http.model.Version;

public interface AppUpdateCheckService {
	public Version getNewVersion(Map<String, Object> map);
}
