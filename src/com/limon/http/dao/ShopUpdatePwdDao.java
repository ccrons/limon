package com.limon.http.dao;

import java.util.Map;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface ShopUpdatePwdDao {
	public Integer updateShopPwd(Map<String,Object> map);
	public Integer checkShopOldPwd(Map<String,Object> map);
}