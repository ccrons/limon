package com.limon.http.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.ShopGoodsUpReq;
import com.limon.http.coder.ShopGoodsUpRsp;
import com.limon.http.service.ShopGoodsUpService;
import com.limon.manage.model.ProductInfo;

public class ShopGoodsUpHandler {
	private static final Logger log = Logger.getLogger(ShopGoodsUpHandler.class);   
	private ShopGoodsUpService service;
	private ShopGoodsUpRsp rsp=new ShopGoodsUpRsp();
	
	public ShopGoodsUpHandler(ShopGoodsUpService service){
		this.service=service;
	}

    public ShopGoodsUpRsp execute(ShopGoodsUpReq req,HttpServletRequest request){    	
        try{
        	String[] ids=req.getGid().split(",");
        	for(String gid:ids){
        		//判断是否已经上架过
    			Map<String,Object> map=new HashMap<String,Object>();
    			map.put("sid",req.getSid());
    			map.put("gid",gid);
    			
    			ProductInfo p=service.getProductInfo(map);
    			map.put("salenum",p.getUpnum());
    			map.put("saleprice",p.getPrice());
    
    			Integer num = service.getStoreProductCount(map);
    			if(num==0){
    				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    				String createtime = sdf.format(new Date());
    				map.put("createtime",createtime);
    				service.storeProductAdd(map);
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
