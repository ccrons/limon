package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.GoodsInfo;

public interface GetGoodsInfoService {
	public GoodsInfo getGoodsInfo(Map<String, String> map);
	public Integer getIsFav(Map<String, String> map);
	List<String> getTimeLimitProduct(Integer pid);
}
