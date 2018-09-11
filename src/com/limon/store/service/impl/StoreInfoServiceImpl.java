package com.limon.store.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.store.dao.StoreInfoDao;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.StoreInfoService;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("StoreInfoService")
public class StoreInfoServiceImpl implements StoreInfoService {
	
	@Autowired
	private StoreInfoDao storeInfoDao;

	public StoreInfo getStoreByUserNameAndPassword(Map<String, String> map) {
		StoreInfo user=storeInfoDao.getStoreByUserNameAndPassword(map);
		return user;
	}

	public boolean updatePassword(String userid, String newpassword) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("newpassword", newpassword);
		Integer r=storeInfoDao.updatePassword(map);
		if(r>0){
			return true;
		}else{
			return false;
		}
	}

	public Integer addStoreInfo(Map<String, Object> map) {
		return storeInfoDao.addStoreInfo(map);
	}

	public Integer delStoreInfoById(Integer id) {
		return storeInfoDao.delStoreInfoById(id);
	}

	public Integer getIsUsedStroeName(String storename) {
		return storeInfoDao.getIsUsedStroeName(storename);
	}
	
	public Integer getIsUsedStroeUserName(String storename) {
		return storeInfoDao.getIsUsedStroeUserName(storename);
	}

	public StoreInfo getStoreInfoById(Integer id) {
		return storeInfoDao.getStoreInfoById(id);
	}

	public List<StoreInfo> getStoreInfoList(Map<String, Object> map) {
		return storeInfoDao.getStoreInfoList(map);
	}

	public Integer getStoreInfoListCount(Map<String, Object> map) {
		return storeInfoDao.getStoreInfoListCount(map);
	}

	public Integer updateStoreInfo(Map<String, Object> map) {
		return storeInfoDao.updateStoreInfo(map);
	}

	public Integer updateStoreInfoStatus(Map<String, Object> map) {
		return storeInfoDao.updateStoreInfoStatus(map);
	}

	public Integer addStoreRole(Map<String, Object> map) {
		return storeInfoDao.addStoreRole(map);
	}

	public Integer delStoreRole(Map<String, Object> map) {
		return storeInfoDao.delStoreRole(map);
	}

	public Integer getStoreRole(Map<String, Object> map) {
		return storeInfoDao.getStoreRole(map);
	}

	public Integer StoreEdit(Map<String, Object> map) {
		return storeInfoDao.StoreEdit(map);
	}

	public List<StoreInfo> getStoreInfoListAll() {
		return storeInfoDao.getStoreInfoListAll();
	}

	@Override
	public List<StoreInfo> getStoreInfoListOpen() {
		return storeInfoDao.getStoreInfoListOpen();
	}
	
}