package com.limon.http.bussiness;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.ShopGoodsDownReq;
import com.limon.http.coder.ShopGoodsDownRsp;
import com.limon.http.service.ShopGoodsDownService;

public class ShopGoodsDownHandler {
	private static final Logger log = Logger.getLogger(ShopGoodsDownHandler.class);   
	private ShopGoodsDownService service;
	private ShopGoodsDownRsp rsp=new ShopGoodsDownRsp();
	
	public ShopGoodsDownHandler(ShopGoodsDownService service){
		this.service=service;
	}

    public ShopGoodsDownRsp execute(ShopGoodsDownReq req,HttpServletRequest request){    	
        try{
        	String[] idsarray = req.getSpid().split(",");
    		for (int i = 0; i < idsarray.length; i++) {
    			String id = idsarray[i];
    			if(id!=null && !id.equals("")){
    				service.storeProductDel(id);
    			}
    		}
            rsp.setResult("1");
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
