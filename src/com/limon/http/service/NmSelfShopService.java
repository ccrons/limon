package com.limon.http.service;

import java.util.List;

import com.limon.http.model.AdInfo;
import com.limon.http.model.SaleInfo;
import com.limon.http.model.SelfShop;

public interface NmSelfShopService {
	public SelfShop getNmSelfShop();
	public List<SaleInfo> getSaleInfoList(Integer id);
	public List<AdInfo> getAdInfoList(Integer id);
}
