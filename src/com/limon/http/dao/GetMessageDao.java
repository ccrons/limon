package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Message;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetMessageDao {
	public List<Message> getMessageList(Map<String,Object> map);
	public Integer getMessageListCount(Map<String,Object> map);
}