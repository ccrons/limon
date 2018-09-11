package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetGoodsTypeReq;
import com.limon.http.coder.GetGoodsTypeRsp;
import com.limon.http.model.ProductType;
import com.limon.http.model.ProductTypeList;
import com.limon.http.service.GetGoodsTypeService;
import com.limon.util.JSONUtil;

public class GetGoodsTypeHandler {
	private static final Logger log = Logger.getLogger(GetGoodsTypeHandler.class);   
	private GetGoodsTypeService service;
	private GetGoodsTypeRsp rsp=new GetGoodsTypeRsp();
	
	public GetGoodsTypeHandler(GetGoodsTypeService service){
		this.service=service;
	}

    public GetGoodsTypeRsp execute(GetGoodsTypeReq req,HttpServletRequest request){    	
        try{
        	//String uid=req.getUid();
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("storeid", req.getSid());
        	map.put("pid", req.getFid());
        	List<ProductType> ptlist=service.getStoreProductType(map);
        	ProductTypeList typelist=new ProductTypeList();
        	typelist.setList(ptlist);
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
