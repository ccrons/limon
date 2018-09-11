package com.limon.http.bussiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetTotalOrderReq;
import com.limon.http.coder.GetTotalOrderRsp;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.http.model.ShopTotalOrderList;
import com.limon.http.service.GetTotalOrderService;
import com.limon.store.model.StoreInfo;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

public class GetTotalOrderHandler {
	private static final Logger log = Logger.getLogger(GetTotalOrderHandler.class);   
	private GetTotalOrderService service;
	private GetTotalOrderRsp rsp=new GetTotalOrderRsp();
	
	public GetTotalOrderHandler(GetTotalOrderService service){
		this.service=service;
	}

    public GetTotalOrderRsp execute(GetTotalOrderReq req,HttpServletRequest request){ 
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
        	Integer todayNum=0;
        	if(store.getIsself()==0){
        		list=service.getTodayOrderSelf(map);
        		todayNum=service.getTodayOrderSelfCount(map);
        	}else{
        		list=service.getTodayOrder(map);
        		todayNum=service.getTodayOrderCount(map);
        	}
        	for(ShopOrder o:list){
        		List<OrderProductGet> oplist=service.getProductListByOid(o.getId());
        		for(OrderProductGet p:oplist){
        			p.setImgurl(allPath+p.getImgurl());
        		}
        		o.setList(oplist);
        	}
        	
        	Integer totalNum=service.getTotalOrderCount(map);
        	Double totaltoday=service.getTodayOrderSum(map);
        	Double totalall=service.getTotalOrderSum(map);
        	
        	ShopTotalOrderList orderlist=new ShopTotalOrderList();
        	orderlist.setTodaynum(todayNum);
        	if(totaltoday==null){
        		totaltoday=0.0;
        	}
        	orderlist.setTotaltoday(totaltoday+"");
        	orderlist.setTotalnum(totalNum);
        	orderlist.setTotalall(totalall+"");
        	orderlist.setList(list);
        	rsp.setResult("1");
            rsp.setOrderList(JSON.toJSONString(orderlist,JSONUtil.features));
            rsp.setRecordNum(todayNum);
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
