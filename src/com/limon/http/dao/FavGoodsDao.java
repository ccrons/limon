package com.limon.http.dao;

import java.util.Map;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface FavGoodsDao {
	public Integer favGoods(Map<String, Object> map);
	public Integer getFavGoods(Map<String, Object> map);
	public Integer getCartNum(Integer userid);
}