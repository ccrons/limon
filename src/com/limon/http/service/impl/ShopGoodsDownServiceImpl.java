package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.ShopGoodsDownDao;
import com.limon.http.service.ShopGoodsDownService;

@Service("ShopGoodsDownService")
public class ShopGoodsDownServiceImpl implements ShopGoodsDownService{
	@Autowired
	private ShopGoodsDownDao ShopGoodsDownDao;

	public void storeProductDel(String id) {
		ShopGoodsDownDao.storeProductDel(id);
	}
}
