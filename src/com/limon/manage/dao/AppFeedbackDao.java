package com.limon.manage.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.AppFeedback;

/**
 * @author wn
 *
 * 2015-7-2
 */
public interface AppFeedbackDao {
	/**
	 * 查询意见反馈列表
	 * @param map 
	 * @return
	 */
	public List<AppFeedback> getFeedbackList(Map<String, Object> map);
	/**
	 * 查询意见反馈列表数量
	 * @param map 
	 * @return Integer
	 */
	public Integer getFeedbackListCount(Map<String, Object> map);
	
}
