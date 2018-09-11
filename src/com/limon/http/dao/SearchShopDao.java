package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Shop;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SearchShopDao {
	public List<Shop> searchShop(Map<String, String> map);
}