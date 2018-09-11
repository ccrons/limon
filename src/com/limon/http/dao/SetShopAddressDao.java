package com.limon.http.dao;

import java.util.Map;

import com.limon.http.model.Address;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SetShopAddressDao {
	public Integer saveShopAddress(Map<String,Object> map);
	public Integer updateShopAddress(Map<String,Object> map);
	public Address getShopAddressBySidAndUid(Map<String,Object> map);
}