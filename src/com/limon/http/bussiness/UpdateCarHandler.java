package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.UpdateCarReq;
import com.limon.http.coder.UpdateCarRsp;
import com.limon.http.model.ShoppingCart;
import com.limon.http.service.UpdateCarService;

public class UpdateCarHandler {
	private static final Logger log = Logger.getLogger(UpdateCarHandler.class);   
	private UpdateCarService service;
	private UpdateCarRsp rsp=new UpdateCarRsp();
	
	public UpdateCarHandler(UpdateCarService service){
		this.service=service;
	}

    public UpdateCarRsp execute(UpdateCarReq req,HttpServletRequest request){    	
        try{
        	ShoppingCart cart=new ShoppingCart();
        	cart.setStoreid(req.getSid());
        	cart.setProductid(req.getGid());
        	cart.setUserid(req.getUid());
        	cart.setCount(req.getNum());
        	service.updateCar(cart);
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
