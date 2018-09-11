package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.GoodsLimit;
import com.limon.http.model.SearchGoods;

public interface SearchGoodsService {
	public List<SearchGoods> searchGoods(Map<String, Object> map);
	public Integer searchGoodsCount(Map<String, Object> map);
	public GoodsLimit getTimelimit(Map<String, Object> map);
	public GoodsLimit getAdProduct(Map<String, Object> map);
	public GoodsLimit getBuyNum(Map<String, Object> map);
}
