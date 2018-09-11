package com.limon.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.limon.base.model.SysConfig;
import com.limon.base.service.SysConfigService;


/**
 * @author gqf
 * 
 * sys_config配置信息工具
 * 2015-2-10 下午01:59:02
 */
@Component
public class ConfigUtil {
	
	@Autowired
	private SysConfigService sysConfigService;
	private static ConfigUtil configUtil;  
	
	public static SysConfig getConfig(String configkey){
		SysConfig config=configUtil.sysConfigService.getSysConfig(configkey);
		return config;
	}
	
	@PostConstruct  
	public void init() {  
		configUtil = this;  
		configUtil.sysConfigService = configUtil.sysConfigService;  
	} 
}
