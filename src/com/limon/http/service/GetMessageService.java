package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Message;

public interface GetMessageService {
	public List<Message> getMessageList(Map<String,Object> map);
	public Integer getMessageListCount(Map<String,Object> map);
}
