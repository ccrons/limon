package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.SetShopAddressReq;
import com.limon.http.coder.SetShopAddressRsp;
import com.limon.http.model.AddAddress;
import com.limon.http.model.Address;
import com.limon.http.service.SetShopAddressService;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

public class SetShopAddressHandler {
	private static final Logger log = Logger.getLogger(SetShopAddressHandler.class);   
	private SetShopAddressService service;
	private SetShopAddressRsp rsp=new SetShopAddressRsp();
	
	public SetShopAddressHandler(SetShopAddressService service){
		this.service=service;
	}

    public SetShopAddressRsp execute(SetShopAddressReq req,HttpServletRequest request){    	
        try{
        	
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id",0);
        	map.put("userid", req.getUid());
        	map.put("storeid", req.getSid());
        	map.put("address", req.getAddress());
        	map.put("province",req.getProvice());
        	map.put("city",req.getCity());
        	map.put("county",req.getDistrict());
        	map.put("contact",req.getName());
        	map.put("mobile",req.getTel());
        	map.put("createtime",DateUtil.getTodayTime());
        	Address addr=service.getShopAddressBySidAndUid(map);
        	AddAddress addaddr=new AddAddress();
        	if(addr!=null){
        		service.updateShopAddress(map);
        		addaddr.setId(addr.getId());
        	}else{
        		service.saveShopAddress(map);
            	addaddr.setId((Integer)map.get("id"));
        	}
        	
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
