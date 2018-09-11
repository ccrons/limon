package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetShopGoodsBuyNumDao;
import com.limon.http.model.GoodsLimit;
import com.limon.http.service.GetShopGoodsBuyNumService;

@Service("GetShopGoodsBuyNumService")
public class GetShopGoodsBuyNumServiceImpl implements GetShopGoodsBuyNumService{
	@Autowired
	private GetShopGoodsBuyNumDao getShopGoodsBuyNumDao;

	@Override
	public GoodsLimit getBuyNum(Map<String, Object> map) {
		return getShopGoodsBuyNumDao.getBuyNum(map);
	}

	@Override
	public GoodsLimit getLimitNum(Map<String, Object> map) {
		return getShopGoodsBuyNumDao.getLimitNum(map);
	}

	
}
