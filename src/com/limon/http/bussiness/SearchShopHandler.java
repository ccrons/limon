package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.SearchShopReq;
import com.limon.http.coder.SearchShopRsp;
import com.limon.http.model.Shop;
import com.limon.http.model.ShopList;
import com.limon.http.service.SearchShopService;
import com.limon.util.JSONUtil;

public class SearchShopHandler {
	private static final Logger log = Logger.getLogger(SearchShopHandler.class);   
	private SearchShopService service;
	private SearchShopRsp rsp=new SearchShopRsp();
	
	public SearchShopHandler(SearchShopService service){
		this.service=service;
	}

    public SearchShopRsp execute(SearchShopReq req,HttpServletRequest request){    	
        try{
        	//String uid=req.getUid();
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("storename", req.getName());
        	List<Shop> nearlist=service.searchShop(map);
        	ShopList shoplist=new ShopList();
        	shoplist.setList(nearlist);
        	String shopliststr=JSON.toJSONString(shoplist,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setShopList(shopliststr);
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setShopList("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
