package com.limon.http.dao;

import java.util.List;

import com.limon.http.model.Address;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetAddressDao {
	public Address getAddressByAid(Integer aid);	
	public Address getAddressNameByAid(Integer aid);
	public List<Address> getDefaultAddress(Integer uid);
}