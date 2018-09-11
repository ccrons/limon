package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.SysPushDao;
import com.limon.manage.model.SysPush;
import com.limon.manage.service.SysPushService;
import com.limon.store.model.StoreInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("SysPushService")
public class SysPushServiceImpl implements SysPushService {
	
	@Autowired
	private SysPushDao sysPushDao;

	@Override
	public List<SysPush> getPushList(Map<String, Object> map) {
		return sysPushDao.getPushList(map);
	}

	@Override
	public Integer getPushListCount(Map<String, Object> map) {
		return sysPushDao.getPushListCount(map);
	}

	@Override
	public SysPush getSysPushById(String id) {
		return sysPushDao.getSysPushById(id);
	}

	@Override
	public Integer sysPushAdd(Map<String, Object> map) {
		return sysPushDao.sysPushAdd(map);
	}

	@Override
	public Integer sysPushDel(String id) {
		return sysPushDao.sysPushDel(id);
	}

	@Override
	public List<String> getAppuserId(Map<String, Object> map) {
		return sysPushDao.getAppuserId(map);
	}

	@Override
	public List<String> getStoreId() {
		return sysPushDao.getStoreId();
	}

	@Override
	public String getCityByCitycode(String citycode) {
		return sysPushDao.getCityByCitycode(citycode);
	}

	@Override
	public String getCountyByCountycode(String countycode) {
		return sysPushDao.getCountyByCountycode(countycode);
	}

	@Override
	public String getProvinceByProvcode(String provcode) {
		return sysPushDao.getProvinceByProvcode(provcode);
	}

	@Override
	public List<StoreInfo> getStoreBycountycode(String countycode) {
		return sysPushDao.getStoreBycountycode(countycode);
	}

	@Override
	public List<String> getStoresId(Map<String, Object> map) {
		return sysPushDao.getStoresId(map);
	}

	
	
}