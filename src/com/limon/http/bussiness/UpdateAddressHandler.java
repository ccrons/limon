package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.UpdateAddressReq;
import com.limon.http.coder.UpdateAddressRsp;
import com.limon.http.model.Address;
import com.limon.http.service.UpdateAddressService;

public class UpdateAddressHandler {
	private static final Logger log = Logger.getLogger(UpdateAddressHandler.class);   
	private UpdateAddressService service;
	private UpdateAddressRsp rsp=new UpdateAddressRsp();
	
	public UpdateAddressHandler(UpdateAddressService service){
		this.service=service;
	}

    public UpdateAddressRsp execute(UpdateAddressReq req,HttpServletRequest request){    	
        try{
        	Address addr=new Address();
        	addr.setAddress(req.getAddress());
        	addr.setCity(req.getCity());
        	addr.setContact(req.getName());
        	addr.setCounty(req.getDistrict());
        	addr.setId(req.getAid());
        	addr.setMobile(req.getTel());
        	addr.setProvince(req.getProvice());
        	service.updateUserAddress(addr);
        	rsp.setResult("1");
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
