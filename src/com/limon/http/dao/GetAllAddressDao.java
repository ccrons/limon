package com.limon.http.dao;

import java.util.List;

import com.limon.http.model.Address;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetAllAddressDao {
	public List<Address> getAllAddress(Integer uid);
	public List<Address> getAllAddressName(Integer uid);
}