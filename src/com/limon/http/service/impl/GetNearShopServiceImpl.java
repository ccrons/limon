package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetNearShopDao;
import com.limon.http.model.Shop;
import com.limon.http.service.GetNearShopService;

@Service("GetNearShopService")
public class GetNearShopServiceImpl implements GetNearShopService{
	@Autowired
	private GetNearShopDao getNearShopDao;

	public List<Shop> getNearShop(Map<String, String> map) {
		return getNearShopDao.getNearShop(map);
	}

}
