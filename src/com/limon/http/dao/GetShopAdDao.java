package com.limon.http.dao;

import java.util.List;

import com.limon.http.model.ShopAd;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetShopAdDao {
	public List<ShopAd> getShopAdBySid(Integer aid);
}