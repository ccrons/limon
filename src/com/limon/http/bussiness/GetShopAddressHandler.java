package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetShopAddressReq;
import com.limon.http.coder.GetShopAddressRsp;
import com.limon.http.model.Address;
import com.limon.http.service.GetShopAddressService;
import com.limon.util.JSONUtil;

public class GetShopAddressHandler {
	private static final Logger log = Logger.getLogger(GetShopAddressHandler.class);   
	private GetShopAddressService service;
	private GetShopAddressRsp rsp=new GetShopAddressRsp();
	
	public GetShopAddressHandler(GetShopAddressService service){
		this.service=service;
	}

    public GetShopAddressRsp execute(GetShopAddressReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("userid", req.getUid());
        	map.put("storeid",req.getSid());
        	Address address=service.getShopAddressBySidAndUid(map);
        	if(address==null){
        		address=service.getShopAddressDefault(req.getSid());
        		if(address.getId()==null){
        			address.setId(0);
        		}
        	}
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
