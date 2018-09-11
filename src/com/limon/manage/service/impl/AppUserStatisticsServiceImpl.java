package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.AppUserStatisticsDao;
import com.limon.manage.model.AppUser;
import com.limon.manage.service.AppUserStatisticsService;

/**
 * 数据统计->用户统计
 * 项目名称：limon   
 * 类名称：AppUserStatisticsServiceImpl
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:46   
 * @version v1.0
 */
@Service("AppUserStatisticsService")
public class AppUserStatisticsServiceImpl implements AppUserStatisticsService {
	@Autowired
	private AppUserStatisticsDao appUserStatisticsDao;
	/**
	 * AppUser统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getAppUserListCount(Map<String, Object> map) {
		return appUserStatisticsDao.getAppUserListCount(map);
	}
	/**
	 * AppUser统计列表
	 * @param map
	 * @return
	 */
	public List<AppUser> getAppUserList(Map<String, Object> map) {
		return appUserStatisticsDao.getAppUserList(map);
	}
	public Integer getAppUserAllCount() {
		return appUserStatisticsDao.getAppUserAllCount();
	}
	
}