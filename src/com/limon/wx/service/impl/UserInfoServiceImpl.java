package com.limon.wx.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.wx.dao.UserInfoDao;
import com.limon.wx.model.UserInfo;
import com.limon.wx.service.UserInfoService;
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public UserInfo getUserInfoByUserName(Map<String, Object> map) {
		return userInfoDao.getUserInfoByUserName(map);
	}
	@Override
	public Integer  insertUserInfo(Map<String, Object> map) {
		return userInfoDao.insertUserInfo(map);
	}
	@Override
	public Integer  updateUserInfo(Map<String, Object> map) {
		return userInfoDao.updateUserInfo(map);
	}
	@Override
	public Integer  insertWXToken(Map<String, Object> map) {
		return userInfoDao.insertWXToken(map);
	}
	@Override
	public Integer  updateWXToken(Map<String, Object> map) {
		return userInfoDao.updateWXToken(map);
	}
	@Override
	public Integer  findWXToken(Map<String, Object> map) {
		return userInfoDao.findWXToken(map);
	}
	 
	@Override
	public Integer insertUserAccount(Map<String, Object> map) {
		return userInfoDao.insertUserAccount(map);
	}
	
	//根据userid 查用户信息
	@Override
	public UserInfo getUserByID(Integer userid) {
		return userInfoDao.getUserByID(userid);
	}
}
