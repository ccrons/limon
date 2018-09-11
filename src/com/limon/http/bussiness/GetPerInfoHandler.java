package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetPerInfoReq;
import com.limon.http.coder.GetPerInfoRsp;
import com.limon.http.model.PerInfo;
import com.limon.http.service.GetPerInfoService;
import com.limon.util.JSONUtil;

public class GetPerInfoHandler {
	private static final Logger log = Logger.getLogger(GetPerInfoHandler.class);   
	private GetPerInfoService service;
	private GetPerInfoRsp rsp=new GetPerInfoRsp();
	
	public GetPerInfoHandler(GetPerInfoService service){
		this.service=service;
	}

    public GetPerInfoRsp execute(GetPerInfoReq req,HttpServletRequest request){    	
        try{
        	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
        	PerInfo pi=service.getPerInfoById(req.getUid());
        	if(pi.getHeadimg()!=null&&!pi.getHeadimg().equals("")){
        		pi.setHeadimg(allPath+"/"+pi.getHeadimg());
        	}
        	String pjson=JSON.toJSONString(pi,JSONUtil.features);
        	rsp.setResult("1");
        	rsp.setPerInfo(pjson);
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setPerInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
