package com.limon.base.dao;

import java.util.List;
import java.util.Map;

import com.limon.base.model.AUser;
import com.limon.base.model.SysConfig;
import com.limon.http.model.GoodsLimit;
import com.limon.manage.model.Recruit;
import com.limon.manage.model.StoreAdProductInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SysConfigDao {
	public SysConfig getSysConfig(String configkey);
	public Integer getIsUseSysConfig(Map<String,Object> map);
	public List<SysConfig> getSysConfigs(Map<String,Object> map);
	public Integer getSysConfigsCount(Map<String,Object> map);
	public List<SysConfig> getSysConfigsNoPage(Map<String,Object> map);
	public Integer insertSysConfig(Map<String,Object> map);
	public SysConfig getSysConfigById(Map<String,Object> map);
	public Integer updateSysConfigById(Map<String,Object> map);
	public Integer deleteSysConfigById(Map<String,Object> map);
	public void updateSysConfigByKey(Map<String, Object> map);
	public Recruit getRec();
	public List<StoreAdProductInfo> getZero(Integer sid);
	public StoreAdProductInfo getZeroProductByPId(Map<String,Object> map);
	public List<StoreAdProductInfo> getLuck(Integer sid);
	public StoreAdProductInfo getLuckProductByPId(Map<String,Object> map);
	public List<StoreAdProductInfo> getTLimit(Map<String, Object> map);
	public StoreAdProductInfo getTLimitProductByPId(Map<String,Object> map);
	public StoreAdProductInfo getStoreProductByPId(Map<String,Object> map);
	public GoodsLimit getBuyNum(Map<String, Object> map);
	public GoodsLimit getLimitNum(Map<String, Object> map);
	public AUser getAppUserById(Integer auid);
	public void changeLuck(Integer auid);
}
