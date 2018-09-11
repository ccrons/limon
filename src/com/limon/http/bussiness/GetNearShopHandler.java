package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetNearShopReq;
import com.limon.http.coder.GetNearShopRsp;
import com.limon.http.model.Shop;
import com.limon.http.model.ShopList;
import com.limon.http.service.GetNearShopService;
import com.limon.util.JSONUtil;

public class GetNearShopHandler {
	private static final Logger log = Logger.getLogger(GetNearShopHandler.class);   
	private GetNearShopService service;
	private GetNearShopRsp rsp=new GetNearShopRsp();
	
	public GetNearShopHandler(GetNearShopService service){
		this.service=service;
	}

    public GetNearShopRsp execute(GetNearShopReq req,HttpServletRequest request){    	
        try{
        	//String uid=req.getUid();
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("userlat", req.getLat());
        	map.put("userlng", req.getLng());
        	//为IOS审核设置大范围搜索
        	//map.put("radius", req.getDistance());
        	map.put("radius", "2000");
        	List<Shop> nearlist=service.getNearShop(map);
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
