package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.store.model.StoreInfo;


/**
 * 数据统计->便利店统计
 * 项目名称：limon   
 * 类名称：StoreStatisticsService
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:20   
 * @version v1.0
 */
public interface StoreStatisticsService {
	
	/**
	 * 便利店统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getStoreInfoListCount(Map<String, Object> map);
	/**
	 * 便利店统计列表
	 * @param map
	 * @return
	 */
	public List<StoreInfo> getStoreInfoList(Map<String, Object> map);
	
}
