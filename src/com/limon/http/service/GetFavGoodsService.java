package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.FavGoods;

public interface GetFavGoodsService {
	public List<FavGoods> getFavGoods(Map<String, Object> map);
	public Integer getFavGoodsCount(Map<String, Object> map);
}
