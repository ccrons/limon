package com.limon.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.store.dao.AreaCountyDao;
import com.limon.store.model.AreaCounty;
import com.limon.store.service.AreaCountyService;

/**
 * @author gqf
 *
 * 2015-7-2
 */
@Service("AreaCountyService")
public class AreaCountyServiceImpl implements AreaCountyService {
	
	@Autowired
	private AreaCountyDao areaCountyDao;

	public List<AreaCounty> getCountyByCityId(Integer cityid) {
		return areaCountyDao.getCountyByCityId(cityid);
	}

}
