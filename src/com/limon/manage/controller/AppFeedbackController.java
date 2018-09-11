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
import com.limon.manage.model.AppFeedback;
import com.limon.manage.service.AppFeedbackService;
/**
 * 系统设置->意见反馈
 * 项目名称：limon   
 * 类名称：FeedbackController
 * 创建人：WN	
 * 创建时间：2015年7月23日 下午9:44:04   
 * @version v1.0
 */
@Controller
@RequestMapping("/appfeedback")
public class AppFeedbackController extends BaseController{
	@Autowired
	private AppFeedbackService appfeedbackService;
	
	
	/**
	 * 日志查询
	 * @return
	 */
	@RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String scontent = this.getParaString("scontent");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("scontent",scontent);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=appfeedbackService.getFeedbackListCount(map);
		List<AppFeedback> feedbacklist = appfeedbackService.getFeedbackList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(feedbacklist);
		//返回页面参数
		request.setAttribute("scontent", scontent);
		request.setAttribute("page",page);
				
		request.setAttribute("datas",feedbacklist);
        return "/manage/appfeedback/list";
	}
	
	
}
