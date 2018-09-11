package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetFavGoodsDao;
import com.limon.http.model.FavGoods;
import com.limon.http.service.GetFavGoodsService;

@Service("GetFavGoodsService")
public class GetFavGoodsServiceImpl implements GetFavGoodsService{
	@Autowired
	private GetFavGoodsDao getFavGoodsDao;

	public List<FavGoods> getFavGoods(Map<String, Object> map) {
		return getFavGoodsDao.getFavGoods(map);
	}

	public Integer getFavGoodsCount(Map<String, Object> map) {
		return getFavGoodsDao.getFavGoodsCount(map);
	}

}
