package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.RetrievePwdReq;
import com.limon.http.coder.RetrievePwdRsp;
import com.limon.http.service.RetrievePwdService;
import com.limon.util.CommonUtil;

public class RetrievePwdHandler {
	private static final Logger log = Logger.getLogger(RetrievePwdHandler.class);   
	private RetrievePwdService service;
	private RetrievePwdRsp rsp=new RetrievePwdRsp();
	
	public RetrievePwdHandler(RetrievePwdService service){
		this.service=service;
	}

    public RetrievePwdRsp execute(RetrievePwdReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("username",req.getMobile());
        	map.put("passwd",CommonUtil.md5(req.getPasswd()));
        	
        	Integer isRight=CommonUtil.CheckCode(req.getCheckCode(),req.getMobile());
        	if(isRight==1){
        		service.updateAppUserPwd(map);
            	rsp.setResult(1);
                rsp.setErrorMsg("");
        	}else{
        		rsp.setResult(0);
                rsp.setErrorMsg("验证码错误");
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
