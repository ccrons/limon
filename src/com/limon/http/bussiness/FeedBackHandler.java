package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import com.limon.http.coder.FeedBackReq;
import com.limon.http.coder.FeedBackRsp;
import com.limon.http.service.FeedBackService;
import com.limon.util.DateUtil;

public class FeedBackHandler {
	private static final Logger log = Logger.getLogger(FeedBackHandler.class);   
	private FeedBackService service;
	private FeedBackRsp rsp=new FeedBackRsp();
	
	public FeedBackHandler(FeedBackService service){
		this.service=service;
	}

    public FeedBackRsp execute(FeedBackReq req,HttpServletRequest request){    	
        try{
        	
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id",0);
        	map.put("userid",req.getUid());
        	map.put("content",req.getContent());
        	map.put("createtime",DateUtil.getTodayTime());
        	service.saveFeedBack(map);
        	if((Integer)(map.get("id"))>0){
        		rsp.setResult(1);
                rsp.setErrorMsg("");
        	}else{
        		rsp.setResult(0);
                rsp.setErrorMsg("反馈意见提交失败，数据异常");
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
