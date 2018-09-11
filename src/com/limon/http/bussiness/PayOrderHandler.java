package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.PayOrderReq;
import com.limon.http.coder.PayOrderRsp;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.PayOrderService;
import com.limon.util.JPushUtil;

public class PayOrderHandler {
	private static final Logger log = Logger.getLogger(PayOrderHandler.class);   
	private PayOrderService service;
	private PayOrderRsp rsp=new PayOrderRsp();
	
	public PayOrderHandler(PayOrderService service){
		this.service=service;
	}

    public PayOrderRsp execute(PayOrderReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id", req.getOid());
        	map.put("payno",req.getPayNo());
        	map.put("paytype",req.getPayType());
        	map.put("status",1);
        	//更新订单状态
        	service.updateOrderStatus(map);
        	//查询订单信息
        	Order o=service.getOrderbyOid(Integer.parseInt(req.getOid()));
        	List<OrderProductGet> plist=service.getProductListByOid(Integer.parseInt(req.getOid()));
        	//减少商家库存
        	Map<String,Object> smap=new HashMap<String,Object>();
        	smap.put("storeid", o.getSid());
        	for(OrderProductGet op:plist){
        		smap.put("pnum",op.getCount());
        		smap.put("productid",op.getGid());
        		service.updateStoreSaleNum(smap);
        	}
        	//推送商家新订单
        	JPushUtil.pushToStoreNewOrder("shop"+o.getSid(), "您的店铺有新订单："+o.getOrderno());
        	
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
