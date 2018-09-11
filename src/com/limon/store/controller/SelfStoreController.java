package com.limon.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.controller.BaseController;
import com.limon.store.model.StoreAdInfo;
import com.limon.store.model.StoreDiscount;
import com.limon.store.service.SelfStoreService;

/**
 * 店铺管理->折扣管理、广告管理
 * 项目名称：limon   
 * 类名称：StoreInfoController
 * 创建人：WN	
 * 创建时间：2015年7月11日 下午2:52:42   
 * @version v1.0
 */
@Controller
@RequestMapping("/self")
public class SelfStoreController extends BaseController{
	@Autowired
	private SelfStoreService selfStoreService;
	
	/**
	 * 自营店折扣管理
	 * @return
	 */
	@RequestMapping("/salelist")
    public String salelist(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		//参数接收
		String rs = this.getParaString("rs");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("storeid", uid);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		Integer totalRecord = selfStoreService.getSaleInfoListCount(map);
		List<StoreDiscount> salelist = selfStoreService.getSaleInfoList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(salelist);
		//返回页面参数
		request.setAttribute("page",page);
		request.setAttribute("rs", rs);
        return "/store/storeinfo/salelist";
    }
	
	
	
	/**
	 * 自营店广告管理
	 * @return
	 */
	@RequestMapping("/adlist")
    public String adlist(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		//参数接收
		String rs = this.getParaString("rs");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("storeid", uid);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		Integer totalRecord = selfStoreService.getAdInfoListCount(map);
		List<StoreAdInfo> salelist = selfStoreService.getAdInfoList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(salelist);
		//返回页面参数
		request.setAttribute("page",page);
		request.setAttribute("rs", rs);
        return "/store/storeinfo/adlist";
    }
	
}
