package com.limon.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.store.dao.AreaProvinceDao;
import com.limon.store.model.AreaProvince;
import com.limon.store.service.AreaProvinceService;

/**
 * @author gqf
 *
 * 2015-7-2
 */
@Service("AreaProvinceService")
public class AreaProviceServiceImpl implements AreaProvinceService {
	
	@Autowired
	private AreaProvinceDao areaProvinceDao;

	public List<AreaProvince> getProvinceListAll() {
		return areaProvinceDao.getProvinceListAll();
	}
	
}
