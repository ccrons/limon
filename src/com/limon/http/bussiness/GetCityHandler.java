package com.limon.http.bussiness;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetCityReq;
import com.limon.http.coder.GetCityRsp;
import com.limon.http.model.City;
import com.limon.http.model.CityList;
import com.limon.http.service.GetCityService;
import com.limon.util.JSONUtil;

public class GetCityHandler {
	private static final Logger log = Logger.getLogger(GetCityHandler.class);   
	private GetCityService service;
	private GetCityRsp rsp=new GetCityRsp();
	
	public GetCityHandler(GetCityService service){
		this.service=service;
	}

    public GetCityRsp execute(GetCityReq req,HttpServletRequest request){    	
        try{
        	CityList clist=new CityList();
        	List<City> list=service.getCityByProvince(req.getProvinceid());
        	clist.setList(list);
        	rsp.setCityList(JSON.toJSONString(clist,JSONUtil.features));
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
