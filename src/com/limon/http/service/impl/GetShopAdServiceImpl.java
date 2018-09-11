package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetShopAdDao;
import com.limon.http.model.ShopAd;
import com.limon.http.service.GetShopAdService;

@Service("GetShopAdService")
public class GetShopAdServiceImpl implements GetShopAdService{
	@Autowired
	private GetShopAdDao getShopAdDao;

	public List<ShopAd> getShopAdBySid(Integer aid) {
		return getShopAdDao.getShopAdBySid(aid);
	}

}
