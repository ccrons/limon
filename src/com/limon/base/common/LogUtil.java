package com.limon.base.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.limon.base.model.SysLog;
import com.limon.base.service.SysLogService;


/**
 * @author gqf
 * 
 * 日志工具类
 * 2015-2-10 下午01:59:02
 */
@Component
public class LogUtil {
	
	@Autowired
	private SysLogService sysLogService;
	private static LogUtil logUtil;  
	
	/**
	 * 系统日志
	 * @param logclass 该参数类型有三种级别ERROR DEBUG INFO
	 * @param logtype  该参数类型有4种 1-系统日志  2-操作日志 3-登录日志 4-退出日志
	 * @param logcontent
	 * @return 
	 */
	public static int logSystem(String logcontent){
		LogClass logclass=LogClass.INFO;
		SysLog sysLog=new SysLog();
		sysLog.setContent(logcontent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		sysLog.setLogtime(sdf.format(new Date()));
		sysLog.setLogtype(1);
		switch(logclass){
		case ERROR:
			sysLog.setLogclass("ERROR");
			break;
		case DEBUG:
			sysLog.setLogclass("DEBUG");
			break;
		case INFO:
			sysLog.setLogclass("INFO");
			break;
		}
		sysLog.setLoguser("系统日志");
		sysLog.setLogip("127.0.0.1");
		int result =logUtil.sysLogService.saveSysLog(sysLog);
		return result;
	}
	
	/**
	 * 操作日志
	 * @param logclass 该参数类型有三种级别ERROR DEBUG INFO
	 * @param logtype  该参数类型有2种 1-系统日志  2-操作日志 3-登录日志 4-退出日志
	 * @param logcontent
	 * @param loguser
	 * @param logip
	 * @return
	 */
	public static int logOperation(String logcontent,String username,String ipaddr){
		LogClass logclass=LogClass.INFO;
		SysLog sysLog=new SysLog();
		sysLog.setContent(logcontent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		sysLog.setLogtime(sdf.format(new Date()));
		sysLog.setLogtype(2);
		switch(logclass){
		case ERROR:
			sysLog.setLogclass("ERROR");
			break;
		case DEBUG:
			sysLog.setLogclass("DEBUG");
			break;
		case INFO:
			sysLog.setLogclass("INFO");
			break;
		}
		sysLog.setLoguser(username);
		sysLog.setLogip(ipaddr);
		int result =logUtil.sysLogService.saveSysLog(sysLog);
		return result;
	}
	
	/**
	 * 登录日志
	 * @param logclass 该参数类型有三种级别ERROR DEBUG INFO
	 * @param logtype  日志类型 1-系统日志  2-操作日志 3-管理登录日志 4-商铺登录日志  5-APP用户登录日志
	 * @param logcontent
	 * @param loguser
	 * @param logip
	 * @return
	 */
	public static int logLogin(String logcontent,String username,String ipaddr,Integer logtype){
		SysLog sysLog=new SysLog();
		sysLog.setContent(logcontent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		sysLog.setLogtime(sdf.format(new Date()));
		sysLog.setLogtype(logtype);
		sysLog.setLogclass("INFO");		
		sysLog.setLoguser(username);
		sysLog.setLogip(ipaddr);
		int result =logUtil.sysLogService.saveSysLog(sysLog);
		return result;
	}
	
	/**
	 * App登录日志
	 * @param logclass 该参数类型有三种级别ERROR DEBUG INFO
	 * @param logtype  日志类型 1-系统日志  2-操作日志 3-管理登录日志 4-商铺登录日志  5-APP用户登录日志
	 * @param logcontent
	 * @param loguser
	 * @param logip
	 * @return
	 */
	public static int logAppLogin(String logcontent,String username,String ipaddr,Integer logtype,String os,String imei,String imsi){
		SysLog sysLog=new SysLog();
		sysLog.setContent(logcontent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		sysLog.setLogtime(sdf.format(new Date()));
		sysLog.setLogtype(logtype);
		sysLog.setLogclass("INFO");		
		sysLog.setLoguser(username);
		sysLog.setLogip(ipaddr);
		sysLog.setLogos(os);
		sysLog.setLogimei(imei);
		sysLog.setLogimsi(imsi);
		int result =logUtil.sysLogService.saveSysLog(sysLog);
		return result;
	}
	
	 @PostConstruct  
	 public void init() {  
		 logUtil = this;  
		 logUtil.sysLogService = this.sysLogService;  
	 } 
}
