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
import com.limon.manage.model.ProductInfo;
import com.limon.store.model.OrderInfo;
import com.limon.store.service.OrderInfoService;
import com.limon.util.DateUtil;

/**
 * 数据统计->历史订单
 * 项目名称：limon   
 * 类名称：HistoryOrderInfoController
 * 创建人：WN	
 * 创建时间：2015年7月11日 下午10:14:24   
 * @version v1.0
 */
@Controller
@RequestMapping("/historyorderinfo")
public class HistoryOrderInfoController extends BaseController{
	@Autowired
	private OrderInfoService orderInfoService;
	
	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping("/list")
    public String HistoryOrderList(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sorderno = this.getParaString("sorderno");//订单编号
		String sstatus = this.getParaString("sstatus");//状态
		if(stime.equals("")){
			//stime=DateUtil.getLastWeek();
			stime=DateUtil.getToday();
		}
		if(etime.equals("")){
			etime=DateUtil.getToday();
		}
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("stime",stime);
		map.put("etime",etime);
		map.put("sorderno",sorderno);
		map.put("sstatus",sstatus);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = orderInfoService.getHistoryOrderInfoListCount(map);
		List<OrderInfo> orderinfolist = orderInfoService.getHistoryOrderInfoList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(orderinfolist);
		
		//返回页面参数
		request.setAttribute("stime", stime);
		request.setAttribute("etime", etime);
		request.setAttribute("sorderno", sorderno);
		request.setAttribute("sstatus", sstatus);
		request.setAttribute("page",page);
		
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",orderinfolist);
        return "/store/historyorderinfo/list";
    }
	
	
	/**
	 * 订单详情
	 * @return
	 */
	@RequestMapping("/info")
    public String orderinfo(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		OrderInfo orderinfo = orderInfoService.getOrderInfo(id);
		List<ProductInfo> productinfolist = orderInfoService.getProductInfoList(id);
		request.setAttribute("orderinfo",orderinfo);
		request.setAttribute("productinfolist",productinfolist);
		request.setAttribute("id",id);
        return "/store/orderinfo/info";
    }
}
