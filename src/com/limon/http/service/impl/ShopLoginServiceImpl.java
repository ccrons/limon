package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.ShopLoginDao;
import com.limon.http.model.ShopLogin;
import com.limon.http.service.ShopLoginService;

@Service("ShopLoginService")
public class ShopLoginServiceImpl implements ShopLoginService{
	@Autowired
	private ShopLoginDao shopLoginDao;

	public ShopLogin getShopByUserName(Map<String, String> map) {
		return shopLoginDao.getShopByUserName(map);
	}

	public ShopLogin getShopByUserNameAndPassword(Map<String, String> map) {
		return shopLoginDao.getShopByUserNameAndPassword(map);
	}
	
	
}
