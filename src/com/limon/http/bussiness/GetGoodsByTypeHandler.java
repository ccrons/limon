package com.limon.http.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.base.service.SysConfigService;
import com.limon.http.coder.GetGoodsByTypeReq;
import com.limon.http.coder.GetGoodsByTypeRsp;
import com.limon.http.model.Goods;
import com.limon.http.model.GoodsLimit;
import com.limon.http.model.GoodsList;
import com.limon.http.service.GetGoodsByTypeService;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.StoreAdService;
import com.limon.util.ConfigUtil;
import com.limon.util.JSONUtil;

public class GetGoodsByTypeHandler {
	private static final Logger log = Logger.getLogger(GetGoodsByTypeHandler.class);   
	private GetGoodsByTypeService service;
	private StoreAdService storeAdService;
	private GetGoodsByTypeRsp rsp=new GetGoodsByTypeRsp();
	
	public GetGoodsByTypeHandler(GetGoodsByTypeService service,StoreAdService storeAdService){
		this.service=service;
		this.storeAdService=storeAdService;
	}

    public GetGoodsByTypeRsp execute(GetGoodsByTypeReq req,HttpServletRequest request){
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	//String uid=req.getUid();
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("storeid", req.getSid());
        	map.put("typeid", req.getType());
        	map.put("ftypeid", req.getFid());
        	map.put("pageStart",(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum()));
        	map.put("pageSize",Integer.parseInt(req.getPageNum()));
        	List<Goods> glist=service.getGoodsByType(map);
        	for(Goods goods:glist){
        		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
        			goods.setImgurl(allPath+goods.getImgurl());
            	}else{
            		goods.setImgurl("");
            	}
        		goods.setInfourl(allPath+"spinfo?sid="+req.getSid()+"&pid="+goods.getId());
        		goods.setType(goods.getIsfirst());
        		map.put("pid",goods.getId());
        		GoodsLimit gla=service.getAdProduct(map);
        		if(gla!=null&&gla.getAdnum()>0){
        			goods.setIsad("0");
        		}else{
        			goods.setIsad("1");
        		}
        		GoodsLimit glt=service.getTimelimit(map);
        		if(glt!=null&&glt.getLimitnum()>0){
        			goods.setIslimit("0");
        		}else{
        			goods.setIslimit("1");
        		}
        		
        		StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(goods.getId()+"");
    			if(ap!=null){
    				goods.setPrice(ap.getAdprice()+"");
    			}
    			
    			List<String> tltimes=service.getTimeLimitProduct(goods.getId());
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
    				
    				if(tl.indexOf("1,")!=-1&&d.getTime()>stime1.getTime()&&d.getTime()<etime1.getTime()){
    					//活动验证
    	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
    					if(timel!=null){
    						goods.setPrice(timel.getTlprice()+"");
    					}
    				}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
    					//活动验证
    	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
    					if(timel!=null){
    						goods.setPrice(timel.getTlprice()+"");
    					}
    				}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
    					//活动验证
    	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
    					if(timel!=null){
    						goods.setPrice(timel.getTlprice()+"");
    					}
    				}
    			}
        	}
        	Integer recordNum=service.getGoodsByTypeCount(map);
        	GoodsList goodslist=new GoodsList();
        	goodslist.setList(glist);
        	String goodsliststr=JSON.toJSONString(goodslist,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setGoodsList(goodsliststr);
        	rsp.setErrorMsg("");
        	rsp.setRecordNum(recordNum+"");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setGoodsList("");
            rsp.setRecordNum("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
