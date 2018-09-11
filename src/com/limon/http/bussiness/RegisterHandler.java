package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.RegisterReq;
import com.limon.http.coder.RegisterRsp;
import com.limon.http.model.AppUser;
import com.limon.http.service.RegisterService;
import com.limon.util.CommonUtil;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

public class RegisterHandler {
	private static final Logger log = Logger.getLogger(RegisterHandler.class);   
	private RegisterService service;
	private RegisterRsp rsp=new RegisterRsp();
	
	public RegisterHandler(RegisterService service){
		this.service=service;
	}

    public RegisterRsp execute(RegisterReq req,HttpServletRequest request){    	
        try{
        	AppUser user=new AppUser();
        	Integer hasUser=service.getAppUser(req.getTel());
        	if(hasUser>0){
        		rsp.setResult(0);
                rsp.setUserInfo("");
                rsp.setErrorMsg("该手机号码已注册");
        	}else{
        		user.setHeadimg("");
        		user.setMobile(req.getTel());
        		user.setUsername(req.getTel());
        		user.setPassword(CommonUtil.md5(req.getPassWd()));
        		user.setCreatetime(DateUtil.getTodayTime());
        		service.saveAppUser(user);
        		AppUser u=new AppUser();
        		if(user.getId()!=null){
        			u.setId(user.getId());
            		rsp.setResult(1);
                    rsp.setUserInfo(JSON.toJSONString(u,JSONUtil.features));
                    rsp.setErrorMsg("");
        		}else{
        			rsp.setResult(0);
                    rsp.setUserInfo("");
                    rsp.setErrorMsg("数据操作失败");
        		}
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
