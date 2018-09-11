package com.limon.http.bussiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetWSendOrderReq;
import com.limon.http.coder.GetWSendOrderRsp;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.http.model.ShopOrderList;
import com.limon.http.service.GetWSendOrderService;
import com.limon.store.model.StoreInfo;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

public class GetWSendOrderHandler {
	private static final Logger log = Logger.getLogger(GetWSendOrderHandler.class);   
	private GetWSendOrderService service;
	private GetWSendOrderRsp rsp=new GetWSendOrderRsp();
	
	public GetWSendOrderHandler(GetWSendOrderService service){
		this.service=service;
	}

    public GetWSendOrderRsp execute(GetWSendOrderReq req,HttpServletRequest request){ 
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	Integer pageStart=(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum());
        	Integer pageSize=Integer.parseInt(req.getPageNum());
        	map.put("sid",req.getSid());
        	map.put("nowday",DateUtil.getToday());
        	map.put("pageStart", pageStart);
        	map.put("pageSize", pageSize);
        	StoreInfo store=service.getStoreInfoById(Integer.parseInt(req.getSid()));
        	List<ShopOrder> list=new ArrayList<ShopOrder>();
        	if(store.getIsself()==0){
        		list=service.getWSendOrderSelf(map);
        	}else{
        		list=service.getWSendOrder(map);
        	}
        	
        	for(ShopOrder o:list){
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
        		//抽奖订单
        		o.setIsluck(isluck);
        	}
        	Integer recordNum=service.getWSendOrderCount(map);
        	ShopOrderList orderlist=new ShopOrderList();
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
