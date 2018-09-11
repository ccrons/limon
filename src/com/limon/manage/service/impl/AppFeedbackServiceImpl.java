package com.limon.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.AppFeedbackDao;
import com.limon.manage.model.AppFeedback;
import com.limon.manage.service.AppFeedbackService;

/**
 * @author wn
 *
 * 2015-7-2
 */
@Service("appfeedbackService")
public class AppFeedbackServiceImpl implements AppFeedbackService {
	
	@Autowired
	private AppFeedbackDao appfeedbackDao;
	/**
	 * 查询意见反馈列表
	 * @return
	 */
	public List<AppFeedback> getFeedbackList(Map<String, Object> map) {
		return appfeedbackDao.getFeedbackList(map);
	}
	/**
	 * 查询意见反馈列表数量
	 * @return
	 */
	public Integer getFeedbackListCount(Map<String, Object> map) {
		return appfeedbackDao.getFeedbackListCount(map);
	}
	
}
