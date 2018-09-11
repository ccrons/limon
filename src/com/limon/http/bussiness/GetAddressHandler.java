package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetAddressReq;
import com.limon.http.coder.GetAddressRsp;
import com.limon.http.model.Address;
import com.limon.http.service.GetAddressService;
import com.limon.util.JSONUtil;

public class GetAddressHandler {
	private static final Logger log = Logger.getLogger(GetAddressHandler.class);   
	private GetAddressService service;
	private GetAddressRsp rsp=new GetAddressRsp();
	
	public GetAddressHandler(GetAddressService service){
		this.service=service;
	}

    public GetAddressRsp execute(GetAddressReq req,HttpServletRequest request){    	
        try{
        	Address address=service.getAddressByAid(req.getAid());

        	rsp.setResult("1");
            rsp.setAddress(JSON.toJSONString(address,JSONUtil.features));
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setAddress("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
