package com.limon.store.dao;

import java.util.List;
import java.util.Map;

import com.limon.store.model.StoreInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface StoreInfoDao {
    public StoreInfo getStoreByUserNameAndPassword(Map<String,String> map);
    public Integer updatePassword(Map<String,Object> map);
    public Integer getStoreInfoListCount(Map<String,Object> map);
    public List<StoreInfo> getStoreInfoList(Map<String,Object> map);
    public StoreInfo getStoreInfoById(Integer id);
    public Integer addStoreInfo(Map<String,Object> map);
    public Integer updateStoreInfo(Map<String,Object> map);
    public Integer updateStoreInfoStatus(Map<String,Object> map);
    public Integer delStoreInfoById(Integer id);
    public Integer getIsUsedStroeName(String storename);
    public Integer getIsUsedStroeUserName(String storename);
    public Integer addStoreRole(Map<String,Object> map);
    public Integer delStoreRole(Map<String,Object> map);
    public Integer getStoreRole(Map<String,Object> map);
	public Integer StoreEdit(Map<String, Object> map);
	public List<StoreInfo> getStoreInfoListAll();
	public List<StoreInfo> getStoreInfoListOpen();
}