package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetAllAddressDao;
import com.limon.http.model.Address;
import com.limon.http.service.GetAllAddressService;

@Service("GetAllAddressService")
public class GetAllAddressServiceImpl implements GetAllAddressService{
	@Autowired
	private GetAllAddressDao getAllAddressDao;

	public List<Address> getAllAddress(Integer uid) {
		return getAllAddressDao.getAllAddress(uid);
	}

	@Override
	public List<Address> getAllAddressName(Integer uid) {
		return getAllAddressDao.getAllAddressName(uid);
	}
}
