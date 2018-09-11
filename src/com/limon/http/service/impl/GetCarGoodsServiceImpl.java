package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetCarGoodsDao;
import com.limon.http.model.CarGoods;
import com.limon.http.service.GetCarGoodsService;

@Service("GetCarGoodsService")
public class GetCarGoodsServiceImpl implements GetCarGoodsService{
	@Autowired
	private GetCarGoodsDao getCarGoodsDao;

	public List<CarGoods> getCarGoods(Map<String, Object> map) {
		return getCarGoodsDao.getCarGoods(map);
	}
	
}
