package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.ShopGoodsUpdateReq;
import com.limon.http.coder.ShopGoodsUpdateRsp;
import com.limon.http.service.ShopGoodsUpdateService;

public class ShopGoodsUpdateHandler {
	private static final Logger log = Logger.getLogger(ShopGoodsUpdateHandler.class);   
	private ShopGoodsUpdateService service;
	private ShopGoodsUpdateRsp rsp=new ShopGoodsUpdateRsp();
	
	public ShopGoodsUpdateHandler(ShopGoodsUpdateService service){
		this.service=service;
	}

    public ShopGoodsUpdateRsp execute(ShopGoodsUpdateReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("spid",req.getSpid());
        	map.put("saleprice", req.getPrice());
        	map.put("salenum", req.getNum());
        	if(req.getPrice()!=null&&!req.getPrice().equals("")&&req.getNum()!=null&&!req.getNum().equals("")){
        		service.storeProductUpdate(map);
        	}else if(req.getPrice()!=null&&!req.getPrice().equals("")&&(req.getNum()==null||req.getNum().equals(""))){
        		service.storeProductUpdatePrice(map);
        	}else if(req.getNum()!=null&&!req.getNum().equals("")&&(req.getPrice()==null||req.getPrice().equals(""))){
        		service.storeProductUpdateNum(map);
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
