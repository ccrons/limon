package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.ShopUpdateOrderReq;
import com.limon.http.coder.ShopUpdateOrderRsp;
import com.limon.http.model.Order;
import com.limon.http.service.ShopUpdateOrderService;
import com.limon.util.JPushUtil;

public class ShopUpdateOrderHandler {
	private static final Logger log = Logger.getLogger(ShopUpdateOrderHandler.class);   
	private ShopUpdateOrderService service;
	private ShopUpdateOrderRsp rsp=new ShopUpdateOrderRsp();
	
	public ShopUpdateOrderHandler(ShopUpdateOrderService service){
		this.service=service;
	}

    public ShopUpdateOrderRsp execute(ShopUpdateOrderReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id",req.getOid());
        	map.put("status",req.getStatus());
        	map.put("reason",req.getReason());
        	service.shopupdateOrderStatus(map);
        	if(req.getStatus()==2){
        		//商家确认订单推送用户
        		Order order=service.getOrderbyId(Integer.parseInt(req.getOid()));
        		JPushUtil.pushToAppUserAcceptOrder("app"+order.getUserid(), "您在 "+order.getSname()+" 的订单商家已经确认了，订单号:"+order.getOrderno());
        	}else if(req.getStatus()==5){
        		//商家确取消订单推送用户
        		Order order=service.getOrderbyId(Integer.parseInt(req.getOid()));
        		JPushUtil.pushToAppUserCancelOrder("app"+order.getUserid(), "您在 "+order.getSname()+" 的订单被商家取消了，原因："+req.getReason()+"，订单号:"+order.getOrderno());
        	
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
