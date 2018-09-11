package com.limon.store.dao;

import java.util.List;
import java.util.Map;

import com.limon.store.model.StoreAdInfo;
import com.limon.store.model.StoreDiscount;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SelfStoreDao {
	public Integer getSaleInfoListCount(Map<String,Object> map);
    
    public List<StoreDiscount> getSaleInfoList(Map<String,Object> map);
    
    public StoreDiscount getSaleInfoById(Integer id);
    
    public Integer addSaleInfo(Map<String,Object> map);
    
    public Integer updateSaleInfo(Map<String,Object> map);
    
    public Integer delSaleInfoById(Integer id);
    
    
    
    public Integer getAdInfoListCount(Map<String,Object> map);
    
    public List<StoreAdInfo> getAdInfoList(Map<String,Object> map);
    
    public StoreAdInfo getAdInfoById(Integer id);
    
    public Integer addAdInfo(Map<String,Object> map);
    
    public Integer updateAdInfo(Map<String,Object> map);
    
    public Integer delAdInfoById(Integer id);
}