package com.limon.http.service;

import java.util.Map;

import com.limon.http.model.Address;

public interface GetShopAddressService {
	public Address getShopAddressBySidAndUid(Map<String,Object> map);
	public Address getShopAddressDefault(Integer sid);
}
