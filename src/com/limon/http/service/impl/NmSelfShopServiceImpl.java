package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.NmSelfShopDao;
import com.limon.http.model.AdInfo;
import com.limon.http.model.SaleInfo;
import com.limon.http.model.SelfShop;
import com.limon.http.service.NmSelfShopService;

@Service("NmSelfShopService")
public class NmSelfShopServiceImpl implements NmSelfShopService{
	@Autowired
	private NmSelfShopDao nmSelfShopDao;

	public SelfShop getNmSelfShop() {
		return nmSelfShopDao.getNmSelfShop();
	}

	public List<AdInfo> getAdInfoList(Integer id) {
		return nmSelfShopDao.getAdInfoList(id);
	}

	public List<SaleInfo> getSaleInfoList(Integer id) {
		return nmSelfShopDao.getSaleInfoList(id);
	}
	
}
