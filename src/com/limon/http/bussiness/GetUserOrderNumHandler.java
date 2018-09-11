package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.GetUserOrderNumReq;
import com.limon.http.coder.GetUserOrderNumRsp;
import com.limon.http.service.GetUserOrderNumService;

public class GetUserOrderNumHandler {
	private static final Logger log = Logger.getLogger(GetUserOrderNumHandler.class);   
	private GetUserOrderNumService service;
	private GetUserOrderNumRsp rsp=new GetUserOrderNumRsp();
	
	public GetUserOrderNumHandler(GetUserOrderNumService service){
		this.service=service;
	}

    public GetUserOrderNumRsp execute(GetUserOrderNumReq req,HttpServletRequest request){ 
    	try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("uid",req.getUid());
        	Integer onum=service.GetUserOrderNum(map);
        	rsp.setResult("1");
            rsp.setRecordNum(onum);
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setRecordNum(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
