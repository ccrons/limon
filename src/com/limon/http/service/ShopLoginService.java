package com.limon.http.service;

import java.util.Map;

import com.limon.http.model.ShopLogin;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface ShopLoginService {
	public ShopLogin getShopByUserName(Map<String, String> map);
	public ShopLogin getShopByUserNameAndPassword(Map<String, String> map);
}