package com.limon.store.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.store.dao.SelfStoreDao;
import com.limon.store.model.StoreAdInfo;
import com.limon.store.model.StoreDiscount;
import com.limon.store.service.SelfStoreService;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("SelfStoreService")
public class SelfStoreServiceImpl implements SelfStoreService {
	
	@Autowired
	private SelfStoreDao selfStoreDao;

	public Integer addAdInfo(Map<String, Object> map) {
		return selfStoreDao.addAdInfo(map);
	}

	public Integer addSaleInfo(Map<String, Object> map) {
		return selfStoreDao.addSaleInfo(map);
	}

	public Integer delAdInfoById(Integer id) {
		return selfStoreDao.delAdInfoById(id);
	}

	public Integer delSaleInfoById(Integer id) {
		return selfStoreDao.delSaleInfoById(id);
	}

	public StoreAdInfo getAdInfoById(Integer id) {
		return selfStoreDao.getAdInfoById(id);
	}

	public List<StoreAdInfo> getAdInfoList(Map<String, Object> map) {
		return selfStoreDao.getAdInfoList(map);
	}

	public Integer getAdInfoListCount(Map<String, Object> map) {
		return selfStoreDao.getAdInfoListCount(map);
	}

	public StoreDiscount getSaleInfoById(Integer id) {
		return selfStoreDao.getSaleInfoById(id);
	}

	public List<StoreDiscount> getSaleInfoList(Map<String, Object> map) {
		return selfStoreDao.getSaleInfoList(map);
	}

	public Integer getSaleInfoListCount(Map<String, Object> map) {
		return selfStoreDao.getSaleInfoListCount(map);
	}

	public Integer updateAdInfo(Map<String, Object> map) {
		return selfStoreDao.updateAdInfo(map);
	}

	public Integer updateSaleInfo(Map<String, Object> map) {
		return selfStoreDao.updateSaleInfo(map);
	}

	
}