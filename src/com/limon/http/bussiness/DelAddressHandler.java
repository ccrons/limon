package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import com.limon.http.coder.DelAddressReq;
import com.limon.http.coder.DelAddressRsp;
import com.limon.http.service.DelAddressService;

public class DelAddressHandler {
	private static final Logger log = Logger.getLogger(DelAddressHandler.class);   
	private DelAddressService service;
	private DelAddressRsp rsp=new DelAddressRsp();
	
	public DelAddressHandler(DelAddressService service){
		this.service=service;
	}

    public DelAddressRsp execute(DelAddressReq req,HttpServletRequest request){    	
        try{
        	service.delUserAddr(req.getAid());
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
