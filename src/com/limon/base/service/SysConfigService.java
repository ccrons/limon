package com.limon.base.service;

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
public interface SysConfigService {
	/**
	 * 根据配置key查询配置
	 * @param configkey
	 * @return
	 */
	public SysConfig getSysConfig(String configkey);
	/**
	 * 查询配置列表
	 * @param map
	 * @return
	 */
	public List<SysConfig> getSysConfigs(Map<String,Object> map);
	/**
	 * 查询配置数
	 * @param map
	 * @return
	 */
	public Integer getSysConfigsCount(Map<String,Object> map);
	/**
	 * 查询配置列表无分页
	 * @param map
	 * @return
	 */
	public List<SysConfig> getSysConfigsNoPage(Map<String,Object> map);
	/**
	 * 添加配置
	 * @param PropConfig
	 * @return
	 */
	public Integer insertSysConfig(Map<String,Object> map);
	/**
	 * 通过id获取配置 
	 * @param map
	 * @return
	 */
	public SysConfig getSysConfigById(Map<String,Object> map);
	/**
	 * 修改配置
	 * @param PropConfig
	 * @return
	 */
	public Integer updateSysConfigById(Map<String,Object> map);
	/**
	 * 修改配置
	 * @param PropConfig
	 * @return
	 */
	public void updateSysConfigByKey(Map<String,Object> map);
	/**
	 * 删除配置
	 * @param map
	 * @return
	 */
	public Integer deleteSysConfigById(Map<String,Object> map);
	public Recruit getRec();
	public List<StoreAdProductInfo> getZero(Integer sid);
	public StoreAdProductInfo getZeroProductByPId(Integer pid,Integer sid);
	public List<StoreAdProductInfo> getTLimit(Integer sid,String tltime);
	public StoreAdProductInfo getTLimitProductByPId(Integer pid,Integer sid);
	public StoreAdProductInfo getStoreProductByPId(Integer pid,Integer sid);
	public GoodsLimit getBuyNum(Map<String, Object> map);
	public GoodsLimit getLimitNum(Map<String, Object> map);
	public List<StoreAdProductInfo> getLuck(Integer sid);
	public StoreAdProductInfo getLuckProductByPId(Integer pid,Integer sid);
	
	public AUser getAppUserById(Integer auid);
	public void changeLuck(Integer auid);
}
