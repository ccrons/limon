package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetAllGoodsByShopDao;
import com.limon.http.model.ShopAllGoods;
import com.limon.http.service.GetAllGoodsByShopService;

@Service("GetAllGoodsByShopService")
public class GetAllGoodsByShopServiceImpl implements GetAllGoodsByShopService{
	@Autowired
	private GetAllGoodsByShopDao getAllGoodsByShopDao;

	public List<ShopAllGoods> getGoodsByShop(Map<String, Object> map) {
		return getAllGoodsByShopDao.getGoodsByShop(map);
	}

	public Integer getGoodsByShopCount(Map<String, Object> map) {
		return getAllGoodsByShopDao.getGoodsByShopCount(map);
	}

}
