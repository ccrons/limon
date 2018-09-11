package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.SearchShopDao;
import com.limon.http.model.Shop;
import com.limon.http.service.SearchShopService;

@Service("SearchShopService")
public class SearchShopServiceImpl implements SearchShopService{
	@Autowired
	private SearchShopDao searchShopDao;

	public List<Shop> searchShop(Map<String, String> map) {
		return searchShopDao.searchShop(map);
	}

}
