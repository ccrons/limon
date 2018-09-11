package com.limon.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.controller.BaseController;
import com.limon.manage.model.AppUser;
import com.limon.manage.service.AppUserStatisticsService;
import com.limon.util.DateUtil;

/**
 * 数据统计->用户统计
 * 项目名称：limon   
 * 类名称：AppUserStatisticsController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:23:04   
 * @version v1.0
 */
@Controller
@RequestMapping("/appuserstatistics")
public class AppUserStatisticsController extends BaseController{
	@Autowired
	private AppUserStatisticsService appUserStatisticsService;
	/**
	 * app用户列表
	 * @return
	 */
	@RequestMapping("/list")
    public String AppUserStatisticsList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String susername = this.getParaString("susername");
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
		map.put("susername",susername);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = appUserStatisticsService.getAppUserListCount(map);
		List<AppUser> appuserlist = appUserStatisticsService.getAppUserList(map);
		Integer allRecord = appUserStatisticsService.getAppUserAllCount();
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(appuserlist);
		
		//返回页面参数
		request.setAttribute("stime", stime);
		request.setAttribute("etime", etime);
		request.setAttribute("susername", susername);
		request.setAttribute("page",page);
		request.setAttribute("allRecord",allRecord);
		
		request.setAttribute("datas",appuserlist);
        return "/manage/appuserstatistics/list";
    }
	
}
