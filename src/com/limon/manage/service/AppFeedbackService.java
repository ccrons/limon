package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.AppFeedback;


/**
 * @author wn
 *
 * 2015-7-2
 */
public interface AppFeedbackService {
	/**
	 * 查询意见反馈列表
	 * @param map 
	 * @return
	 */
	public List<AppFeedback> getFeedbackList(Map<String, Object> map);
	/**
	 * 查询意见反馈列表数量
	 * @param map
	 * @return
	 */
	public Integer getFeedbackListCount(Map<String, Object> map);
	
	
}
