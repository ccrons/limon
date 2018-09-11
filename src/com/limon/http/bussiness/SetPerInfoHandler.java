package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.SetPerInfoReq;
import com.limon.http.coder.SetPerInfoRsp;
import com.limon.http.service.SetPerInfoService;

public class SetPerInfoHandler {
	private static final Logger log = Logger.getLogger(SetPerInfoHandler.class);   
	private SetPerInfoService service;
	private SetPerInfoRsp rsp=new SetPerInfoRsp();
	
	public SetPerInfoHandler(SetPerInfoService service){
		this.service=service;
	}

    public SetPerInfoRsp execute(SetPerInfoReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("uid",req.getUid());
        	map.put("nickname",req.getNickName());
        	map.put("name",req.getName());
        	map.put("sex",req.getSex());
        	map.put("age",req.getAge());
        	map.put("marriage",req.getMarriage());
        	map.put("hidename",req.getHidename());
        	service.setPerInfo(map);
        	rsp.setResult("1");
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
