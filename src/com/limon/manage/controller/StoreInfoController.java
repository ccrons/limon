package com.limon.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.limon.manage.model.ProductInfo;
import com.limon.manage.service.ProductInfoService;
import com.limon.store.model.AreaCity;
import com.limon.store.model.AreaCounty;
import com.limon.store.model.AreaProvince;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.AreaCityService;
import com.limon.store.service.AreaCountyService;
import com.limon.store.service.AreaProvinceService;
import com.limon.store.service.ShelvesService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;

/**
 * @author gqf
 *
 * 便利店信息管理
 * 2015-2-10 上午10:32:56
 */
@Controller
@RequestMapping("/storeinfo")
public class StoreInfoController extends BaseController{
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ShelvesService shelvesService;
	@Autowired
	private AreaProvinceService areaProvinceService;
	@Autowired
	private AreaCityService areaCityService;
	@Autowired
	private AreaCountyService areaCountyService;
	/**
	 * 便利店信息列表
	 * @return
	 */
	@RequestMapping("/list")
    public String storeinfolist(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String storename = this.getParaString("storename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String isopen = this.getParaString("isopen");
		String rs = this.getParaString("rs");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("storename",storename);
		map.put("provincecode",provincecode);
		map.put("citycode",citycode);
		map.put("countycode",countycode);
		map.put("isopen",isopen);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=storeInfoService.getStoreInfoListCount(map);
		List<StoreInfo> storelist = storeInfoService.getStoreInfoList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(storelist);
		
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		List<AreaCity> citylist=areaCityService.getCityByProvId(provincecode);
		List<AreaCounty> countylist=areaCountyService.getCountyByCityId(citycode);
		
		//返回页面参数
		request.setAttribute("storename", storename);
		request.setAttribute("provincecode", provincecode);
		request.setAttribute("citycode", citycode);
		request.setAttribute("countycode", countycode);
		request.setAttribute("isopen", isopen);
		request.setAttribute("rs", rs);
		request.setAttribute("provlist",provlist);
		request.setAttribute("citylist",citylist);
		request.setAttribute("countylist",countylist);
		request.setAttribute("page",page);
		
        return "/manage/storeinfo/list";
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
	/**
	 * 便利店信息编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String storeinfoedit(HttpServletRequest request, HttpServletResponse response){
		Integer id = this.getParaInteger("id");
		StoreInfo store = storeInfoService.getStoreInfoById(id);
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		List<AreaCity> citylist=areaCityService.getCityByProvId(store.getProvincecode());
		List<AreaCounty> countylist=areaCountyService.getCountyByCityId(store.getCitycode());
		request.setAttribute("store",store);
		request.setAttribute("provlist",provlist);
		request.setAttribute("citylist",citylist);
		request.setAttribute("countylist",countylist);
        return "/manage/storeinfo/edit";
    }
	/**
	 * 便利店信息添加页面
	 * @return
	 */
	@RequestMapping("/add")
    public String storeinfoadd(HttpServletRequest request, HttpServletResponse response){
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		request.setAttribute("provlist",provlist);
		return "/manage/storeinfo/add";
    }
	
	/**
	 * 便利店信息查看页面
	 * @return
	 */
	@RequestMapping("/info")
    public String storeinfo(HttpServletRequest request, HttpServletResponse response){
		Integer id = this.getParaInteger("id");
		StoreInfo store = storeInfoService.getStoreInfoById(id);
		request.setAttribute("store",store);
        return "/manage/storeinfo/info";
    }
	
	/**
	 * 便利店名称校验
	 * @return
	 */
	@RequestMapping("/validStoreName")
    public void validStoreName(HttpServletRequest request, HttpServletResponse response){
		try{
			Integer storeid=this.getParaInteger("id");
			String testname=this.getParaString("param");
			Integer num=storeInfoService.getIsUsedStroeName(testname);
			if(storeid==0){
				if(num==0){
					response.getWriter().write("y");
				}else{
					response.getWriter().write("便利店名称已存在");
				}
			}else{
				StoreInfo store=storeInfoService.getStoreInfoById(storeid);
				if(testname.equals(store.getStorename())){
					response.getWriter().write("y");
				}else{
					if(num==0){
						response.getWriter().write("y");
					}else{
						response.getWriter().write("便利店名称已存在");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	/**
	 * 便利店名称校验
	 * @return
	 */
	@RequestMapping("/validStoreUserName")
    public void validStoreUserName(HttpServletRequest request, HttpServletResponse response){
		try{
			Integer storeid=this.getParaInteger("id");
			String testname=this.getParaString("param");
			Integer num=storeInfoService.getIsUsedStroeUserName(testname);
			if(storeid==0){
				if(num==0){
					response.getWriter().write("y");
				}else{
					response.getWriter().write("便利店账号已存在");
				}
			}else{
				StoreInfo store=storeInfoService.getStoreInfoById(storeid);
				if(testname.equals(store.getStorename())){
					response.getWriter().write("y");
				}else{
					if(num==0){
						response.getWriter().write("y");
					}else{
						response.getWriter().write("便利店账号已存在");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	/**
	 * 便利店信息保存
	 * @return
	 */
	@RequestMapping("/save")
    public String storeinfosave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		String returnurl="";
		Integer id = this.getParaInteger("id");
		String storename = this.getParaString("storename");
		String username = this.getParaString("username");
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
		String bussiness_stime = this.getParaString("bussiness_stime");
		String bussiness_etime = this.getParaString("bussiness_etime");
		String send_stime = this.getParaString("send_stime");
		String send_etime = this.getParaString("send_etime");
		String lat = this.getParaString("lat");
		String lng = this.getParaString("lng");
		Integer isopen = this.getParaInteger("isopen");
		Integer isself = this.getParaInteger("isself");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("storename", storename);
			map.put("username", username);
			map.put("password", CommonUtil.md5(this.defaultPassword));
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
			map.put("bussiness_stime", bussiness_stime);
			map.put("bussiness_etime", bussiness_etime);
			map.put("send_stime", send_stime);
			map.put("send_etime", send_etime);
			map.put("lat", lat);
			map.put("lng", lng);
			map.put("isopen", isopen);
			map.put("isself", isself);
			map.put("createtime", DateUtil.getTodayTime());
			if(id==0){
				storeInfoService.addStoreInfo(map);
				Map<String, Object> rolemap = new HashMap<String, Object>();
				rolemap.put("storeid", map.get("id"));
				if(isself==1){
					rolemap.put("roleid", 3);
				}else{
					rolemap.put("roleid", 2);
				}
				//添加便利店角色表
				storeInfoService.addStoreRole(rolemap);
				
				//上架所有官方供应商品
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				String createtime = sdf.format(new Date());
				List<ProductInfo> pil = productInfoService.getOfficialProductInfoList();
				for (int i = 0; i < pil.size(); i++) {
					Map<String, Object> spmap = new HashMap<String, Object>();
					spmap.put("uid", map.get("id"));
					spmap.put("id", pil.get(i).getId());
					spmap.put("salenum", pil.get(i).getUpnum());
					spmap.put("saleprice", pil.get(i).getPrice());
					spmap.put("createtime", createtime);
					spmap.put("isdel", "0");
					shelvesService.storeProductAdd(spmap);//上架
				}
				LogUtil.logOperation("添加便利店信息:"+storename+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				returnurl="/manage/storeinfo/add";
			}else{
				storeInfoService.updateStoreInfo(map);
				LogUtil.logOperation("修改便利店信息:"+storename+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				returnurl="/manage/storeinfo/edit";
			}
			rs="1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//新增编辑页面地址参数
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		request.setAttribute("provlist",provlist);
		if(id!=0){
			StoreInfo store=storeInfoService.getStoreInfoById(id);
			List<AreaCity> citylist=areaCityService.getCityByProvId(store.getProvincecode());
			List<AreaCounty> countylist=areaCountyService.getCountyByCityId(store.getCitycode());
			request.setAttribute("store",store);
			request.setAttribute("citylist",citylist);
			request.setAttribute("countylist",countylist);
		}
		request.setAttribute("rs", rs);
		return returnurl;
	}
	
	/**
	 * 便利店信息删除
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/del")
    public String storeinfodel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		//参数接收
		Integer id = this.getParaInteger("id");
		String storename = this.getParaString("storename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String isopen = this.getParaString("isopen");
		String currentPage = this.getParaString("currentPage");
		try{
			//查询条件
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("storename",storename);
			map.put("provincecode",provincecode);
			map.put("citycode",citycode);
			map.put("countycode",countycode);
			map.put("isopen",isopen);
			storeInfoService.delStoreInfoById(id);
			rs="1";
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "redirect:list?currentPage="+currentPage+"&storename="+URLEncoder.encode(storename,"UTF-8")+"&rs="+rs+"&provincecode="+provincecode+"&citycode="+citycode+"&countycode="+countycode+"&isopen="+isopen;
    }
	/**
	 * 便利店状态更新
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/changestatus")
    public String changestatus(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="2";
		//参数接收
		Integer id = this.getParaInteger("id");
		String storename = this.getParaString("storename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String isopen = this.getParaString("isopen");
		String cisopen = this.getParaString("cisopen");
		String currentPage = this.getParaString("currentPage");
		try{
			//查询条件
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			map.put("cisopen",cisopen);
			storeInfoService.updateStoreInfoStatus(map);
			rs="3";
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "redirect:list?currentPage="+currentPage+"&storename="+URLEncoder.encode(storename,"UTF-8")+"&rs="+rs+"&provincecode="+provincecode+"&citycode="+citycode+"&countycode="+countycode+"&isopen="+isopen;
    }
	
	/**
	 * 便利店密码重置
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/resetpwd")
    public String resetpwd(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="2";
		//参数接收
		Integer id = this.getParaInteger("id");
		String storename = this.getParaString("storename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String isopen = this.getParaString("isopen");
		String currentPage = this.getParaString("currentPage");
		try{
			storeInfoService.updatePassword(id+"", CommonUtil.md5(this.defaultPassword));
			rs="3";
		}catch(Exception e){
			e.printStackTrace();
		}	
		return "redirect:list?currentPage="+currentPage+"&storename="+URLEncoder.encode(storename,"UTF-8")+"&rs="+rs+"&provincecode="+provincecode+"&citycode="+citycode+"&countycode="+countycode+"&isopen="+isopen;
    }
}
