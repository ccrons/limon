package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.CarGoods;

public interface GetCarGoodsService {
	public List<CarGoods> getCarGoods(Map<String, Object> map);
}
