package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.GoodsLimit;
import com.limon.http.model.SearchGoods;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SearchGoodsDao {
	public List<SearchGoods> searchGoods(Map<String, Object> map);
	public Integer searchGoodsCount(Map<String, Object> map);
	public GoodsLimit getTimelimit(Map<String, Object> map);
	public GoodsLimit getAdProduct(Map<String, Object> map);
	public GoodsLimit getBuyNum(Map<String, Object> map);
}