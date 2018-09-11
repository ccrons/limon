package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetCityDao;
import com.limon.http.model.City;
import com.limon.http.service.GetCityService;

@Service("GetCityService")
public class GetCityServiceImpl implements GetCityService{
	@Autowired
	private GetCityDao getCityDao;

	public List<City> getCityByProvince(Integer provincecode) {
		return getCityDao.getCityByProvince(provincecode);
	}
	
}
