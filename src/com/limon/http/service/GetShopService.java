package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Shop;

public interface GetShopService {
	public List<Shop> getShopByArea(Map<String,Object> map);
}
