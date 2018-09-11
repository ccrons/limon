package com.limon.http.dao;

import java.util.List;

import com.limon.http.model.City;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetCityDao {
	public List<City> getCityByProvince(Integer provincecode);
}