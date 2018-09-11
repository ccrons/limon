package com.limon.http.bussiness;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetShopAdReq;
import com.limon.http.coder.GetShopAdRsp;
import com.limon.http.model.ShopAd;
import com.limon.http.service.GetShopAdService;
import com.limon.util.JSONUtil;

public class GetShopAdHandler {
	private static final Logger log = Logger.getLogger(GetShopAdHandler.class);   
	private GetShopAdService service;
	private GetShopAdRsp rsp=new GetShopAdRsp();
	
	public GetShopAdHandler(GetShopAdService service){
		this.service=service;
	}

    public GetShopAdRsp execute(GetShopAdReq req,HttpServletRequest request){
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/";
        try{
        	List<ShopAd> adlist=service.getShopAdBySid(req.getSid());
        	for(ShopAd ad:adlist){
        		ad.setAdurl(allPath+"storead/show?id="+ad.getId()+"&sid="+req.getSid());
        		if(ad.getImgurl()!=null&&!ad.getImgurl().equals("")){
        			ad.setImgurl(allPath+ad.getImgurl());
        		}
        	}
        	rsp.setResult("1");
            rsp.setAdList(JSON.toJSONString(adlist,JSONUtil.features));
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setAdList("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
