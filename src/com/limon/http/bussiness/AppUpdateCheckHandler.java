package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.AppUpdateCheckReq;
import com.limon.http.coder.AppUpdateCheckRsp;
import com.limon.http.model.Version;
import com.limon.http.service.AppUpdateCheckService;

public class AppUpdateCheckHandler {
	private static final Logger log = Logger.getLogger(AppUpdateCheckHandler.class);   
	private AppUpdateCheckService service;
	private AppUpdateCheckRsp rsp=new AppUpdateCheckRsp();
	
	public AppUpdateCheckHandler(AppUpdateCheckService service){
		this.service=service;
	}

    public AppUpdateCheckRsp execute(AppUpdateCheckReq req,HttpServletRequest request){  
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("os",req.getOS());
        	map.put("ver",req.getVer());
        	Version ver=service.getNewVersion(map);
        	if(ver!=null){
        		rsp.setResult(1);
            	rsp.setNotes(ver.getDescription());
            	rsp.setType(ver.getType()+"");
            	if(ver.getApp_path()!=null&&!ver.getApp_path().equals("")){
            		rsp.setUrl(allPath+ver.getApp_path());
            	}else{
            		rsp.setUrl("");
            	}
            	rsp.setVer(ver.getVersion());
            	rsp.setErrorMsg("");
        	}else{
        		rsp.setResult(0);
                rsp.setNotes("");
                rsp.setType("");
                rsp.setUrl("");
                rsp.setVer("");
                rsp.setErrorMsg("没有新版本");
        	}
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setNotes("");
            rsp.setType("");
            rsp.setUrl("");
            rsp.setVer("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
