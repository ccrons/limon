package com.limon.store.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.manage.model.TimeLimit;
import com.limon.store.dao.StoreAdDao;
import com.limon.store.model.StoreAd;
import com.limon.store.service.StoreAdService;

/**
 * @author wn
 *
 * 2015-9-1 下午9:21:56
 */
@Service("StoreAdService")
public class StoreAdServiceImpl implements StoreAdService {
	
	@Autowired
	private StoreAdDao storeAdDao;

	public List<StoreAd> getStoreAdList(Map<String, Object> map) {
		return storeAdDao.getStoreAdList(map);
	}

	public Integer getStoreAdListCount(Map<String, Object> map) {
		return storeAdDao.getStoreAdListCount(map);
	}

	public StoreAd getStoreAd(String id) {
		return storeAdDao.getStoreAd(id);
	}

	public void storeAdAdd(Map<String, Object> map) {
		storeAdDao.storeAdAdd(map);
	}

	public void storeAdUpdate(Map<String, Object> map) {
		storeAdDao.storeAdUpdate(map);
	}

	public void storeAdDel(String id) {
		storeAdDao.storeAdDel(id);
	}

	public void storeAdUp(Map<String, Object> map) {
		storeAdDao.storeAdUp(map);
	}

	public Integer getStoreAdUpCount(Map<String, Object> map) {
		return storeAdDao.getStoreAdUpCount(map);
	}

	public List<StoreAd> getStoreAdListAll() {
		return storeAdDao.getStoreAdListAll();
	}

	public StoreAdProduct getStoreAdProductByProductId(String id) {
		return storeAdDao.getStoreAdProductByProductId(id);
	}

	public void storeAdProductDel(Map<String, Object> map) {
		storeAdDao.storeAdProductDel(map);
	}

	public void storeAdProductSave(Map<String, Object> map) {
		storeAdDao.storeAdProductSave(map);
	}

	public TimeLimit getTimeLimitByProductId(String id) {
		return storeAdDao.getTimeLimitByProductId(id);
	}

	public void timeLimitupdate(Map<String, Object> map) {
		storeAdDao.timeLimitupdate(map);
	}

	public void timeLimitSave(Map<String, Object> map) {
		storeAdDao.timeLimitSave(map);
	}

	public void timeLimitdel(Map<String, Object> map) {
		storeAdDao.timeLimitdel(map);
	}

	@Override
	public List<StoreAdProductInfo> getStoreAdProductByStoreId(Integer sid) {
		return storeAdDao.getStoreAdProductByStoreId(sid);
	}

	@Override
	public StoreAdProductInfo getStoreAdProductByPId(Map<String, Object> map) {
		return storeAdDao.getStoreAdProductByPId(map);
	}

	@Override
	public List<String> getTimeLimitProduct(Integer pid) {
		return storeAdDao.getTimeLimitProduct(pid);
	}

	@Override
	public String getGoodsSalePrice(Map<String, Object> map) {
		return storeAdDao.getGoodsSalePrice(map);
	}

	@Override
	public List<StoreAdProductInfo> getStoreAdProductByStoreIdMap(
			Map<String, Object> map) {
		return storeAdDao.getStoreAdProductByStoreIdMap(map);
	}

	
}