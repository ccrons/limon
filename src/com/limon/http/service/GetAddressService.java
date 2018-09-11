package com.limon.http.service;

import com.limon.http.model.Address;

public interface GetAddressService {
	public Address getAddressByAid(Integer aid);
	public Address getDefaultAddress(Integer uid);
	public Address getAddressNameByAid(Integer aid);
}
