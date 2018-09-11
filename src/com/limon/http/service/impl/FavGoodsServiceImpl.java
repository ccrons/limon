package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.FavGoodsDao;
import com.limon.http.service.FavGoodsService;

@Service("FavGoodsService")
public class FavGoodsServiceImpl implements FavGoodsService{
	@Autowired
	private FavGoodsDao FavGoodsDao;

	public Integer favGoods(Map<String, Object> map) {
		return FavGoodsDao.favGoods(map);
	}

	public Integer getFavGoods(Map<String, Object> map) {
		return FavGoodsDao.getFavGoods(map);
	}

	@Override
	public Integer getCartNum(Integer userid) {
		return FavGoodsDao.getCartNum(userid);
	}

}
