package com.limon.wx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.util.DateUtil;
import com.limon.wx.dao.WxConfigDao;
import com.limon.wx.model.WxConfig;
import com.limon.wx.service.WxConfigService;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
@Service("wxConfigService")
public class WxConfigServiceImpl implements WxConfigService {
	
	@Autowired
	private WxConfigDao wxConfigDao;

	@Override
	public WxConfig getAuthorizer_access_token(String appid) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appid", appid);
		map.put("cname", "AUTHORIZER_ACCESS_TOKEN");
		return wxConfigDao.getAuthorizer_access_token(map);
	}

	@Override
	public WxConfig getComponent_access_token() {
		return wxConfigDao.getComponent_access_token("COMPONENT_ACCESS_TOKEN");
	}

	@Override
	public WxConfig getComponent_verify_ticket() {
		return wxConfigDao.getComponent_verify_ticket("COMPONENT_VERIFY_TICKET");
	}

	@Override
	public WxConfig getPre_auth_code() {
		return wxConfigDao.getPre_auth_code("PRE_AUTH_CODE");
	}

	@Override
	public void updateAuthorizer_access_token(String cvalue,String expires_in,String appid) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appid", appid);
		map.put("cname", "AUTHORIZER_ACCESS_TOKEN");
		map.put("cvalue", cvalue);
		map.put("expires_in", Long.parseLong(expires_in));
		wxConfigDao.updateAuthorizer_access_token(map);
	}

	@Override
	public void updateComponent_access_token(String component_access_token,long expires_in) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cname", "COMPONENT_ACCESS_TOKEN");
		map.put("cvalue", component_access_token);
		map.put("expires_in", expires_in);
		wxConfigDao.updateComponent_access_token(map);
	}

	@Override
	public void updateComponent_verify_ticket(String ticket) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cname", "COMPONENT_VERIFY_TICKET");
		map.put("cvalue", ticket);
		wxConfigDao.updateComponent_verify_ticket(map);
	}

	@Override
	public void updatePre_auth_code(String pre_auth_code,long expires_in) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cname", "PRE_AUTH_CODE");
		map.put("cvalue", pre_auth_code);
		map.put("expires_in", expires_in);
		map.put("createtime",DateUtil.getTodayTime());
		wxConfigDao.updatePre_auth_code(map);
	}

	@Override
	public void insertAuthorizer_access_token(String cvalue,String expires_in,String appid) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cname", "AUTHORIZER_ACCESS_TOKEN");
		map.put("cvalue", cvalue);
		map.put("expires_in", expires_in);
		map.put("appid", appid);
		wxConfigDao.insertAuthorizer_access_token(map);
	}

	//保存公众号access_token
	@Override
	public void updateAccess_token(String cvalue) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cname", "ACCESS_TOKEN");
		map.put("cvalue", cvalue);
		map.put("expires_in", "7200");
		map.put("createtime",DateUtil.getTodayTime());
		//System.out.println("保存公众号access_token=="+map.toString());
		wxConfigDao.updatePre_auth_code(map);
	}
	//获取公众号access_token
	@Override
	public WxConfig getAccess_token() {
		return wxConfigDao.getComponent_access_token("ACCESS_TOKEN");
	}
	
	
}
