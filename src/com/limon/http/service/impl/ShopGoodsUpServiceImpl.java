package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.ShopGoodsUpDao;
import com.limon.http.service.ShopGoodsUpService;
import com.limon.manage.model.ProductInfo;

@Service("ShopGoodsUpService")
public class ShopGoodsUpServiceImpl implements ShopGoodsUpService{
	@Autowired
	private ShopGoodsUpDao ShopGoodsUpDao;

	public Integer getStoreProductCount(Map<String, Object> map) {
		return ShopGoodsUpDao.getStoreProductCount(map);
	}

	public void storeProductAdd(Map<String, Object> map) {
		ShopGoodsUpDao.storeProductAdd(map);
	}

	public ProductInfo getProductInfo(Map<String, Object> map) {
		return ShopGoodsUpDao.getProductInfo(map);
	}

}
