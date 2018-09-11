package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetShopInfoReq;
import com.limon.http.coder.GetShopInfoRsp;
import com.limon.http.model.Shop;
import com.limon.http.service.GetShopInfoService;
import com.limon.util.JSONUtil;

public class GetShopInfoHandler {
	private static final Logger log = Logger.getLogger(GetShopInfoHandler.class);   
	private GetShopInfoService service;
	private GetShopInfoRsp rsp=new GetShopInfoRsp();
	
	public GetShopInfoHandler(GetShopInfoService service){
		this.service=service;
	}

    public GetShopInfoRsp execute(GetShopInfoReq req,HttpServletRequest request){    	
        try{
        	Shop shop=service.getShopById(req.getSid());
        	String shopstr=JSON.toJSONString(shop,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setShopInfo(shopstr);
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setShopInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
