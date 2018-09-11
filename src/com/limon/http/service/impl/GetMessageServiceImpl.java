package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetMessageDao;
import com.limon.http.model.Message;
import com.limon.http.service.GetMessageService;

@Service("GetMessageService")
public class GetMessageServiceImpl implements GetMessageService{
	@Autowired
	private GetMessageDao getMessageDao;

	public List<Message> getMessageList(Map<String,Object> map) {
		return getMessageDao.getMessageList(map);
	}

	public Integer getMessageListCount(Map<String, Object> map) {
		return getMessageDao.getMessageListCount(map);
	}

}
