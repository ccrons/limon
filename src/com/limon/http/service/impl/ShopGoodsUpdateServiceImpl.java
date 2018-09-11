package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.ShopGoodsUpdateDao;
import com.limon.http.service.ShopGoodsUpdateService;

@Service("ShopGoodsUpdateService")
public class ShopGoodsUpdateServiceImpl implements ShopGoodsUpdateService{
	@Autowired
	private ShopGoodsUpdateDao ShopGoodsUpdateDao;

	public void storeProductUpdate(Map<String, Object> map) {
		ShopGoodsUpdateDao.storeProductUpdate(map);
	}

	public void storeProductUpdateNum(Map<String, Object> map) {
		ShopGoodsUpdateDao.storeProductUpdateNum(map);
	}

	public void storeProductUpdatePrice(Map<String, Object> map) {
		ShopGoodsUpdateDao.storeProductUpdatePrice(map);
	}

}
