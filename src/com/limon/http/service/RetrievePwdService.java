package com.limon.http.service;

import java.util.Map;

public interface RetrievePwdService {
	public Integer updateAppUserPwd(Map<String,Object> map);
	public Integer checkOldPwd(Map<String,Object> map);
}
