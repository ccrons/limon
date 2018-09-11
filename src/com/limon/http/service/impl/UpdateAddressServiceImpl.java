package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.UpdateAddressDao;
import com.limon.http.model.Address;
import com.limon.http.service.UpdateAddressService;

@Service("UpdateAddressService")
public class UpdateAddressServiceImpl implements UpdateAddressService{
	@Autowired
	private UpdateAddressDao updateAddressDao;

	public Integer updateUserAddress(Address addr) {
		return updateAddressDao.updateUserAddress(addr);
	}
}
