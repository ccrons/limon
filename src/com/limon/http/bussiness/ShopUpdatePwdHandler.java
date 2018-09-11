package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.ShopUpdatePwdReq;
import com.limon.http.coder.ShopUpdatePwdRsp;
import com.limon.http.service.ShopUpdatePwdService;
import com.limon.util.CommonUtil;

public class ShopUpdatePwdHandler {
	private static final Logger log = Logger.getLogger(ShopUpdatePwdHandler.class);   
	private ShopUpdatePwdService service;
	private ShopUpdatePwdRsp rsp=new ShopUpdatePwdRsp();
	
	public ShopUpdatePwdHandler(ShopUpdatePwdService service){
		this.service=service;
	}

    public ShopUpdatePwdRsp execute(ShopUpdatePwdReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("sid",req.getSid());
        	map.put("opasswd",CommonUtil.md5(req.getOPasswd()));
        	map.put("npasswd",CommonUtil.md5(req.getNPasswd()));
        	Integer isRight=service.checkShopOldPwd(map);
        	if(isRight>0){
        		service.updateShopPwd(map);
            	rsp.setResult(1);
                rsp.setErrorMsg("");
        	}else{
        		rsp.setResult(0);
                rsp.setErrorMsg("原密码错误");
        	}
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
