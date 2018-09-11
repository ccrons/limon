package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Goods;
import com.limon.http.model.GoodsLimit;

public interface GetGoodsByTypeService {
	public List<Goods> getGoodsByType(Map<String, Object> map);
	public Integer getGoodsByTypeCount(Map<String, Object> map);
	public GoodsLimit getTimelimit(Map<String, Object> map);
	public GoodsLimit getAdProduct(Map<String, Object> map);
	public GoodsLimit getBuyNum(Map<String, Object> map);
	List<String> getTimeLimitProduct(Integer pid);
}
