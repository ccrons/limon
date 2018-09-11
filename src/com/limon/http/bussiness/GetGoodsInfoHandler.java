package com.limon.http.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetGoodsInfoReq;
import com.limon.http.coder.GetGoodsInfoRsp;
import com.limon.http.model.GoodsInfo;
import com.limon.http.service.GetGoodsInfoService;
import com.limon.http.service.GetShopGoodsBuyNumService;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.StoreAdService;
import com.limon.util.ConfigUtil;
import com.limon.util.JSONUtil;

public class GetGoodsInfoHandler {
	private static final Logger log = Logger.getLogger(GetGoodsInfoHandler.class);   
	private GetGoodsInfoService service;
	private StoreAdService storeAdService;
	private GetGoodsInfoRsp rsp=new GetGoodsInfoRsp();
	
	public GetGoodsInfoHandler(GetGoodsInfoService service,StoreAdService storeAdService){
		this.service=service;
		this.storeAdService=storeAdService;
	}

    public GetGoodsInfoRsp execute(GetGoodsInfoReq req,HttpServletRequest request){  
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	//String uid=req.getUid();
        	Map<String,String> map=new HashMap<String,String>();
        	map.put("storeid", req.getSid());
        	map.put("productid", req.getGid());
        	GoodsInfo goodsinfo=service.getGoodsInfo(map);
        	if(goodsinfo.getImgurls()!=null&&!goodsinfo.getImgurls().equals("")){
        		goodsinfo.setImgurls(allPath+goodsinfo.getImgurls());
        	}else{
        		goodsinfo.setImgurls("");
        	}
        	if(goodsinfo.getPic1()!=null&&!goodsinfo.getPic1().equals("")){
        		goodsinfo.setPic1(allPath+goodsinfo.getPic1());
        	}else{
        		goodsinfo.setPic1("");
        	}
        	if(goodsinfo.getPic2()!=null&&!goodsinfo.getPic2().equals("")){
        		goodsinfo.setPic2(allPath+goodsinfo.getPic2());
        	}else{
        		goodsinfo.setPic2("");
        	}
        	if(goodsinfo.getPic3()!=null&&!goodsinfo.getPic3().equals("")){
        		goodsinfo.setPic3(allPath+goodsinfo.getPic3());
        	}else{
        		goodsinfo.setPic3("");
        	}
        	if(goodsinfo.getPic4()!=null&&!goodsinfo.getPic4().equals("")){
        		goodsinfo.setPic4(allPath+goodsinfo.getPic4());
        	}else{
        		goodsinfo.setPic4("");
        	}
        	if(goodsinfo.getPic5()!=null&&!goodsinfo.getPic5().equals("")){
        		goodsinfo.setPic5(allPath+goodsinfo.getPic5());
        	}else{
        		goodsinfo.setPic5("");
        	}
        	goodsinfo.setInfourl(allPath+"spinfo?sid="+req.getSid()+"&pid="+req.getGid());
        	map.put("userid",req.getUid());
        	Integer s=service.getIsFav(map);
        	if(s>0){
        		goodsinfo.setIsfav("0");
        	}else{
        		goodsinfo.setIsfav("1");
        	}
        	
        	//活动验证
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(req.getGid());
			if(ap!=null){
				goodsinfo.setPrice(ap.getAdprice()+"");
			}
        	
        	List<String> tltimes=service.getTimeLimitProduct(Integer.parseInt(req.getGid()));
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
	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(req.getGid());
					if(timel!=null){
		    			goodsinfo.setPrice(timel.getTlprice()+"");
					}
				}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
					//活动验证
	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(req.getGid());
					if(timel!=null){
		    			goodsinfo.setPrice(timel.getTlprice()+"");
					}
				}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
					//活动验证
	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(req.getGid());
					if(timel!=null){
		    			goodsinfo.setPrice(timel.getTlprice()+"");
					}
				}
			}
        	String goodsinfostr=JSON.toJSONString(goodsinfo,JSONUtil.features);
        	rsp.setResult(1);
        	rsp.setGoodsInfo(goodsinfostr);
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult(0);
            rsp.setGoodsInfo("");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
