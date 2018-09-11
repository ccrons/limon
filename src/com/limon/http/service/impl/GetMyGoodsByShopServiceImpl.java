package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetMyGoodsByShopDao;
import com.limon.http.model.ShopMyGoods;
import com.limon.http.model.ShopMyGoodsIndex;
import com.limon.http.service.GetMyGoodsByShopService;

@Service("GetMyGoodsByShopService")
public class GetMyGoodsByShopServiceImpl implements GetMyGoodsByShopService{
	@Autowired
	private GetMyGoodsByShopDao getMyGoodsByShopDao;

	public List<ShopMyGoods> getMyGoodsByShop(Map<String, Object> map) {
		return getMyGoodsByShopDao.getMyGoodsByShop(map);
	}

	public Integer getMyGoodsByShopCount(Map<String, Object> map) {
		return getMyGoodsByShopDao.getMyGoodsByShopCount(map);
	}

	public List<ShopMyGoodsIndex> getMyGoodsRecByShop(Integer sid) {
		return getMyGoodsByShopDao.getMyGoodsRecByShop(sid);
	}

	@Override
	public List<ShopMyGoodsIndex> getMyGoodsFirstByShop(Integer sid) {
		return getMyGoodsByShopDao.getMyGoodsFirstByShop(sid);
	}

	@Override
	public List<ShopMyGoodsIndex> getMyGoodsLuckByShop(Integer sid) {
		return getMyGoodsByShopDao.getMyGoodsLuckByShop(sid);
	}

	@Override
	public List<ShopMyGoodsIndex> getMyGoodsSearchByShop(Map<String, Object> map) {
		return getMyGoodsByShopDao.getMyGoodsSearchByShop(map);
	}
}
