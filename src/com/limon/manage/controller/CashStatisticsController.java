package com.limon.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.limon.base.common.LogUtil;
import com.limon.base.controller.BaseController;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.StoreCash;
import com.limon.manage.service.PriceStatisticsService;
import com.limon.store.model.AreaCity;
import com.limon.store.model.AreaCounty;
import com.limon.store.model.AreaProvince;
import com.limon.store.model.OrderInfo;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.AreaCityService;
import com.limon.store.service.AreaCountyService;
import com.limon.store.service.AreaProvinceService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;
import com.limon.wx.service.ShopService;

/**
 * 数据统计->销售额统计
 * 项目名称：limon   
 * 类名称：PriceStatisticsController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:23:04   
 * @version v1.0
 */
@Controller
@RequestMapping("/cashstatistics")
public class CashStatisticsController extends BaseController{
	@Autowired
	private PriceStatisticsService pricesStatisticsService;
	@Autowired
	private AreaProvinceService areaProvinceService;
	@Autowired
	private AreaCityService areaCityService;
	@Autowired
	private AreaCountyService areaCountyService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private StoreInfoService storeInfoService;
	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping("/list")
    public String PriceStatisticsList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sstorename = this.getParaString("sstorename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String rs=this.getParaString("rs");
		if(stime.equals("")){
			stime=DateUtil.getLastWeek();
		}
		if(etime.equals("")){
			etime=DateUtil.getToday();
		}
		
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("stime",stime);
		map.put("etime",etime);
		map.put("sstorename",sstorename);
		map.put("provincecode",provincecode);
		map.put("citycode",citycode);
		map.put("countycode",countycode);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = pricesStatisticsService.getStoreStoreCashListCount(map);
		List<StoreCash> cashlist = pricesStatisticsService.getStoreStoreCashList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(cashlist);
		
		//城市列表
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		List<AreaCity> citylist=areaCityService.getCityByProvId(provincecode);
		List<AreaCounty> countylist=areaCountyService.getCountyByCityId(citycode);
		request.setAttribute("provlist",provlist);
		request.setAttribute("citylist",citylist);
		request.setAttribute("countylist",countylist);
		//返回页面参数
		request.setAttribute("stime", stime);
		request.setAttribute("etime", etime);
		request.setAttribute("sstorename", sstorename);
		request.setAttribute("provincecode", provincecode);
		request.setAttribute("citycode", citycode);
		request.setAttribute("countycode", countycode);
		request.setAttribute("rs",rs);
		request.setAttribute("page",page);
		request.setAttribute("datas",cashlist);
        return "/manage/cashstatistics/list";
    }
	
	/**
	 * 订单列表
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/storesum")
    public String storesum(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	    //获取前月的第一天
        Calendar cal=Calendar.getInstance();//获取当前日期 
        cal.add(Calendar.MONTH,-1);
        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(cal.getTime());
	    //获取前月的最后一天
        cal.add(Calendar.MONTH,1);
	    cal.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
	    String lastDay = format.format(cal.getTime());
		List<StoreInfo> slist=storeInfoService.getStoreInfoListOpen();
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sstorename = this.getParaString("sstorename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		if(stime.equals("")){
			stime=firstDay;
		}
		if(etime.equals("")){
			etime=lastDay;
		}
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("stime",firstDay);
		map.put("etime",lastDay);
		for(StoreInfo s:slist){
			map.put("storeid",s.getId());
			Double storelirun=0.0;
			List<OrderInfo> orderlist = pricesStatisticsService.getStoreOrderList(map);
			for(OrderInfo o:orderlist){
				List<OrderProductGet> plist=shopService.getProductListByOid(o.getId());
				for(OrderProductGet og:plist){
					ProductInfo p=shopService.getGoosById(og.getGid());
					//销售价格-代理价格=代理利润
					storelirun+=(Double.parseDouble(og.getOpprice())-p.getProxyprice())*og.getCount();
				}
			}
			map.put("storeid",s.getId());
			map.put("createtime",DateUtil.getTodayTime());
			map.put("cashnum",storelirun);
			map.put("cashtype",s.getCashtype());
			map.put("cashaccount",s.getCashaccount());
			map.put("cashtime",firstDay+"-"+lastDay);
			map.put("cashstatus",0);
			
			Integer i=pricesStatisticsService.getCashByStoreidAndTime(map);
			if(i==0){
				pricesStatisticsService.saveStoreCash(map);
			}
		}
		rs="1";
		return "redirect:list?currentPage=1&sstorename="+URLEncoder.encode(sstorename,"UTF-8")+"&stime="+stime+"&etime="+etime+"&provincecode="+provincecode+"&citycode="+citycode+"&countycode="+countycode+"&rs="+rs;
    }
	
	/**
	 * 更新状态
	 * @return
	 */
	@RequestMapping("/change")
    public String change(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sstorename = this.getParaString("sstorename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String currentPage=this.getParaString("currentPage");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			map.put("cashstatus",2);
			pricesStatisticsService.updateStoreCashStatus(map);
			rs = "1";
		} catch (Exception e) {
			rs = "0";
		}
		return "redirect:list?currentPage="+currentPage+"&sstorename="+URLEncoder.encode(sstorename,"UTF-8")+"&stime="+stime+"&etime="+etime+"&provincecode="+provincecode+"&citycode="+citycode+"&countycode="+countycode+"&rs="+rs;
		
    }
	/**
	 * ajax获取省
	 * @return
	 */
	@RequestMapping("/getProvince")
    public void getProvince(HttpServletRequest request, HttpServletResponse response){
		try{
			List<AreaProvince> plist=areaProvinceService.getProvinceListAll();
			String json=JSON.toJSONString(plist,JSONUtil.features);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	/**
	 * ajax获取市
	 * @return
	 */
	@RequestMapping("/getCity")
    public void getCity(HttpServletRequest request, HttpServletResponse response){
		try{
			Integer provcode=this.getParaInteger("provcode");
			List<AreaCity> citylist=areaCityService.getCityByProvId(provcode);
			String json=JSON.toJSONString(citylist,JSONUtil.features);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	/**
	 * ajax获取区
	 * @return
	 */
	@RequestMapping("/getCoty")
    public void getCounty(HttpServletRequest request, HttpServletResponse response){
		try{
			Integer citycode=this.getParaInteger("citycode");
			List<AreaCounty> countylist=areaCountyService.getCountyByCityId(citycode);
			String json=JSON.toJSONString(countylist,JSONUtil.features);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
