package com.limon.store.controller;

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
import com.limon.store.model.AreaCity;
import com.limon.store.model.AreaCounty;
import com.limon.store.model.AreaProvince;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.AreaCityService;
import com.limon.store.service.AreaCountyService;
import com.limon.store.service.AreaProvinceService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.JSONUtil;

/**
 * 店铺管理->信息维护
 * 项目名称：limon   
 * 类名称：StoreInfoController
 * 创建人：WN	
 * 创建时间：2015年7月11日 下午2:52:42   
 * @version v1.0
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseController{
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private AreaProvinceService areaProvinceService;
	@Autowired
	private AreaCityService areaCityService;
	@Autowired
	private AreaCountyService areaCountyService;
	/**
	 * 店铺信息维护页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String storeEdit(HttpServletRequest request, HttpServletResponse response){
		StoreInfo storeinfo = storeInfoService.getStoreInfoById(this.getLoginUser().getId());
		request.setAttribute("storeinfo",storeinfo);
		
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		List<AreaCity> citylist=areaCityService.getCityByProvId(storeinfo.getProvincecode());
		List<AreaCounty> countylist=areaCountyService.getCountyByCityId(storeinfo.getCitycode());
		request.setAttribute("provlist",provlist);
		request.setAttribute("citylist",citylist);
		request.setAttribute("countylist",countylist);
        return "/store/storeinfo/edit";
    }
	/**
	 * 店铺信息保存页面
	 * @return
	 */
	@RequestMapping("/save")
    public String storeinfosave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		Integer id = this.getParaInteger("id");
		String contact = this.getParaString("contact");
		String mobile = this.getParaString("mobile");
		String storemobile = this.getParaString("storemobile");
		String provincecode = this.getParaString("provincecode");
		String citycode = this.getParaString("citycode");
		String countycode = this.getParaString("countycode");
		String address = this.getParaString("address");
		Integer startprice = this.getParaInteger("startprice");
		Integer sendprice = this.getParaInteger("sendprice");
		Integer distance = this.getParaInteger("distance");
		String cashaccount = this.getParaString("cashaccount");
		String bussiness_stime = this.getParaString("bussiness_stime");
		String bussiness_etime = this.getParaString("bussiness_etime");
		String send_stime = this.getParaString("send_stime");
		String send_etime = this.getParaString("send_etime");
		String password = this.getParaString("password");
		String password2 = this.getParaString("password2");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("contact", contact);
		map.put("mobile", mobile);
		map.put("storemobile", storemobile);
		map.put("provincecode", provincecode);
		map.put("citycode", citycode);
		map.put("countycode", countycode);
		map.put("address", address);
		map.put("startprice", startprice);
		map.put("sendprice", sendprice);
		map.put("distance", distance);
		map.put("cashaccount", cashaccount);
		map.put("bussiness_stime", bussiness_stime);
		map.put("bussiness_etime", bussiness_etime);
		map.put("send_stime", send_stime);
		map.put("send_etime", send_etime);
		if(password!=null && password2!=null && !password.equals("") && password.equals(password2)){
			map.put("password", CommonUtil.md5(password));
		}
		
		storeInfoService.StoreEdit(map);
		rs = "1";
		StoreInfo storeinfo = storeInfoService.getStoreInfoById(this.getLoginUser().getId());
		request.setAttribute("storeinfo",storeinfo);
		
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		List<AreaCity> citylist=areaCityService.getCityByProvId(storeinfo.getProvincecode());
		List<AreaCounty> countylist=areaCountyService.getCountyByCityId(storeinfo.getCitycode());
		request.setAttribute("provlist",provlist);
		request.setAttribute("citylist",citylist);
		request.setAttribute("countylist",countylist);
		
		LogUtil.logOperation("修改便利店信息:"+storeinfo.getStorename()+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		request.setAttribute("rs",rs);
		return "/store/storeinfo/edit";
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
