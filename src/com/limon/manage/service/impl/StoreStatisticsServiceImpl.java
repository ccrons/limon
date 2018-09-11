package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.StoreStatisticsDao;
import com.limon.manage.service.StoreStatisticsService;
import com.limon.store.model.StoreInfo;

/**
 * 数据统计->便利店统计
 * 项目名称：limon   
 * 类名称：StoreStatisticsServiceImpl
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:46   
 * @version v1.0
 */
@Service("StoreStatisticsService")
public class StoreStatisticsServiceImpl implements StoreStatisticsService {
	@Autowired
	private StoreStatisticsDao storeStatisticsDao;
	/**
	 * 便利店统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getStoreInfoListCount(Map<String, Object> map) {
		return storeStatisticsDao.getStoreInfoListCount(map);
	}
	/**
	 * 便利店统计列表
	 * @param map
	 * @return
	 */
	public List<StoreInfo> getStoreInfoList(Map<String, Object> map) {
		return storeStatisticsDao.getStoreInfoList(map);
	}
	
}