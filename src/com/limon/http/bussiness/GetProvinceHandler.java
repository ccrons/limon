package com.limon.http.bussiness;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetProvinceReq;
import com.limon.http.coder.GetProvinceRsp;
import com.limon.http.model.Province;
import com.limon.http.model.ProvinceList;
import com.limon.http.service.GetProvinceService;
import com.limon.util.JSONUtil;

public class GetProvinceHandler {
	private static final Logger log = Logger.getLogger(GetProvinceHandler.class);   
	private GetProvinceService service;
	private GetProvinceRsp rsp=new GetProvinceRsp();
	
	public GetProvinceHandler(GetProvinceService service){
		this.service=service;
	}

    public GetProvinceRsp execute(GetProvinceReq req,HttpServletRequest request){    	
        try{
        	ProvinceList plist=new ProvinceList();
        	List<Province> list=service.getAllProvince();
        	plist.setList(list);
        	rsp.setProvinceList(JSON.toJSONString(plist,JSONUtil.features));
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
