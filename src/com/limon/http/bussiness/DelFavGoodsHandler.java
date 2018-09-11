package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.DelFavGoodsReq;
import com.limon.http.coder.DelFavGoodsRsp;
import com.limon.http.service.DelFavGoodsService;

public class DelFavGoodsHandler {
	private static final Logger log = Logger.getLogger(DelFavGoodsHandler.class);   
	private DelFavGoodsService service;
	private DelFavGoodsRsp rsp=new DelFavGoodsRsp();
	
	public DelFavGoodsHandler(DelFavGoodsService service){
		this.service=service;
	}

    public DelFavGoodsRsp execute(DelFavGoodsReq req,HttpServletRequest request){    	
        try{
        	String[] gids=req.getGid().split(",");
        	for(String p:gids){
        		Map<String,Object> map=new HashMap<String,Object>();
            	map.put("userid",req.getUid());
            	map.put("storeid",req.getSid());
            	map.put("productid",p);
            	service.delFavGoods(map);
        	}
        	rsp.setResult(1);
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
