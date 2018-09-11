package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetGoodsInfoDao;
import com.limon.http.model.GoodsInfo;
import com.limon.http.service.GetGoodsInfoService;

@Service("GetGoodsInfoService")
public class GetGoodsInfoServiceImpl implements GetGoodsInfoService{
	@Autowired
	private GetGoodsInfoDao getGoodsInfoDao;

	public GoodsInfo getGoodsInfo(Map<String, String> map) {
		return getGoodsInfoDao.getGoodsInfo(map);
	}

	public Integer getIsFav(Map<String, String> map) {
		return getGoodsInfoDao.getIsFav(map);
	}

	@Override
	public List<String> getTimeLimitProduct(Integer pid) {
		return getGoodsInfoDao.getTimeLimitProduct(pid);
	}

}
