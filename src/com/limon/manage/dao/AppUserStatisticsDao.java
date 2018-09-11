package com.limon.manage.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.AppUser;

/**
 * 数据统计->用户统计
 * 项目名称：limon   
 * 类名称：AppUserStatisticsDao
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:27:27   
 * @version v1.0
 */
public interface AppUserStatisticsDao {
	/**
	 * AppUser统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getAppUserListCount(Map<String, Object> map);
	/**
	 * AppUser统计列表
	 * @param map
	 * @return
	 */
	public List<AppUser> getAppUserList(Map<String, Object> map);
	/**
	 * AppUser所有用户总数
	 * @param map
	 * @return
	 */
	public Integer getAppUserAllCount();
}
