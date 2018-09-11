package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.AddAddressDao;
import com.limon.http.model.Address;
import com.limon.http.service.AddAddressService;

@Service("AddAddressService")
public class AddAddressServiceImpl implements AddAddressService{
	@Autowired
	private AddAddressDao addAddressDao;

	public Integer saveUserAddress(Address addr) {
		return addAddressDao.saveUserAddress(addr);
	}
}
