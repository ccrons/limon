package com.limon.manage.dao;

import java.util.List;
import java.util.Map;
import com.limon.manage.model.SysPush;
import com.limon.store.model.StoreInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SysPushDao {
	public List<SysPush> getPushList(Map<String,Object> map);
	public Integer getPushListCount(Map<String,Object> map);
	public SysPush getSysPushById(String id);
	public Integer sysPushAdd(Map<String, Object> map);
	public Integer sysPushDel(String id);
	public List<String> getStoreId();
	public List<String> getAppuserId(Map<String, Object> map);
	public String getProvinceByProvcode(String provcode);
	public String getCityByCitycode(String citycode);
	public String getCountyByCountycode(String countycode);
	public List<StoreInfo> getStoreBycountycode(String countycode);
	public List<String> getStoresId(Map<String, Object> map);
}