package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.UpdatePwdReq;
import com.limon.http.coder.UpdatePwdRsp;
import com.limon.http.service.UpdatePwdService;
import com.limon.util.CommonUtil;

public class UpdatePwdHandler {
	private static final Logger log = Logger.getLogger(UpdatePwdHandler.class);   
	private UpdatePwdService service;
	private UpdatePwdRsp rsp=new UpdatePwdRsp();
	
	public UpdatePwdHandler(UpdatePwdService service){
		this.service=service;
	}

    public UpdatePwdRsp execute(UpdatePwdReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("uid",req.getUid());
        	map.put("opasswd",CommonUtil.md5(req.getOPasswd()));
        	map.put("npasswd",CommonUtil.md5(req.getNPasswd()));
        	Integer isRight=service.checkOldPwd(map);
        	if(isRight>0){
        		service.updateAppUserPwd(map);
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
