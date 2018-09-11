package com.limon.manage.service;

import java.util.List;
import java.util.Map;
import com.limon.manage.model.SysVersion;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SysVersionService {
	public List<SysVersion> getVersionList(Map<String,Object> map);
	public Integer getVersionListCount(Map<String,Object> map);
	public SysVersion getSysVersionById(String id);
	public Integer sysVersionAdd(Map<String, Object> map);
	public Integer sysVersionUpdate(Map<String, Object> map);
	public Integer sysVersionDel(String id);
	public Integer sysVersionUpdateStatus(Map<String,Object> map);
}
