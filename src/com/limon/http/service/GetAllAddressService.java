package com.limon.http.service;

import java.util.List;

import com.limon.http.model.Address;

public interface GetAllAddressService {
	public List<Address> getAllAddress(Integer uid);
	public List<Address> getAllAddressName(Integer uid);
}
