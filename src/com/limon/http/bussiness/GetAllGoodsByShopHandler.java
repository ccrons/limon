package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetAllGoodsByShopReq;
import com.limon.http.coder.GetAllGoodsByShopRsp;
import com.limon.http.model.ShopAllGoods;
import com.limon.http.model.ShopAllList;
import com.limon.http.service.GetAllGoodsByShopService;
import com.limon.util.JSONUtil;

public class GetAllGoodsByShopHandler {
	private static final Logger log = Logger.getLogger(GetAllGoodsByShopHandler.class);   
	private GetAllGoodsByShopService service;
	private GetAllGoodsByShopRsp rsp=new GetAllGoodsByShopRsp();
	
	public GetAllGoodsByShopHandler(GetAllGoodsByShopService service){
		this.service=service;
	}

    public GetAllGoodsByShopRsp execute(GetAllGoodsByShopReq req,HttpServletRequest request){
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("sid", req.getSid());
        	map.put("pageStart",(req.getPage()-1)*Integer.parseInt(req.getPageNum()));
        	map.put("pageSize",Integer.parseInt(req.getPageNum()));
        	List<ShopAllGoods> glist=service.getGoodsByShop(map);
        	for(ShopAllGoods goods:glist){
        		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
        			goods.setImgurl(allPath+goods.getImgurl());
            	}else{
            		goods.setImgurl("");
            	}
        		if(goods.getSpid()!=null){
        			goods.setIsup(1);
        		}else{
        			goods.setIsup(0);
        		}
        	}
        	Integer recordNum=service.getGoodsByShopCount(map);
        	ShopAllList goodslist=new ShopAllList();
        	goodslist.setList(glist);
        	String goodsliststr=JSON.toJSONString(goodslist,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setGoodsList(goodsliststr);
        	rsp.setErrorMsg("");
        	rsp.setRecordNum(recordNum);
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setGoodsList("");
            rsp.setRecordNum(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
