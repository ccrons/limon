package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.base.model.SysMenu;
import com.limon.manage.model.SysRole;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface SysRoleService {
	public List<SysRole> getRoleList(Map<String,Object> map);
	public Integer getRoleListCount(Map<String,Object> map);
	public SysRole getSysRoleById(String id);
	public Integer sysRoleAdd(Map<String, Object> map);
	public Integer sysRoleUpdate(Map<String, Object> map);
	public Integer sysRoleDel(String id);
	public Integer getUserRoleCount(String id);
	public List<SysMenu> getMenuList(String menuid);
	public Integer sysRoleMenuAdd(Map<String, Object> map);
	public Integer sysRoleMenuDel(String roleid);
}
