package com.limon.wx.service;


import com.limon.wx.model.WxConfig;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface WxConfigService {
	public WxConfig getComponent_verify_ticket(); 
	public WxConfig getComponent_access_token();
	public WxConfig getAuthorizer_access_token(String appid);
	public WxConfig getPre_auth_code();

	public void updateComponent_verify_ticket(String ticket);
	public void updateComponent_access_token(String component_access_token,long expires_in);
	public void updateAuthorizer_access_token(String cvalue,String expires_in,String appid);
	public void updatePre_auth_code(String pre_auth_code,long expires_in);
	
	public void insertAuthorizer_access_token(String cvalue,String expires_in,String appid);
	
	public void updateAccess_token(String cvalue);
	public WxConfig getAccess_token();
}
