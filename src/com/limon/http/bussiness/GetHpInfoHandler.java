package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetHpInfoReq;
import com.limon.http.coder.GetHpInfoRsp;
import com.limon.http.model.RecruitInfo;
import com.limon.http.model.TimeLimitInfo;
import com.limon.http.model.ZeroInfo;
import com.limon.http.service.GetHpInfoService;
import com.limon.util.ConfigUtil;
import com.limon.util.JSONUtil;

public class GetHpInfoHandler {
	private static final Logger log = Logger.getLogger(GetHpInfoHandler.class);   
	private GetHpInfoService service;
	private GetHpInfoRsp rsp=new GetHpInfoRsp();
	
	public GetHpInfoHandler(GetHpInfoService service){
		this.service=service;
	}

    public GetHpInfoRsp execute(GetHpInfoReq req,HttpServletRequest request){    	
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
    	try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("sid", req.getSid());
        	String recpic=allPath+ConfigUtil.getConfig("recpic").getConfig_value();
        	String tlpic=allPath+ConfigUtil.getConfig("tlpic").getConfig_value();
        	String fpic=allPath+ConfigUtil.getConfig("fpic").getConfig_value();
        	//招募令
        	RecruitInfo ri=new RecruitInfo();
        	ri.setImgurl(recpic);
        	ri.setInfourl(allPath+"rec?sid="+req.getSid());
        	//零元购
        	ZeroInfo zi=new ZeroInfo();
        	zi.setImgurl(fpic);
        	zi.setInfourl(allPath+"zero?sid="+req.getSid());
        	//限时抢购
        	TimeLimitInfo ti= new TimeLimitInfo();
        	ti.setImgurl(tlpic);
        	ti.setInfourl(allPath+"timelimit?sid="+req.getSid());
        	
        	rsp.setRecruitInfo(JSON.toJSONString(ri,JSONUtil.features));
        	rsp.setZeroInfo(JSON.toJSONString(zi,JSONUtil.features));
        	rsp.setTimeLimitInfo(JSON.toJSONString(ti,JSONUtil.features));
        	rsp.setResult("1");
            rsp.setErrorMsg("");
        }catch(Exception e){     
        	rsp.setRecruitInfo("");
        	rsp.setZeroInfo("");
        	rsp.setTimeLimitInfo("");
            rsp.setResult("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
