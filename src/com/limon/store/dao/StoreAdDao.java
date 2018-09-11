package com.limon.store.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.manage.model.TimeLimit;
import com.limon.store.model.StoreAd;

/**
 * @author wn
 *
 * 2015-9-1 下午9:21:56
 */
public interface StoreAdDao {

	public List<StoreAd> getStoreAdList(Map<String, Object> map);

	public Integer getStoreAdListCount(Map<String, Object> map);

	public StoreAd getStoreAd(String id);

	public void storeAdAdd(Map<String, Object> map);

	public void storeAdUpdate(Map<String, Object> map);

	public void storeAdDel(String id);

	public void storeAdUp(Map<String, Object> map);

	public Integer getStoreAdUpCount(Map<String, Object> map);

	public List<StoreAd> getStoreAdListAll();

	public StoreAdProduct getStoreAdProductByProductId(String id);

	public void storeAdProductSave(Map<String, Object> map);

	public void storeAdProductDel(Map<String, Object> map);

	public TimeLimit getTimeLimitByProductId(String id);

	public void timeLimitupdate(Map<String, Object> map);

	public void timeLimitSave(Map<String, Object> map);

	public void timeLimitdel(Map<String, Object> map);
	
	public List<StoreAdProductInfo> getStoreAdProductByStoreId(Integer sid);
	public StoreAdProductInfo getStoreAdProductByPId(Map<String, Object> map);
	List<String> getTimeLimitProduct(Integer pid);
	public String getGoodsSalePrice(Map<String, Object> map);

	public List<StoreAdProductInfo> getStoreAdProductByStoreIdMap(Map<String, Object> map);
}