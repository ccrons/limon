package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetMyGoodsByShopReq;
import com.limon.http.coder.GetMyGoodsByShopRsp;
import com.limon.http.model.ShopMyGoods;
import com.limon.http.model.ShopMyList;
import com.limon.http.service.GetMyGoodsByShopService;
import com.limon.util.JSONUtil;

public class GetMyGoodsByShopHandler {
	private static final Logger log = Logger.getLogger(GetMyGoodsByShopHandler.class);   
	private GetMyGoodsByShopService service;
	private GetMyGoodsByShopRsp rsp=new GetMyGoodsByShopRsp();
	
	public GetMyGoodsByShopHandler(GetMyGoodsByShopService service){
		this.service=service;
	}

    public GetMyGoodsByShopRsp execute(GetMyGoodsByShopReq req,HttpServletRequest request){
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("sid", req.getSid());
        	map.put("pageStart",(req.getPage()-1)*Integer.parseInt(req.getPageNum()));
        	map.put("pageSize",Integer.parseInt(req.getPageNum()));
        	List<ShopMyGoods> glist=service.getMyGoodsByShop(map);
        	for(ShopMyGoods goods:glist){
        		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
        			goods.setImgurl(allPath+goods.getImgurl());
            	}else{
            		goods.setImgurl("");
            	}
        	}
        	Integer recordNum=service.getMyGoodsByShopCount(map);
        	ShopMyList goodslist=new ShopMyList();
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
