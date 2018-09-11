package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.SearchGoodsDao;
import com.limon.http.model.GoodsLimit;
import com.limon.http.model.SearchGoods;
import com.limon.http.service.SearchGoodsService;

@Service("SearchGoodsService")
public class SearchGoodsServiceImpl implements SearchGoodsService{
	@Autowired
	private SearchGoodsDao searchGoodsDao;

	public List<SearchGoods> searchGoods(Map<String, Object> map) {
		return searchGoodsDao.searchGoods(map);
	}

	public Integer searchGoodsCount(Map<String, Object> map) {
		return searchGoodsDao.searchGoodsCount(map);
	}

	@Override
	public GoodsLimit getAdProduct(Map<String, Object> map) {
		return searchGoodsDao.getAdProduct(map);
	}

	@Override
	public GoodsLimit getBuyNum(Map<String, Object> map) {
		return searchGoodsDao.getBuyNum(map);
	}

	@Override
	public GoodsLimit getTimelimit(Map<String, Object> map) {
		return searchGoodsDao.getTimelimit(map);
	}

}
