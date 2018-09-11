package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetGoodsByTypeDao;
import com.limon.http.model.Goods;
import com.limon.http.model.GoodsLimit;
import com.limon.http.service.GetGoodsByTypeService;

@Service("GetGoodsByTypeService")
public class GetGoodsByTypeServiceImpl implements GetGoodsByTypeService{
	@Autowired
	private GetGoodsByTypeDao getGoodsByTypeDao;

	public List<Goods> getGoodsByType(Map<String, Object> map) {
		return getGoodsByTypeDao.getGoodsByType(map);
	}

	public Integer getGoodsByTypeCount(Map<String, Object> map) {
		return getGoodsByTypeDao.getGoodsByTypeCount(map);
	}

	@Override
	public GoodsLimit getAdProduct(Map<String, Object> map) {
		return getGoodsByTypeDao.getAdProduct(map);
	}

	@Override
	public GoodsLimit getBuyNum(Map<String, Object> map) {
		return getGoodsByTypeDao.getBuyNum(map);
	}

	@Override
	public GoodsLimit getTimelimit(Map<String, Object> map) {
		return getGoodsByTypeDao.getTimelimit(map);
	}

	@Override
	public List<String> getTimeLimitProduct(Integer pid) {
		return getGoodsByTypeDao.getTimeLimitProduct(pid);
	}

}
