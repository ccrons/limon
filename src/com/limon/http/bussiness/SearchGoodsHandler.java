package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.SearchGoodsReq;
import com.limon.http.coder.SearchGoodsRsp;
import com.limon.http.model.GoodsLimit;
import com.limon.http.model.SearchGoods;
import com.limon.http.model.SearchGoodsList;
import com.limon.http.service.SearchGoodsService;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.StoreAdService;
import com.limon.util.JSONUtil;

public class SearchGoodsHandler {
	private static final Logger log = Logger.getLogger(SearchGoodsHandler.class);   
	private SearchGoodsService service;
	private StoreAdService storeAdService;
	private SearchGoodsRsp rsp=new SearchGoodsRsp();
	
	public SearchGoodsHandler(SearchGoodsService service,StoreAdService storeAdService){
		this.service=service;
		this.storeAdService=storeAdService;
	}

    public SearchGoodsRsp execute(SearchGoodsReq req,HttpServletRequest request){    
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        try{
        	//String uid=req.getUid();
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("name", req.getName());
        	map.put("storeid",req.getSid());
        	Integer pageStart=(Integer.parseInt(req.getPage())-1)*Integer.parseInt(req.getPageNum());
        	Integer pageSize=Integer.parseInt(req.getPageNum());
        	map.put("userid",req.getUid());
        	map.put("pageStart", pageStart);
        	map.put("pageSize", pageSize);
        	List<SearchGoods> glist=service.searchGoods(map);
        	for(SearchGoods g:glist){
        		g.setImgurl(allPath+g.getImgurl());
        		g.setInfourl(allPath+"spinfo?sid="+req.getSid()+"&pid="+g.getId());
        		g.setType(g.getIsfirst());
        		map.put("pid",g.getId());
        		GoodsLimit gla=service.getAdProduct(map);
        		if(gla!=null&&gla.getAdnum()>0){
        			g.setIsad("0");
        		}else{
        			g.setIsad("1");
        		}
        		GoodsLimit glt=service.getTimelimit(map);
        		if(glt!=null&&glt.getLimitnum()>0){
        			g.setIslimit("0");
        		}else{
        			g.setIslimit("1");
        		}
        		StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(g.getId()+"");
    			if(ap!=null){
    				g.setPrice(ap.getAdprice()+"");
    			}
    			TimeLimit tl = storeAdService.getTimeLimitByProductId(g.getId()+"");
    			if(tl!=null){
    				g.setPrice(tl.getTlprice()+"");
    			}
        	}
        	SearchGoodsList goodslist=new SearchGoodsList();
        	goodslist.setList(glist);
        	String goodsliststr=JSON.toJSONString(glist,JSONUtil.features);
        	Integer goodscount=service.searchGoodsCount(map);
        	rsp.setResult("1");
        	rsp.setRecordNum(goodscount);
        	rsp.setGoodsList(goodsliststr);
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
