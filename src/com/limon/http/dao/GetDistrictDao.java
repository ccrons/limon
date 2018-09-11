package com.limon.http.dao;

import java.util.List;

import com.limon.http.model.District;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetDistrictDao {
	public List<District> getDistrictByCity(Integer citycode);
}