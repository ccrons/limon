package com.limon.http.dao;

import com.limon.http.model.Shop;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetShopInfoDao {
	public Shop getShopById(Integer storeid);
}