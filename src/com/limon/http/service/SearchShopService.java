package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Shop;

public interface SearchShopService {
	public List<Shop> searchShop(Map<String, String> map);
}
