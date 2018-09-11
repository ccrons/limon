package com.limon.http.dao;

import java.util.Map;

import com.limon.http.model.GoodsLimit;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetShopGoodsBuyNumDao {
	public GoodsLimit getBuyNum(Map<String, Object> map);
	public GoodsLimit getLimitNum(Map<String, Object> map);
}