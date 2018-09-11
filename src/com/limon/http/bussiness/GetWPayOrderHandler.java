package com.limon.http.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetWPayOrderReq;
import com.limon.http.coder.GetWPayOrderRsp;
import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.model.OrderList;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.GetWPayOrderService;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.StoreAdService;
import com.limon.util.ConfigUtil;
import com.limon.util.JSONUtil;

public class GetWPayOrderHandler {
	private static final Logger log = Logger.getLogger(GetWPayOrderHandler.class);   
	private GetWPayOrderService service;
	private StoreAdService storeAdService;
	private GetWPayOrderRsp rsp=new GetWPayOrderRsp();
	
	public GetWPayOrderHandler(GetWPayOrderService service,StoreAdService storeAdService){
		this.service=service;
		this.storeAdService=storeAdService;
	}

    public GetWPayOrderRsp execute(GetWPayOrderReq req,HttpServletRequest request){ 
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	Integer pageStart=(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum());
        	Integer pageSize=Integer.parseInt(req.getPageNum());
        	map.put("userid",req.getUid());
        	map.put("pageStart", pageStart);
        	map.put("pageSize", pageSize);
        	List<Order> list=service.getWPayOrders(map);
        	for(Order o:list){
        		Integer isluck=0;
        		Integer isright=0;
        		List<OrderProductGet> oplist=service.getProductListByOid(o.getId());
        		for(OrderProductGet p:oplist){
        			p.setImgurl(allPath+p.getImgurl());
        			//是否抽奖订单验证
        			if(p.getOpprice().equals("0.0")||p.getOpprice().equals("0.00")||p.getOpprice().equals("0")){
        				//System.out.println(p.getOpprice()+"================");
        				isluck=1;
        				//System.out.println(isluck+"================");
        			}
        			//活动是否有效验证  当订单含有限时广告商品  且当前时间超出抢购时间 或  抢购商品已移除 订单失效
        			List<String> tltimes=service.getTimeLimitProduct(p.getGid());
        			if(tltimes!=null&&tltimes.size()>0){
        				String tl=tltimes.get(0);
        				//时间处理
            			SimpleDateFormat datesdf=new SimpleDateFormat("yyyy-MM-dd");
            			SimpleDateFormat datetimesdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            			Date d=new Date();//当前时间
            			
            			String fstime1=ConfigUtil.getConfig("fstime1").getConfig_value();
        				String fetime1=ConfigUtil.getConfig("fetime1").getConfig_value();
            			Date stime1=datetimesdf.parse(datesdf.format(d)+" "+fstime1+":00");
        				Date etime1=datetimesdf.parse(datesdf.format(d)+" "+fetime1+":00");
        				
        				String fstime2=ConfigUtil.getConfig("fstime2").getConfig_value();
        				String fetime2=ConfigUtil.getConfig("fetime2").getConfig_value();
            			Date stime2=datetimesdf.parse(datesdf.format(d)+" "+fstime2+":00");
        				Date etime2=datetimesdf.parse(datesdf.format(d)+" "+fetime2+":00");
        				
        				String fstime3=ConfigUtil.getConfig("fstime3").getConfig_value();
        				String fetime3=ConfigUtil.getConfig("fetime3").getConfig_value();
            			Date stime3=datetimesdf.parse(datesdf.format(d)+" "+fstime3+":00");
        				Date etime3=datetimesdf.parse(datesdf.format(d)+" "+fetime3+":00");
        				//活动验证
        				TimeLimit timel = storeAdService.getTimeLimitByProductId(p.getGid()+"");
        				if(timel==null){
        					isright=1;
        				}else{
		    				if(tl.indexOf("1,")!=-1&&d.getTime()>stime1.getTime()&&d.getTime()<etime1.getTime()){
		    					if(timel==null){
	        						isright=1;
	        					}
	        				}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
	        					if(timel==null){
	        						isright=1;
	        					}
	        				}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
	        					if(timel==null){
	        						isright=1;
	        					}
	        				}else{
	        					isright=1;
	        				}
        				}
        			}
        			
        			
        			//活动是否有效验证  当订单含有广告商品时且广告商品已移除  订单失效
        			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(p.getGid()+"");
        			if(o.getHasad()!=null&&o.getHasad()==1&&ap==null){
        				isright=1;
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
        		
    			//判断订单是否有效
        		o.setIsright(isright);
        		//判断是否抽奖订单
        		o.setIsluck(isluck);
        	}
        	
        	
        	
        	Integer recordNum=service.getWPayOrdersCount(map);
        	OrderList orderlist=new OrderList();
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
