package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Shop;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetNearShopDao {
	public List<Shop> getNearShop(Map<String, String> map);
}