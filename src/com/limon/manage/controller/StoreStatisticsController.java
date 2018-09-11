package com.limon.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.limon.base.controller.BaseController;
import com.limon.manage.service.StoreStatisticsService;
import com.limon.store.model.AreaCity;
import com.limon.store.model.AreaCounty;
import com.limon.store.model.AreaProvince;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.AreaCityService;
import com.limon.store.service.AreaCountyService;
import com.limon.store.service.AreaProvinceService;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

/**
 * 数据统计->便利店统计
 * 项目名称：limon   
 * 类名称：StoreStatisticsController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:23:04   
 * @version v1.0
 */
@Controller
@RequestMapping("/storestatistics")
public class StoreStatisticsController extends BaseController{
	@Autowired
	private StoreStatisticsService storeStatisticsService;
	@Autowired
	private AreaProvinceService areaProvinceService;
	@Autowired
	private AreaCityService areaCityService;
	@Autowired
	private AreaCountyService areaCountyService;
	/**
	 * 便利店列表
	 * @return
	 */
	@RequestMapping("/list")
    public String StoreStatisticsList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sstorename = this.getParaString("sstorename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
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
		
		Integer totalRecord = storeStatisticsService.getStoreInfoListCount(map);
		List<StoreInfo> storelist = storeStatisticsService.getStoreInfoList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(storelist);
		
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
		request.setAttribute("page",page);
		
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",storelist);
        return "/manage/storestatistics/list";
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
