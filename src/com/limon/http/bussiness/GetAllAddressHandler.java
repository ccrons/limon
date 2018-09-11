package com.limon.http.bussiness;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetAllAddressReq;
import com.limon.http.coder.GetAllAddressRsp;
import com.limon.http.model.Address;
import com.limon.http.model.AddressList;
import com.limon.http.service.GetAllAddressService;
import com.limon.util.JSONUtil;

public class GetAllAddressHandler {
	private static final Logger log = Logger.getLogger(GetAllAddressHandler.class);   
	private GetAllAddressService service;
	private GetAllAddressRsp rsp=new GetAllAddressRsp();
	
	public GetAllAddressHandler(GetAllAddressService service){
		this.service=service;
	}

    public GetAllAddressRsp execute(GetAllAddressReq req,HttpServletRequest request){    	
        try{
        	List<Address> list=service.getAllAddress(req.getUid());
        	AddressList addrlist=new AddressList();
        	addrlist.setList(list);
        	rsp.setResult("1");
            rsp.setAddressList(JSON.toJSONString(addrlist,JSONUtil.features));
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setAddressList("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
