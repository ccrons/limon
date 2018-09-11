package com.limon.http.bussiness;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.AddCarReq;
import com.limon.http.coder.AddCarRsp;
import com.limon.http.model.ShoppingCart;
import com.limon.http.service.AddCarService;
import com.limon.util.DateUtil;

public class AddCarHandler {
	private static final Logger log = Logger.getLogger(AddCarHandler.class);   
	private AddCarService service;
	private AddCarRsp rsp=new AddCarRsp();
	
	public AddCarHandler(AddCarService service){
		this.service=service;
	}

    public AddCarRsp execute(AddCarReq req,HttpServletRequest request){    	
        try{
        	ShoppingCart cart=new ShoppingCart();
        	cart.setStoreid(req.getSid());
        	cart.setProductid(req.getGid());
        	cart.setUserid(req.getUid());
        	cart.setCount(req.getNum());
        	cart.setPrice(req.getPrice());
        	cart.setCreatetime(DateUtil.getTodayTime());
        	ShoppingCart hasCar=service.getCar(cart);
        	if(hasCar!=null){
        		cart.setCount(req.getNum()+hasCar.getCount());
        		service.updateCarNum(cart);
        	}else{
        		service.addCar(cart);
        	}
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
