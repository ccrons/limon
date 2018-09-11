package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Shop;

public interface GetNearShopService {
	public List<Shop> getNearShop(Map<String, String> map);
}
