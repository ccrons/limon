package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.SysVersionDao;
import com.limon.manage.model.SysVersion;
import com.limon.manage.service.SysVersionService;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("SysVersionService")
public class SysVersionServiceImpl implements SysVersionService {
	
	@Autowired
	private SysVersionDao sysVersionDao;

	public SysVersion getSysVersionById(String id) {
		return sysVersionDao.getSysVersionById(id);
	}

	public List<SysVersion> getVersionList(Map<String, Object> map) {
		return sysVersionDao.getVersionList(map);
	}

	public Integer getVersionListCount(Map<String, Object> map) {
		return sysVersionDao.getVersionListCount(map);
	}

	public Integer sysVersionAdd(Map<String, Object> map) {
		return sysVersionDao.sysVersionAdd(map);
	}

	public Integer sysVersionDel(String id) {
		return sysVersionDao.sysVersionDel(id);
	}

	public Integer sysVersionUpdate(Map<String, Object> map) {
		return sysVersionDao.sysVersionUpdate(map);
	}

	public Integer sysVersionUpdateStatus(Map<String,Object> map) {
		return sysVersionDao.sysVersionUpdateStatus(map);
	}
	
}