package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetShopDao;
import com.limon.http.model.Shop;
import com.limon.http.service.GetShopService;

@Service("GetShopService")
public class GetShopServiceImpl implements GetShopService{
	@Autowired
	private GetShopDao getShopDao;

	public List<Shop> getShopByArea(Map<String, Object> map) {
		return getShopDao.getShopByArea(map);
	}

}
