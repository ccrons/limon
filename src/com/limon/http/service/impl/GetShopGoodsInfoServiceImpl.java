package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetShopGoodsInfoDao;
import com.limon.http.model.ShopGoods;
import com.limon.http.service.GetShopGoodsInfoService;

@Service("GetShopGoodsInfoService")
public class GetShopGoodsInfoServiceImpl implements GetShopGoodsInfoService{
	@Autowired
	private GetShopGoodsInfoDao getShopGoodsInfoDao;

	public ShopGoods getShopGoodsInfo(Integer gid) {
		return getShopGoodsInfoDao.getShopGoodsInfo(gid);
	}

}
