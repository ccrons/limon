package com.limon.http.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetShopInfoDao;
import com.limon.http.model.Shop;
import com.limon.http.service.GetShopInfoService;

@Service("GetShopInfoService")
public class GetShopInfoServiceImpl implements GetShopInfoService{
	@Autowired
	private GetShopInfoDao getShopInfoDao;

	public Shop getShopById(Integer storeid) {
		return getShopInfoDao.getShopById(storeid);
	}

}
