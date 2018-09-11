package com.limon.wx.dao;

import java.util.Map;

import com.limon.wx.model.WxConfig;


/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface WxConfigDao {
	public WxConfig getComponent_verify_ticket(String cname);
	public WxConfig getComponent_access_token(String cname);
	public WxConfig getAuthorizer_access_token(Map<String,Object> map);
	public WxConfig getPre_auth_code(String canme);
	public void updateComponent_verify_ticket(Map<String,Object> map);
	public void updateComponent_access_token(Map<String,Object> map);
	public void updateAuthorizer_access_token(Map<String,Object> map);
	public void updatePre_auth_code(Map<String,Object> map);
	public void insertAuthorizer_access_token(Map<String,Object> map);
}
