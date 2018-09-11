package com.limon.http.bussiness;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.SendCheckCodeReq;
import com.limon.http.coder.SendCheckCodeRsp;
import com.limon.util.CacheUtil;

public class SendCheckCodeHandler {
	private static final Logger log = Logger.getLogger(SendCheckCodeHandler.class);   
	private SendCheckCodeRsp rsp=new SendCheckCodeRsp();
	
    public SendCheckCodeRsp execute(SendCheckCodeReq req,HttpServletRequest request){    	
        try{
        	//将发送的验证码放入缓存
			Date d=new Date();
			//服务端生成验证码
			//String rand=CommonUtil.getRandom()+"";
			//客户端生成验证码
			String rand=req.getCheckCode();
			CacheUtil.map.put(req.getMobile(),rand+"_"+d.getTime());
			log.info("向手机号为:"+req.getMobile()+"的用户发送验证码,"+CacheUtil.map.get(req.getMobile()));
        	rsp.setResult(1);
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
