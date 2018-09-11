package com.limon.http.service;

import java.util.List;

import com.limon.http.model.ShopAd;

public interface GetShopAdService {
	public List<ShopAd> getShopAdBySid(Integer aid);
}
