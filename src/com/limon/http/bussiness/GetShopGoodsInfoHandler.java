package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetShopGoodsInfoReq;
import com.limon.http.coder.GetShopGoodsInfoRsp;
import com.limon.http.model.ShopGoods;
import com.limon.http.service.GetShopGoodsInfoService;
import com.limon.util.JSONUtil;

public class GetShopGoodsInfoHandler {
	private static final Logger log = Logger.getLogger(GetShopGoodsInfoHandler.class);   
	private GetShopGoodsInfoService service;
	private GetShopGoodsInfoRsp rsp=new GetShopGoodsInfoRsp();
	
	public GetShopGoodsInfoHandler(GetShopGoodsInfoService service){
		this.service=service;
	}

    public GetShopGoodsInfoRsp execute(GetShopGoodsInfoReq req,HttpServletRequest request){  
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	ShopGoods goodsinfo=service.getShopGoodsInfo(req.getGid());
        	if(goodsinfo.getImgurls()!=null&&!goodsinfo.getImgurls().equals("")){
        		goodsinfo.setImgurls(allPath+goodsinfo.getImgurls());
        	}else{
        		goodsinfo.setImgurls("");
        	}
        	if(goodsinfo.getPic1()!=null&&!goodsinfo.getPic1().equals("")){
        		goodsinfo.setPic1(allPath+goodsinfo.getPic1());
        	}else{
        		goodsinfo.setPic1("");
        	}
        	if(goodsinfo.getPic2()!=null&&!goodsinfo.getPic2().equals("")){
        		goodsinfo.setPic2(allPath+goodsinfo.getPic2());
        	}else{
        		goodsinfo.setPic2("");
        	}
        	if(goodsinfo.getPic3()!=null&&!goodsinfo.getPic3().equals("")){
        		goodsinfo.setPic3(allPath+goodsinfo.getPic3());
        	}else{
        		goodsinfo.setPic3("");
        	}
        	if(goodsinfo.getPic4()!=null&&!goodsinfo.getPic4().equals("")){
        		goodsinfo.setPic4(allPath+goodsinfo.getPic4());
        	}else{
        		goodsinfo.setPic4("");
        	}
        	if(goodsinfo.getPic5()!=null&&!goodsinfo.getPic5().equals("")){
        		goodsinfo.setPic5(allPath+goodsinfo.getPic5());
        	}else{
        		goodsinfo.setPic5("");
        	}
        	String goodsinfostr=JSON.toJSONString(goodsinfo,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setShopGoods(goodsinfostr);
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setShopGoods("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
