package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetGoodsFTypeReq;
import com.limon.http.coder.GetGoodsFTypeRsp;
import com.limon.http.model.ProductType;
import com.limon.http.model.ProductTypeList;
import com.limon.http.service.GetGoodsFTypeService;
import com.limon.util.JSONUtil;

public class GetGoodsFTypeHandler {
	private static final Logger log = Logger.getLogger(GetGoodsFTypeHandler.class);   
	private GetGoodsFTypeService service;
	private GetGoodsFTypeRsp rsp=new GetGoodsFTypeRsp();
	
	public GetGoodsFTypeHandler(GetGoodsFTypeService service){
		this.service=service;
	}

    public GetGoodsFTypeRsp execute(GetGoodsFTypeReq req,HttpServletRequest request){    	
        try{
        	ProductTypeList typelist=new ProductTypeList();
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("storeid", req.getSid()+"");
        	List<ProductType> list=service.getStoreProductType(map);
        	typelist.setList(list);
        	String typeliststr=JSON.toJSONString(typelist,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setTypeList(typeliststr);
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setTypeList("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
