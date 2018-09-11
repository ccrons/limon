package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.ShopLoginReq;
import com.limon.http.coder.ShopLoginRsp;
import com.limon.http.model.ShopLogin;
import com.limon.http.service.ShopLoginService;
import com.limon.util.CommonUtil;
import com.limon.util.JSONUtil;

public class ShopLoginHandler {
	private static final Logger log = Logger.getLogger(ShopLoginHandler.class);   
	private ShopLoginService service;
	private ShopLoginRsp rsp=new ShopLoginRsp();
	
	public ShopLoginHandler(ShopLoginService service){
		this.service=service;
	}

    public ShopLoginRsp execute(ShopLoginReq req,HttpServletRequest request){    	
        try{
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("username", req.getUsername());
        	map.put("password", CommonUtil.md5(req.getPasswd()));
        	ShopLogin user=service.getShopByUserName(map);
        	if(user==null){
        		rsp.setResult(0);
           		rsp.setShopInfo("");
                rsp.setErrorMsg("店铺不存在");
        	}else{
        		user=service.getShopByUserNameAndPassword(map);
        		if(user==null){
        			rsp.setResult(0);
               		rsp.setShopInfo("");
                    rsp.setErrorMsg("密码错误");
        		}else{
        			rsp.setResult(1);
               		rsp.setShopInfo(JSON.toJSONString(user,JSONUtil.features));
                    rsp.setErrorMsg("");
        		}
        	}
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setShopInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
