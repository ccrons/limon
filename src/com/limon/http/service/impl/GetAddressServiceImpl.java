package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetAddressDao;
import com.limon.http.model.Address;
import com.limon.http.service.GetAddressService;

@Service("GetAddressService")
public class GetAddressServiceImpl implements GetAddressService{
	@Autowired
	private GetAddressDao getAddressDao;

	public Address getAddressByAid(Integer aid) {
		return getAddressDao.getAddressByAid(aid);
	}

	@Override
	public Address getDefaultAddress(Integer uid) {
		Address a=null;
		List<Address> list=getAddressDao.getDefaultAddress(uid);
		for(Address addr:list){
			if(addr.getIsdefault().equals("1")){
				a=addr;
			}
		}
		if(list.size()>0&&a==null){
			a=list.get(0);
		}
		return a;
	}

	@Override
	public Address getAddressNameByAid(Integer aid) {
		return getAddressDao.getAddressNameByAid(aid);
	}
	
}
