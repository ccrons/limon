package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.SysMessage;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SysMessageService {
	public List<SysMessage> getMessageList(Map<String,Object> map);
	public Integer getMessageListCount(Map<String,Object> map);
	public SysMessage getSysMessageById(String id);
	public Integer sysMessageAdd(Map<String, Object> map);
	public Integer sysMessageDel(String id);
}
