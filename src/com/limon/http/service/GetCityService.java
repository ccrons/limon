package com.limon.http.service;

import java.util.List;

import com.limon.http.model.City;

public interface GetCityService {
	public List<City> getCityByProvince(Integer provincecode);
}
