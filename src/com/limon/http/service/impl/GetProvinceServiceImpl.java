package com.limon.http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetProvinceDao;
import com.limon.http.model.Province;
import com.limon.http.service.GetProvinceService;

@Service("GetProvinceService")
public class GetProvinceServiceImpl implements GetProvinceService{
	@Autowired
	private GetProvinceDao getProvinceDao;

	public List<Province> getAllProvince() {
		return getProvinceDao.getAllProvince();
	}

}
