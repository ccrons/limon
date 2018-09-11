package com.limon.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.base.dao.SysConfigDao;
import com.limon.base.model.AUser;
import com.limon.base.model.SysConfig;
import com.limon.base.service.SysConfigService;
import com.limon.http.model.GoodsLimit;
import com.limon.manage.model.Recruit;
import com.limon.manage.model.StoreAdProductInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	
	@Autowired
	private SysConfigDao sysConfigDao;
	public SysConfig getSysConfig(String configKey){
		SysConfig config = sysConfigDao.getSysConfig(configKey);
		return config;
	}
	
	public List<SysConfig> getSysConfigs(Map<String, Object> map) {
		List<SysConfig> configs = sysConfigDao.getSysConfigs(map);
		return configs;
	}

	public Integer getSysConfigsCount(Map<String, Object> map) {
		Integer count = sysConfigDao.getSysConfigsCount(map);
		return count;
	}

	public List<SysConfig> getSysConfigsNoPage(Map<String, Object> map) {
		List<SysConfig> configs = sysConfigDao.getSysConfigsNoPage(map);
		return configs;
	}

	public Integer insertSysConfig(Map<String,Object> map) {
		Integer inte=sysConfigDao.getIsUseSysConfig(map);
		if(inte==0){
			sysConfigDao.insertSysConfig(map);
		}
		return inte;
	}

	public SysConfig getSysConfigById(Map<String,Object> map){
		SysConfig config = sysConfigDao.getSysConfigById(map);
		return config;
	}

	public Integer updateSysConfigById(Map<String,Object> map) {
		Integer inte=sysConfigDao.getIsUseSysConfig(map);
		if(inte==0){
			sysConfigDao.updateSysConfigById(map);
		}
		return inte;
	}

	public Integer deleteSysConfigById(Map<String, Object> map) {
		Integer inte = sysConfigDao.deleteSysConfigById(map);
		return inte;
	}

	public void updateSysConfigByKey(Map<String, Object> map) {
		sysConfigDao.updateSysConfigByKey(map);
	}

	@Override
	public Recruit getRec() {
		return sysConfigDao.getRec();
	}

	@Override
	public List<StoreAdProductInfo> getZero(Integer sid) {
		return sysConfigDao.getZero(sid);
	}

	@Override
	public StoreAdProductInfo getZeroProductByPId(Integer pid,Integer sid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sid",sid);
		map.put("pid",pid);
		return sysConfigDao.getZeroProductByPId(map);
	}

	@Override
	public StoreAdProductInfo getTLimitProductByPId(Integer pid,Integer sid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sid",sid);
		map.put("pid",pid);
		return sysConfigDao.getTLimitProductByPId(map);
	}

	@Override
	public List<StoreAdProductInfo> getTLimit(Integer sid, String tltime) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sid",sid);
		map.put("tltime",tltime);
		return sysConfigDao.getTLimit(map);
	}

	@Override
	public StoreAdProductInfo getStoreProductByPId(Integer pid,Integer sid) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pid",pid);
		map.put("sid",sid);
		return sysConfigDao.getStoreProductByPId(map);
	}

	@Override
	public GoodsLimit getBuyNum(Map<String, Object> map) {
		return sysConfigDao.getBuyNum(map);
	}

	@Override
	public GoodsLimit getLimitNum(Map<String, Object> map) {
		return sysConfigDao.getLimitNum(map);
	}

	@Override
	public List<StoreAdProductInfo> getLuck(Integer sid) {
		return sysConfigDao.getLuck(sid);
	}

	@Override
	public StoreAdProductInfo getLuckProductByPId(Integer pid,Integer sid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sid",sid);
		map.put("pid",pid);
		return sysConfigDao.getLuckProductByPId(map);
	}

	@Override
	public AUser getAppUserById(Integer auid) {
		return sysConfigDao.getAppUserById(auid);
	}

	@Override
	public void changeLuck(Integer auid) {
		sysConfigDao.changeLuck(auid);
	}

}
