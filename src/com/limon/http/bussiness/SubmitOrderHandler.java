package com.limon.http.bussiness;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.SubmitOrderReq;
import com.limon.http.coder.SubmitOrderRsp;
import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.model.OrderInfo;
import com.limon.http.model.OrderProductInfo;
import com.limon.http.model.OrderProductList;
import com.limon.http.service.SubmitOrderService;
import com.limon.manage.model.StoreAdProduct;
import com.limon.store.service.StoreAdService;
import com.limon.util.CommonUtil;
import com.limon.util.DateUtil;
import com.limon.util.JPushUtil;
import com.limon.util.JSONUtil;

public class SubmitOrderHandler {
	private static final Logger log = Logger.getLogger(SubmitOrderHandler.class);   
	private SubmitOrderService service;
	private StoreAdService storeAdService;
	private SubmitOrderRsp rsp=new SubmitOrderRsp();
	
	public SubmitOrderHandler(SubmitOrderService service,StoreAdService storeAdService){
		this.service=service;
		this.storeAdService=storeAdService;
	}

    public SubmitOrderRsp execute(SubmitOrderReq req,HttpServletRequest request){    	
        try{
        	String orderno=CommonUtil.getDateFormatMillisecond().format(new Date());
        	String payno="";//支付流水号
        	Integer status=0;//订单状态0-未支付1-已支付(待确认)/新订单2-待收货/派送中3-已完成4-已取消（未付款）5-已取消（已付款，退款）
        	Integer userid=req.getUid();
        	Integer storeid=req.getSid();
        	String orderprice=req.getOrderPrice();//订单金额
        	String sendprice=req.getSendPrice();//配送费
        	String remark=req.getReMark();//订单备注
        	Integer addrid=req.getAid();
        	String createtime=DateUtil.getTodayTime();
        	String sendtime=req.getSendTime();
        	String reason="";
        	String goodscode=CommonUtil.getRandom8()+"";
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id",0);
        	map.put("orderno",orderno);
        	map.put("payno",payno);
        	map.put("status",status);
        	map.put("userid",userid);
        	map.put("storeid",storeid);
        	map.put("orderprice",orderprice);
        	map.put("sendprice",sendprice);
        	map.put("remark",remark);
        	map.put("addrid", addrid);
        	map.put("sendtime",sendtime);
        	map.put("goodscode",goodscode);
        	map.put("createtime",createtime);
        	map.put("reason",reason);
        	Address address=service.getAddressById(addrid);
        	map.put("province",address.getProvince());
        	map.put("city",address.getCity());
        	map.put("county",address.getCounty());
        	map.put("address",address.getAddress());
        	map.put("acceptname",address.getContact());
        	map.put("accepttel",address.getMobile());
        	
        	Integer hasad=0;
        	OrderProductList orderproducts=JSON.parseObject(req.getGoodsList(),OrderProductList.class);
        	List<OrderProductInfo> list=orderproducts.getList();
    		for(OrderProductInfo product:list){
	        	//活动验证
				StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(product.getId()+"");
				if(ap!=null){
					hasad=1;
				}
    		}
        	
        	map.put("hasad", hasad);
        	service.saveOrder(map);
        	
        	
        	Integer id=(Integer)map.get("id");
        	if(id>0){
        		for(OrderProductInfo product:list){
        			Map<String,Object> omap=new HashMap<String,Object>();
        			omap.put("orderid", id);
        			omap.put("productid",product.getId());
        			omap.put("ordernum",product.getCount());
        			omap.put("opprice", product.getPrice());
        			service.saveOrderProduct(omap);
        			
        			Map<String,Object> smap=new HashMap<String,Object>();
        			smap.put("sid", storeid);
        			smap.put("pid",product.getId());
        			//更新店铺库存
        			Integer pnum=service.getProductNum(smap);
        			if(pnum>0){
        				service.updateProductNum(smap);
        			}
        			
        			//更新抢购数量
        			Integer tnum=service.getTLimitNum(smap);
        			if(tnum!=null&&tnum>0){
        				service.updateTLimitNum(map);
        			}
        			
        			
        		}
        	}
        	
        	OrderInfo os=new OrderInfo();
        	os.setId((Integer)map.get("id"));
        	os.setPrice((String)map.get("orderprice"));
        	rsp.setResult("1");
            rsp.setOrderInfo(JSON.toJSONString(os,JSONUtil.features));
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setOrderInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
