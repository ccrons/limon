package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.AddAddressReq;
import com.limon.http.coder.AddAddressRsp;
import com.limon.http.model.AddAddress;
import com.limon.http.model.Address;
import com.limon.http.service.AddAddressService;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

public class AddAddressHandler {
	private static final Logger log = Logger.getLogger(AddAddressHandler.class);   
	private AddAddressService service;
	private AddAddressRsp rsp=new AddAddressRsp();
	
	public AddAddressHandler(AddAddressService service){
		this.service=service;
	}

    public AddAddressRsp execute(AddAddressReq req,HttpServletRequest request){    	
        try{
        	
        	Address addr=new Address();
        	addr.setUserid(req.getUid());
        	addr.setAddress(req.getAddress());
        	addr.setCity(req.getCity());
        	addr.setContact(req.getName());
        	addr.setCounty(req.getDistrict());
        	addr.setMobile(req.getTel());
        	addr.setProvince(req.getProvice());
        	addr.setCreatetime(DateUtil.getTodayTime());
        	service.saveUserAddress(addr);
        	
        	AddAddress addaddr=new AddAddress();
        	addaddr.setId(addr.getId());
        	rsp.setResult("1");
       		rsp.setAddressInfo(JSON.toJSONString(addaddr,JSONUtil.features));
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setAddressInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
