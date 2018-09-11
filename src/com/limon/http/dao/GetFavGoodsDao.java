package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.FavGoods;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetFavGoodsDao {
	public List<FavGoods> getFavGoods(Map<String, Object> map);
	public Integer getFavGoodsCount(Map<String, Object> map);
}