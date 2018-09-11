package com.limon.http.dao;

import java.util.List;

import com.limon.http.model.AdInfo;
import com.limon.http.model.SaleInfo;
import com.limon.http.model.SelfShop;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface NmSelfShopDao {
	public SelfShop getNmSelfShop();
	public List<SaleInfo> getSaleInfoList(Integer id);
	public List<AdInfo> getAdInfoList(Integer id);
}