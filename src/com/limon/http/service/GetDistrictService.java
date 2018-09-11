package com.limon.http.service;

import java.util.List;

import com.limon.http.model.District;

public interface GetDistrictService {
	public List<District> getDistrictByCity(Integer citycode);
}
