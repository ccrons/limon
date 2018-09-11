package com.limon.store.service;

import java.util.List;
import java.util.Map;

import com.limon.store.model.StoreInfo;


/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface StoreInfoService {
	/**
	 * 根据用户名和密码查询店铺信息
	 * @param map
	 * @return
	 */
    public StoreInfo getStoreByUserNameAndPassword(Map<String,String> map);
    /**
	 * 根据用户ID修改密码
	 * @param map
	 * @return
	 */
    public boolean updatePassword(String userid,String newpassword);
    
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
