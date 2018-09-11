package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Goods;
import com.limon.http.model.GoodsLimit;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetGoodsByTypeDao {
	public List<Goods> getGoodsByType(Map<String, Object> map);
	public Integer getGoodsByTypeCount(Map<String, Object> map);
	public GoodsLimit getTimelimit(Map<String, Object> map);
	public GoodsLimit getAdProduct(Map<String, Object> map);
	public GoodsLimit getBuyNum(Map<String, Object> map);
	List<String> getTimeLimitProduct(Integer pid);
}