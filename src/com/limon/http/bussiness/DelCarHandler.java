package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.DelCarReq;
import com.limon.http.coder.DelCarRsp;
import com.limon.http.service.DelCarService;

public class DelCarHandler {
	private static final Logger log = Logger.getLogger(DelCarHandler.class);   
	private DelCarService service;
	private DelCarRsp rsp=new DelCarRsp();
	
	public DelCarHandler(DelCarService service){
		this.service=service;
	}

    public DelCarRsp execute(DelCarReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	
        	map.put("userid",req.getUid());
        	map.put("storeid",req.getSid());
        	String[] gids=req.getGid().split(",");
        	for(String gid:gids){
        		map.put("productid",Integer.parseInt(gid));
        		service.delCar(map);
        	}
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
