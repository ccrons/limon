package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.AppUpdateCheckDao;
import com.limon.http.model.Version;
import com.limon.http.service.AppUpdateCheckService;

@Service("AppUpdateCheckService")
public class AppUpdateCheckServiceImpl implements AppUpdateCheckService{
	@Autowired
	private AppUpdateCheckDao appUpdateCheckDao;

	public Version getNewVersion(Map<String, Object> map) {
		return appUpdateCheckDao.getNewVersion(map);
	}
}
