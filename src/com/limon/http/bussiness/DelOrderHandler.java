package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.DelOrderReq;
import com.limon.http.coder.DelOrderRsp;
import com.limon.http.service.DelOrderService;

public class DelOrderHandler {
	private static final Logger log = Logger.getLogger(DelOrderHandler.class);   
	private DelOrderService service;
	private DelOrderRsp rsp=new DelOrderRsp();
	
	public DelOrderHandler(DelOrderService service){
		this.service=service;
	}

    public DelOrderRsp execute(DelOrderReq req,HttpServletRequest request){    	
        try{
        	String[] oids=req.getOid().split(",");
        	for(String oid:oids){
        		service.delOrder(oid);
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
