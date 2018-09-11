package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.SetShopAddressDao;
import com.limon.http.model.Address;
import com.limon.http.service.SetShopAddressService;

@Service("SetShopAddressService")
public class SetShopAddressServiceImpl implements SetShopAddressService{
	@Autowired
	private SetShopAddressDao setShopAddressDao;

	public Integer saveShopAddress(Map<String, Object> map) {
		return setShopAddressDao.saveShopAddress(map);
	}

	public Integer updateShopAddress(Map<String, Object> map) {
		return setShopAddressDao.updateShopAddress(map);
	}

	public Address getShopAddressBySidAndUid(Map<String, Object> map) {
		return setShopAddressDao.getShopAddressBySidAndUid(map);
	}
}
