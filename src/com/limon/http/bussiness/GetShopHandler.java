package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetShopReq;
import com.limon.http.coder.GetShopRsp;
import com.limon.http.model.Shop;
import com.limon.http.model.ShopList;
import com.limon.http.service.GetShopService;
import com.limon.util.JSONUtil;

public class GetShopHandler {
	private static final Logger log = Logger.getLogger(GetShopHandler.class);   
	private GetShopService service;
	private GetShopRsp rsp=new GetShopRsp();
	
	public GetShopHandler(GetShopService service){
		this.service=service;
	}

    public GetShopRsp execute(GetShopReq req,HttpServletRequest request){    	
        try{
        	ShopList shoplist=new ShopList();
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("city",req.getCid());
        	map.put("county", req.getDid());
        	List<Shop> list=service.getShopByArea(map);
        	shoplist.setList(list);
        	rsp.setResult("1");
        	rsp.setRecordNum(list.size());
        	rsp.setShopList(JSON.toJSONString(shoplist,JSONUtil.features));
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
