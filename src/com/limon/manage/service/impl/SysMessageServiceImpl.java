package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.SysMessageDao;
import com.limon.manage.model.SysMessage;
import com.limon.manage.service.SysMessageService;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("SysMessageService")
public class SysMessageServiceImpl implements SysMessageService {
	
	@Autowired
	private SysMessageDao sysMessageDao;

	public List<SysMessage> getMessageList(Map<String, Object> map) {
		return sysMessageDao.getMessageList(map);
	}

	public Integer getMessageListCount(Map<String, Object> map) {
		return sysMessageDao.getMessageListCount(map);
	}

	public SysMessage getSysMessageById(String id) {
		return sysMessageDao.getSysMessageById(id);
	}

	public Integer sysMessageAdd(Map<String, Object> map) {
		return sysMessageDao.sysMessageAdd(map);
	}

	public Integer sysMessageDel(String id) {
		return sysMessageDao.sysMessageDel(id);
	}

	
}