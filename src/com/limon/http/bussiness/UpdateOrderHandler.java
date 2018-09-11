package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.UpdateOrderReq;
import com.limon.http.coder.UpdateOrderRsp;
import com.limon.http.model.Order;
import com.limon.http.service.UpdateOrderService;
import com.limon.util.JPushUtil;

public class UpdateOrderHandler {
	private static final Logger log = Logger.getLogger(UpdateOrderHandler.class);   
	private UpdateOrderService service;
	private UpdateOrderRsp rsp=new UpdateOrderRsp();
	
	public UpdateOrderHandler(UpdateOrderService service){
		this.service=service;
	}

    public UpdateOrderRsp execute(UpdateOrderReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id",req.getOid());
        	map.put("status",req.getStatus());
        	map.put("reason",req.getReason());
        	service.updateOrderStatus(map);
        	if(req.getStatus()==2){
        		//商家确认订单推送用户
        		Order order=service.getOrderById(Integer.parseInt(req.getOid()));
        		JPushUtil.pushToAppUserAcceptOrder("app"+order.getUserid(), "您在 "+order.getSname()+" 的订单商家已经确认了，订单号:"+order.getOrderno());
        	}else if(req.getStatus()==5){
        		//商家确取消订单推送用户
        		Order order=service.getOrderById(Integer.parseInt(req.getOid()));
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
