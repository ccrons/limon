package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetShopAddressDao;
import com.limon.http.model.Address;
import com.limon.http.service.GetShopAddressService;

@Service("GetShopAddressService")
public class GetShopAddressServiceImpl implements GetShopAddressService{
	@Autowired
	private GetShopAddressDao getShopAddressDao;

	public Address getShopAddressBySidAndUid(Map<String, Object> map) {
		return getShopAddressDao.getShopAddressBySidAndUid(map);
	}

	public Address getShopAddressDefault(Integer sid) {
		return getShopAddressDao.getShopAddressDefault(sid);
	}

}
