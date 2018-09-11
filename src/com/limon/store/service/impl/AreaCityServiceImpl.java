package com.limon.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.store.dao.AreaCityDao;
import com.limon.store.model.AreaCity;
import com.limon.store.service.AreaCityService;

/**
 * @author gqf
 *
 * 2015-7-2
 */
@Service("AreaCityService")
public class AreaCityServiceImpl implements AreaCityService {
	
	@Autowired
	private AreaCityDao areaCityDao;

	public List<AreaCity> getCityByProvId(Integer provid) {
		return areaCityDao.getCityByProvId(provid);
	}

}
