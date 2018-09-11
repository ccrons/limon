package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetWReceiveOrderReq;
import com.limon.http.coder.GetWReceiveOrderRsp;
import com.limon.http.model.Address;
import com.limon.http.model.OrderListWReceive;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.OrderWReceive;
import com.limon.http.service.GetWReceiveOrderService;
import com.limon.util.JSONUtil;

public class GetWReceiveOrderHandler {
	private static final Logger log = Logger.getLogger(GetWReceiveOrderHandler.class);   
	private GetWReceiveOrderService service;
	private GetWReceiveOrderRsp rsp=new GetWReceiveOrderRsp();
	
	public GetWReceiveOrderHandler(GetWReceiveOrderService service){
		this.service=service;
	}

    public GetWReceiveOrderRsp execute(GetWReceiveOrderReq req,HttpServletRequest request){    	
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	Integer pageStart=(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum());
        	Integer pageSize=Integer.parseInt(req.getPageNum());
        	map.put("userid",req.getUid());
        	map.put("pageStart", pageStart);
        	map.put("pageSize", pageSize);
        	List<OrderWReceive> list=service.getWReceiveOrders(map);
        	for(OrderWReceive o:list){
        		List<OrderProductGet> oplist=service.getProductListByOid(o.getId());
        		Integer isluck=0;
        		for(OrderProductGet p:oplist){
        			p.setImgurl(allPath+p.getImgurl());
        			//是否抽奖订单验证
        			if(p.getOpprice().equals("0.0")||p.getOpprice().equals("0.00")||p.getOpprice().equals("0")){
        				//System.out.println(p.getOpprice()+"================");
        				isluck=1;
        				//System.out.println(isluck+"================");
        			}
        		}
        		o.setList(oplist);
        		Address address=new Address();
        		address.setId(Integer.parseInt(o.getAid()));
        		address.setProvince(o.getProvince());
        		address.setCity(o.getCity());
        		address.setCounty(o.getCounty());
        		address.setContact(o.getAcceptname());
        		address.setMobile(o.getAccepttel());
        		address.setAddress(o.getAddr());
        		o.setAddress(address);
        		if(o.getStatus().equals("1")){
        			o.setStatus("0");
        		}else if(o.getStatus().equals("2")){
        			o.setStatus("1");
        		}
        		//抽奖订单
        		o.setIsluck(isluck);
        	}
        	Integer recordNum=service.getWReceiveOrdersCount(map);
        	OrderListWReceive orderlist=new OrderListWReceive();
        	orderlist.setList(list);
        	rsp.setResult("1");
            rsp.setOrderList(JSON.toJSONString(orderlist,JSONUtil.features));
            rsp.setRecordNum(recordNum);
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setOrderList("");
            rsp.setRecordNum(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
