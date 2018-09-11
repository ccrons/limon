package com.limon.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.limon.manage.model.SysPush;
import com.limon.manage.service.SysPushService;
import com.limon.store.model.AreaCity;
import com.limon.store.model.AreaCounty;
import com.limon.store.model.AreaProvince;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.AreaCityService;
import com.limon.store.service.AreaCountyService;
import com.limon.store.service.AreaProvinceService;
import com.limon.util.DateUtil;
import com.limon.util.JPushUtil;
import com.limon.util.JSONUtil;
/**
 * @author gqf
 *
 * 消息推送
 * 2015-2-25 下午02:40:46
 */
@Controller
@RequestMapping("/push")
public class JPushController extends BaseController{
	@Autowired
	private SysPushService sysPushService;
	@Autowired
	private AreaProvinceService areaProvinceService;
	@Autowired
	private AreaCityService areaCityService;
	@Autowired
	private AreaCountyService areaCountyService;
	/**
	 * 推送列表
	 * @return
	 */
	@RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response){
		
		//查询参数接收
		String rs=this.getParaString("rs");
		
		String content=this.getParaString("content");
		String stime=this.getParaString("stime");
		if(stime.equals("")){
			stime=DateUtil.getLastWeek();
		}
		String etime=this.getParaString("etime");
		if(etime.equals("")){
			etime=DateUtil.getToday();
		}
		
		//查询条件设置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("content",content);
		if(!stime.equals("")){
			map.put("stime",stime+" 00:00:00");
		}else{
			map.put("stime",stime);
		}
		if(!etime.equals("")){
			map.put("etime",etime+" 23:59:59");
		}else{
			map.put("etime",etime);
		}
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		//分页数据查询
		Integer totalRecord=sysPushService.getPushListCount(map);
		List<SysPush> loglist=sysPushService.getPushList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(loglist);
		
		//返回页面参数
		request.setAttribute("content",content);
		request.setAttribute("stime",stime);
		request.setAttribute("etime",etime);
		request.setAttribute("page",page);
		request.setAttribute("rs",rs);
        return "manage/push/list";
	}
	
	/**
	 * 用户推送添加页面
	 * @return
	 */
	@RequestMapping("/toaddpush")
    public String toaddpush(HttpServletRequest request, HttpServletResponse response){
		List<AreaProvince> provlist=areaProvinceService.getProvinceListAll();
		request.setAttribute("provlist",provlist);
        return "manage/push/add";
	}
	
	/**
	 * 用户消息发送
	 * @return
	 */
	@RequestMapping("/savepush")
    public String savepush(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String content=this.getParaString("content");
			String provincecode=this.getParaString("provincecode");
			String citycode=this.getParaString("citycode");
			String countycode=this.getParaString("countycode");
			String sid=this.getParaString("storeid");
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("provincecode", provincecode);
			map.put("citycode", citycode);
			map.put("countycode", countycode);
			map.put("sid", sid);
			List<String> slist=sysPushService.getStoresId(map);
			String tags="";
			for(int i=0;i<slist.size();i++){
				if((i+1)!=slist.size()){
					tags+="shop"+slist.get(i)+"tag"+",";
				}else{
					tags+="shop"+slist.get(i)+"tag";
				}
			}
			
			String r="";
			//用户消息推送
			if(!tags.equals("")){
				r=JPushUtil.pushToAppUserTag(tags, content);
				map.put("alias", tags);
				map.put("content", content);
				map.put("ptype", 2);
				map.put("createtime", DateUtil.getTodayTime());
				if(r.equals("success")){
					sysPushService.sysPushAdd(map);
					rs="1";
				}else{
					rs="2";
				}
			}else{
				rs="2";
			}
			map.put("content", content);
			map.put("ptype", 1);
			map.put("createtime", DateUtil.getTodayTime());
			if(r.equals("success")){
				sysPushService.sysPushAdd(map);
				rs="1";
			}else{
				rs="2";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		return "manage/push/add";
	}
	
	/**
	 * 商家推送添加页面
	 * @return
	 */
	@RequestMapping("/toaddpushsj")
    public String toaddpushsj(HttpServletRequest request, HttpServletResponse response){
        return "manage/push/addsj";
	}
	
	/**
	 * 商家消息发送
	 * @return
	 */
	@RequestMapping("/savepushsj")
    public String savepushsj(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String content=this.getParaString("content");
			Map<String,Object> map=new HashMap<String,Object>();
			List<String> slist=sysPushService.getStoreId();
			String regids="";
			for(int i=0;i<slist.size();i++){
				if((i+1)!=slist.size()){
					regids+="shop"+slist.get(i)+",";
				}else{
					regids+="shop"+slist.get(i);
				}
			}
			System.out.println("alias:"+regids);
			String r="";
			//商家消息推送
			if(!regids.equals("")){
				r=JPushUtil.pushToStore(regids, content);
				map.put("alias", regids);
				map.put("content", content);
				map.put("ptype", 2);
				map.put("createtime", DateUtil.getTodayTime());
				if(r.equals("success")){
					sysPushService.sysPushAdd(map);
					rs="1";
				}else{
					rs="2";
				}
			}else{
				rs="2";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		return "manage/push/addsj";
	}
	
	/**
	 * 消息删除
	 * @return
	 */
	@RequestMapping("/delpush")
    public String delpush(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String currentPage = this.getParaString("currentPage");
		String id=this.getParaString("id");
		String content=this.getParaString("content");
		String stime=this.getParaString("stime");
		String etime=this.getParaString("etime");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			sysPushService.sysPushDel(id);
			rs="1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list?currentPage=" + currentPage + "&content="+ URLEncoder.encode(content, "UTF-8") +"&stime=" +stime+"&etime="+etime+"&rs="+rs;
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
	 * ajax获取店铺
	 * @return
	 */
	@RequestMapping("/getStore")
    public void getStore(HttpServletRequest request, HttpServletResponse response){
		try{
			String countycode=this.getParaString("countycode");
			List<StoreInfo> storelist=sysPushService.getStoreBycountycode(countycode);
			String json=JSON.toJSONString(storelist,JSONUtil.features);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
}
