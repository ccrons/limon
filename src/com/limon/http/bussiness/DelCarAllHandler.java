package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.DelCarAllReq;
import com.limon.http.coder.DelCarAllRsp;
import com.limon.http.service.DelCarAllService;

public class DelCarAllHandler {
	private static final Logger log = Logger.getLogger(DelCarAllHandler.class);   
	private DelCarAllService service;
	private DelCarAllRsp rsp=new DelCarAllRsp();
	
	public DelCarAllHandler(DelCarAllService service){
		this.service=service;
	}

    public DelCarAllRsp execute(DelCarAllReq req,HttpServletRequest request){    	
        try{
        	service.delCarAll(req.getUid());
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
