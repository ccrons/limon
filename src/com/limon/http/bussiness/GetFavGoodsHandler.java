package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetFavGoodsReq;
import com.limon.http.coder.GetFavGoodsRsp;
import com.limon.http.model.FavGoods;
import com.limon.http.model.FavGoodsList;
import com.limon.http.service.GetFavGoodsService;
import com.limon.util.JSONUtil;

public class GetFavGoodsHandler {
	private static final Logger log = Logger.getLogger(GetFavGoodsHandler.class);   
	private GetFavGoodsService service;
	private GetFavGoodsRsp rsp=new GetFavGoodsRsp();
	
	public GetFavGoodsHandler(GetFavGoodsService service){
		this.service=service;
	}

    public GetFavGoodsRsp execute(GetFavGoodsReq req,HttpServletRequest request){ 
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	Integer pageStart=(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum());
        	Integer pageSize=Integer.parseInt(req.getPageNum());
        	map.put("userid",req.getUid());
        	map.put("pageStart", pageStart);
        	map.put("pageSize", pageSize);
        	List<FavGoods> list=service.getFavGoods(map);
        	for(FavGoods goods:list){
        		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
        			goods.setImgurl(allPath+goods.getImgurl());
            	}else{
            		goods.setImgurl("");
            	}
        	}
        	Integer recordNum=service.getFavGoodsCount(map);
        	FavGoodsList goodslist=new FavGoodsList();
        	goodslist.setList(list);
        	rsp.setResult("1");
            rsp.setGoodsList(JSON.toJSONString(goodslist,JSONUtil.features));
            rsp.setRecordNum(recordNum);
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setGoodsList("");
            rsp.setRecordNum(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
