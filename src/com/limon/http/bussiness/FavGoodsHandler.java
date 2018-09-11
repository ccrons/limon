package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.FavGoodsReq;
import com.limon.http.coder.FavGoodsRsp;
import com.limon.http.service.FavGoodsService;

public class FavGoodsHandler {
	private static final Logger log = Logger.getLogger(FavGoodsHandler.class);   
	private FavGoodsService service;
	private FavGoodsRsp rsp=new FavGoodsRsp();
	
	public FavGoodsHandler(FavGoodsService service){
		this.service=service;
	}

    public FavGoodsRsp execute(FavGoodsReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("userid",req.getUid());
        	map.put("storeid",req.getSid());
        	map.put("productid",req.getGid());
        	Integer isFav=service.getFavGoods(map);
        	if(isFav>0){
	        	rsp.setResult("0");
	            rsp.setErrorMsg("已在收藏商品中");
        	}else{
	        	service.favGoods(map);
	        	rsp.setResult("1");
	            rsp.setErrorMsg("");
        	}
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
