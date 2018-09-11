package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.ShopAllGoods;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetAllGoodsByShopDao {
	public List<ShopAllGoods> getGoodsByShop(Map<String, Object> map);
	public Integer getGoodsByShopCount(Map<String, Object> map);
}