package com.limon.wx.service;

import java.util.Map;
import com.limon.wx.model.UserInfo;

public interface UserInfoService {
	public UserInfo getUserInfoByUserName(Map<String, Object> map);
	public Integer insertUserInfo(Map<String, Object> map);
	public Integer updateUserInfo(Map<String, Object> map);
	public Integer insertUserAccount(Map<String, Object> map);
	
	//保存用户的token
	public Integer insertWXToken(Map<String, Object> map);
	public Integer updateWXToken(Map<String, Object> map);
	public Integer findWXToken(Map<String, Object> map);
	
	//根据userid 查用户信息
	public UserInfo getUserByID(Integer userid);
}
