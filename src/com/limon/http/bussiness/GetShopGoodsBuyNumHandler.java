package com.limon.http.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.limon.http.coder.GetShopGoodsBuyNumReq;
import com.limon.http.coder.GetShopGoodsBuyNumRsp;
import com.limon.http.model.GoodsLimit;
import com.limon.http.service.GetShopGoodsBuyNumService;
import com.limon.util.ConfigUtil;

public class GetShopGoodsBuyNumHandler {
	private static final Logger log = Logger.getLogger(GetShopGoodsBuyNumHandler.class);   
	private GetShopGoodsBuyNumService service;
	private GetShopGoodsBuyNumRsp rsp=new GetShopGoodsBuyNumRsp();
	
	public GetShopGoodsBuyNumHandler(GetShopGoodsBuyNumService service){
		this.service=service;
	}

    public GetShopGoodsBuyNumRsp execute(GetShopGoodsBuyNumReq req,HttpServletRequest request){ 
    	try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("uid",req.getUid());
        	map.put("pid", req.getGid());
        	map.put("sid",req.getSid());
        	
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
				//System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					tltime="3,";
				}
				fstime3=datetimesdf.format(stime);
				fetime3=datetimesdf.format(etime);
				fstime=fstime3;
				fetime=fetime3;
			}
			map.put("stime",fstime);
			map.put("etime",fetime);
			GoodsLimit gla=service.getBuyNum(map);
        	
        	GoodsLimit glt=service.getLimitNum(map);
        	rsp.setResult("1");
        	if(gla!=null){
        		rsp.setBuyNum(gla.getBuynum());
        	}else{
        		rsp.setBuyNum(0);
        	}
        	if(glt!=null){
        		rsp.setLimitNum(glt.getLimitnum());
        		rsp.setTotalNum(glt.getTotalnum());
        	}else{
        		rsp.setLimitNum(0);
        		rsp.setTotalNum(0);
        	}
            rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setBuyNum(0);
            rsp.setLimitNum(0);
            rsp.setTotalNum(0);
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}
