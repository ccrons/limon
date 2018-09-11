package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.GoodsInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetGoodsInfoDao {
	public GoodsInfo getGoodsInfo(Map<String, String> map);
	public Integer getIsFav(Map<String, String> map);
	List<String> getTimeLimitProduct(Integer pid);
}