package com.limon.http.service;

import java.util.Map;

import com.limon.http.model.Address;

public interface SetShopAddressService {
	public Integer saveShopAddress(Map<String,Object> map);
	public Integer updateShopAddress(Map<String,Object> map);
	public Address getShopAddressBySidAndUid(Map<String,Object> map);
}
