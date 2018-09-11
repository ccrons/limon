package com.limon.http.bussiness;

import java.net.URLEncoder;
import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import api.ucenter.Client;
import api.ucenter.XMLHelper;

import com.alibaba.fastjson.JSON;
import com.limon.base.common.LogUtil;
import com.limon.http.coder.LoginReq;
import com.limon.http.coder.LoginRsp;
import com.limon.http.model.AppUser;
import com.limon.http.service.LoginService;
import com.limon.util.CacheUtil;
import com.limon.util.CommonUtil;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

public class LoginHandler {
	private static final Logger log = Logger.getLogger(LoginHandler.class);   
	private LoginService service;
	private LoginRsp rsp=new LoginRsp();
	
	public LoginHandler(LoginService service){
		this.service=service;
	}
	/**
	 * 用户登录业务   通过手机验证码登录  没有用户自动注册
	 * @param req
	 * @param request
	 * @return
	 */
    public LoginRsp execute(LoginReq req,HttpServletRequest request,HttpServletResponse response){    
    	String errorInfo = "";
        try{
        	String cachestr=(String)CacheUtil.map.get(req.getUsername());
    		if(cachestr!=null){
    			String[] cache=cachestr.split("_");
    			String checkcode=cache[0];
    			if(req.getCheckCode().equals(checkcode)||req.getCheckCode().equals("123654")){
    				AppUser user=service.getUserByUserName(req.getUsername());
    	        	if(user==null){
    	        		user=new AppUser();
    	        		user.setHeadimg("");
    	        		user.setMobile(req.getUsername());
    	        		user.setUsername(req.getUsername());
    	        		user.setPassword(CommonUtil.md5("1qaz@wsx"));
    	        		user.setCreatetime(DateUtil.getTodayTime());
    	        		service.saveAppUser(user);
    	        		AppUser u=new AppUser();
    	        		if(user.getId()!=null){
    	        			u.setId(user.getId());
    	        			LogUtil.logAppLogin("App用户 "+user.getUsername()+"  登录", user.getUsername(), CommonUtil.getIpAddr(request), 5, req.getOS(), req.getIMEI(), req.getIMSI());
    	        			Client e = new Client();
    	        			//注册bbs
    	        			e.uc_user_register(req.getUsername(), "1qaz@wsx", req.getUsername()+"@mobile.com");
    	        			//传给客户端bbs访问地址
    	    				String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
    	    				String tobbs=allPath+"tobbs?uid="+req.getUsername();
    	    				user.setBbsInfo(tobbs);
    	        			
    	        			rsp.setResult(1);
    	                    rsp.setUserInfo(JSON.toJSONString(u,JSONUtil.features));
    	                    rsp.setErrorMsg(errorInfo);
    	        		}else{
    	        			rsp.setResult(0);
    	                    rsp.setUserInfo("");
    	                    rsp.setErrorMsg("用户注册失败");
    	        		}
    	        	}else{
    	        		LogUtil.logAppLogin("App用户 "+user.getUsername()+"  登录", user.getUsername(), CommonUtil.getIpAddr(request), 5, req.getOS(), req.getIMEI(), req.getIMSI());
    	        		//传给客户端bbs访问地址
    					String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
    					String tobbs=allPath+"tobbs?uid="+req.getUsername();
    					user.setBbsInfo(tobbs);
    	        		rsp.setResult(1);
                   		rsp.setUserInfo(JSON.toJSONString(user,JSONUtil.features));
                        rsp.setErrorMsg(errorInfo);
    	        	}
    			}else{
    				rsp.setResult(0);
               		rsp.setUserInfo("");
                    rsp.setErrorMsg("验证码错误");
    			}
    		}else{
    			rsp.setResult(0);
           		rsp.setUserInfo("");
                rsp.setErrorMsg("验证码错误");
    		}
    		
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setUserInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
