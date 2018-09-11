package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.ShopUpdatePwdDao;
import com.limon.http.service.ShopUpdatePwdService;

@Service("ShopUpdatePwdService")
public class ShopUpdatePwdServiceImpl implements ShopUpdatePwdService{
	@Autowired
	private ShopUpdatePwdDao shopUpdatePwdDao;

	public Integer checkShopOldPwd(Map<String, Object> map) {
		return shopUpdatePwdDao.checkShopOldPwd(map);
	}

	public Integer updateShopPwd(Map<String, Object> map) {
		return shopUpdatePwdDao.updateShopPwd(map);
	}

}
