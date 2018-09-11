package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetHistoryOrderReq;
import com.limon.http.coder.GetHistoryOrderRsp;
import com.limon.http.model.Address;
import com.limon.http.model.OrderHistory;
import com.limon.http.model.OrderHistoryList;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.GetHistoryOrderService;
import com.limon.util.JSONUtil;

public class GetHistoryOrderHandler {
	private static final Logger log = Logger.getLogger(GetHistoryOrderHandler.class);   
	private GetHistoryOrderService service;
	private GetHistoryOrderRsp rsp=new GetHistoryOrderRsp();
	
	public GetHistoryOrderHandler(GetHistoryOrderService service){
		this.service=service;
	}

    public GetHistoryOrderRsp execute(GetHistoryOrderReq req,HttpServletRequest request){    	
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	Integer pageStart=(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum());
        	Integer pageSize=Integer.parseInt(req.getPageNum());
        	map.put("userid",req.getUid());
        	map.put("pageStart", pageStart);
        	map.put("pageSize", pageSize);
        	List<OrderHistory> list=service.getHistoryOrders(map);
        	for(OrderHistory o:list){
        		if(o.getStatus()==3){
        			//已完成
        			o.setType(0);
        		}else if(o.getStatus()==4||o.getStatus()==5){
        			//已取消
        			o.setType(1);
        		}else{
        			o.setType(0);
        		}
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
        		Address address=new Address();
        		address.setId(Integer.parseInt(o.getAid()));
        		address.setProvince(o.getProvince());
        		address.setCity(o.getCity());
        		address.setCounty(o.getCounty());
        		address.setContact(o.getAcceptname());
        		address.setMobile(o.getAccepttel());
        		address.setAddress(o.getAddr());
        		o.setAddress(address);
        		o.setList(oplist);
        		//抽奖订单
        		o.setIsluck(isluck);
        	}
        	Integer recordNum=service.getHistoryOrdersCount(map);
        	OrderHistoryList orderlist=new OrderHistoryList();
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
