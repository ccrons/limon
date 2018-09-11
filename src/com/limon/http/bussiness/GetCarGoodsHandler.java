package com.limon.http.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetCarGoodsReq;
import com.limon.http.coder.GetCarGoodsRsp;
import com.limon.http.model.CarGoods;
import com.limon.http.model.CarGoodsList;
import com.limon.http.model.GoodsLimit;
import com.limon.http.service.GetCarGoodsService;
import com.limon.http.service.GetShopGoodsBuyNumService;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.StoreAdService;
import com.limon.util.ConfigUtil;
import com.limon.util.JSONUtil;

public class GetCarGoodsHandler {
	private static final Logger log = Logger.getLogger(GetCarGoodsHandler.class);   
	private GetCarGoodsService service;
	private GetShopGoodsBuyNumService gservice;
	private StoreAdService storeAdService;
	private GetCarGoodsRsp rsp=new GetCarGoodsRsp();
	
	public GetCarGoodsHandler(GetCarGoodsService service,GetShopGoodsBuyNumService gservice,StoreAdService storeAdService){
		this.service=service;
		this.gservice=gservice;
		this.storeAdService=storeAdService;
	}

    public GetCarGoodsRsp execute(GetCarGoodsReq req,HttpServletRequest request){  
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	/**限时抢购时间段处理***********************************/
        	String fstime1=ConfigUtil.getConfig("fstime1").getConfig_value();
			String fetime1=ConfigUtil.getConfig("fetime1").getConfig_value();
			String fstime2=ConfigUtil.getConfig("fstime2").getConfig_value();
			String fetime2=ConfigUtil.getConfig("fetime2").getConfig_value();
			String fstime3=ConfigUtil.getConfig("fstime3").getConfig_value();
			String fetime3=ConfigUtil.getConfig("fetime3").getConfig_value();
			
			//时间处理
			SimpleDateFormat datesdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat datetimesdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d=new Date();//当前时间
			String fstime="";
			String fetime="";
			String tltime="";
			if(fstime1!=null&&!fstime1.equals("")&&fetime1!=null&&!fetime1.equals("")){
				//抢购1开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime1+":00");
				//抢购1结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime1+":00");
				//System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()){
					tltime="1,";
				}
				fstime1=datetimesdf.format(stime);
				fetime1=datetimesdf.format(etime);
				fstime=fstime1;
				fetime=fetime1;
			}
			if(fstime2!=null&&!fstime2.equals("")&&fetime2!=null&&!fetime2.equals("")){
				//抢购2开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime2+":00");
				//抢购2结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime2+":00");
				//System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					tltime="2,";
				}
				fstime2=datetimesdf.format(stime);
				fetime2=datetimesdf.format(etime);
				fstime=fstime2;
				fetime=fetime2;
			}
			if(fstime3!=null&&!fstime3.equals("")&&fetime3!=null&&!fetime3.equals("")){
				//抢购2开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime3+":00");
				//抢购2结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime3+":00");
				System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					tltime="3,";
				}
				fstime3=datetimesdf.format(stime);
				fetime3=datetimesdf.format(etime);
				fstime=fstime3;
				fetime=fetime3;
			}
			
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("userid",req.getUid());
        	map.put("pageStart",(req.getPage()-1)*req.getPageNum());
        	map.put("pageSize",req.getPageNum());
        	List<CarGoods> list=service.getCarGoods(map);
        	for(CarGoods goods:list){
        		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
        			goods.setImgurl(allPath+goods.getImgurl());
            	}else{
            		goods.setImgurl("");
            	}
        		
        		Map<String,Object> m=new HashMap<String,Object>();
            	m.put("uid",req.getUid());
            	m.put("pid",goods.getId());
            	m.put("sid",goods.getSid());
    			m.put("stime",fstime);
    			m.put("etime",fetime);
    			GoodsLimit gla=gservice.getBuyNum(m);
            	GoodsLimit glt=gservice.getLimitNum(m);
            	if(gla!=null){
            		goods.setBuynum(gla.getBuynum()+"");
            	}else{
            		goods.setBuynum("0");
            	}
            	if(glt!=null){
            		if(glt.getLimitnum()!=null&&glt.getLimitnum()>0){
            			goods.setLimitnum(glt.getLimitnum()+"");
            			goods.setIslimit("0");
            		}else{
            			goods.setLimitnum("0");
            			goods.setIslimit("1");
            		}
            	}else{
            		goods.setLimitnum("0");
        			goods.setIslimit("1");
            	}
            	
            	//获取原价
            	Map<String,Object> mp=new HashMap<String,Object>();
				mp.put("sid",goods.getSid());
				mp.put("pid",goods.getId());
				String price=storeAdService.getGoodsSalePrice(mp);
            	
            	List<String> tltimes=storeAdService.getTimeLimitProduct(goods.getId());
    			if(tltimes!=null&&tltimes.size()>0){
    				String tl=tltimes.get(0);
        			Date stime1=datetimesdf.parse(fstime1);
    				Date etime1=datetimesdf.parse(fetime1);
        			Date stime2=datetimesdf.parse(fstime2);
    				Date etime2=datetimesdf.parse(fetime2);
        			Date stime3=datetimesdf.parse(fstime3);
    				Date etime3=datetimesdf.parse(fetime3);
    				
					
					//活动验证
					TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
    				if(tl.indexOf("1,")!=-1&&d.getTime()>stime1.getTime()&&d.getTime()<etime1.getTime()){
    					if(timel==null){
    		    			goods.setPrice(price);
    					}
    				}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
    					if(timel==null){
    						goods.setPrice(price);
    					}
    				}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
    					if(timel==null){
    						goods.setPrice(price);
    					}
    				}else{
    					goods.setPrice(price);
    				}
    				
    			}else{
    				//活动验证
        			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(goods.getId()+"");
        			System.out.println(ap+"===================");
        			if(ap==null){
        				System.out.println(price+"==========================");
        				goods.setPrice(price);
        			}
    			}
        	}
        	CarGoodsList goodslist=new CarGoodsList();
        	goodslist.setList(list);
        	rsp.setResult("1");
            rsp.setGoodsList(JSON.toJSONString(goodslist,JSONUtil.features));
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setGoodsList("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
