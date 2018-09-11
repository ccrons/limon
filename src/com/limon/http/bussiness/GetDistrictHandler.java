package com.limon.http.bussiness;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetDistrictReq;
import com.limon.http.coder.GetDistrictRsp;
import com.limon.http.model.District;
import com.limon.http.model.DistrictList;
import com.limon.http.service.GetDistrictService;
import com.limon.util.JSONUtil;

public class GetDistrictHandler {
	private static final Logger log = Logger.getLogger(GetDistrictHandler.class);   
	private GetDistrictService service;
	private GetDistrictRsp rsp=new GetDistrictRsp();
	
	public GetDistrictHandler(GetDistrictService service){
		this.service=service;
	}

    public GetDistrictRsp execute(GetDistrictReq req,HttpServletRequest request){    	
    	try{
    		DistrictList dlist=new DistrictList();
        	List<District> list=service.getDistrictByCity(req.getCityid());
        	dlist.setList(list);
        	rsp.setDistrictList(JSON.toJSONString(dlist,JSONUtil.features));
        	rsp.setRecordNum(list.size());
        	rsp.setResult("1");
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
