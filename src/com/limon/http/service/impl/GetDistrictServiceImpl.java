package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetDistrictDao;
import com.limon.http.model.District;
import com.limon.http.service.GetDistrictService;

@Service("GetDistrictService")
public class GetDistrictServiceImpl implements GetDistrictService{
	@Autowired
	private GetDistrictDao getDistrictDao;

	public List<District> getDistrictByCity(Integer citycode) {
		return getDistrictDao.getDistrictByCity(citycode);
	}

}
