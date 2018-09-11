package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.FeedBackDao;
import com.limon.http.service.FeedBackService;

@Service("FeedBackService")
public class FeedBackServiceImpl implements FeedBackService{
	@Autowired
	private FeedBackDao feedBackDao;

	public Integer saveFeedBack(Map<String, Object> map) {
		return feedBackDao.saveFeedBack(map);
	}
}
