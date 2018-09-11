package com.limon.http.bussiness;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.NmSelfShopReq;
import com.limon.http.coder.NmSelfShopRsp;
import com.limon.http.model.AdInfo;
import com.limon.http.model.SaleInfo;
import com.limon.http.model.SelfShop;
import com.limon.http.service.NmSelfShopService;
import com.limon.util.JSONUtil;

public class NmSelfShopHandler {
	private static final Logger log = Logger.getLogger(NmSelfShopHandler.class);   
	private NmSelfShopService service;
	private NmSelfShopRsp rsp=new NmSelfShopRsp();
	
	public NmSelfShopHandler(NmSelfShopService service){
		this.service=service;
	}

    public NmSelfShopRsp execute(NmSelfShopReq req,HttpServletRequest request){ 
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	SelfShop shop=service.getNmSelfShop();
        	List<SaleInfo> salelist=service.getSaleInfoList(shop.getId());
        	for(SaleInfo si:salelist){
        		if(si.getImgurl()!=null&&!si.getImgurl().equals("")){
        			si.setImgurl(allPath+si.getImgurl());
            	}else{
            		si.setImgurl("");
            	}
        	}
        	List<AdInfo> adlist=service.getAdInfoList(shop.getId());
        	for(AdInfo ai:adlist){
        		if(ai.getImgurl()!=null&&!ai.getImgurl().equals("")){
        			ai.setImgurl(allPath+ai.getImgurl());
            	}else{
            		ai.setImgurl("");
            	}
        	}
        	shop.setAdinfo(adlist);
        	shop.setSaleinfo(salelist);
        	rsp.setResult(1);
            rsp.setShopInfo(JSON.toJSONString(shop,JSONUtil.features));
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
