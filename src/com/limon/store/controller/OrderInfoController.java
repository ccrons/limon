package com.limon.store.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.common.LogUtil;
import com.limon.base.controller.BaseController;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.store.model.OrderInfo;
import com.limon.store.service.OrderInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.DateUtil;

/**
 * 订单管理->订单处理
 * 项目名称：limon   
 * 类名称：OrderInfoController
 * 创建人：WN	
 * 创建时间：2015年7月10日 上午11:29:50   
 * @version v1.0
 */
@Controller
@RequestMapping("/orderinfo")
public class OrderInfoController extends BaseController{
	@Autowired
	private OrderInfoService orderInfoService;
	
	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping("/list")
    public String orderList(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		//参数接收
		String sorderno = this.getParaString("sorderno");//订单编号
		String sstatus = this.getParaString("sstatus");//状态
		if("".equals(sstatus)){
			sstatus = "1";
		}else if("99".equals(sstatus)){
			sstatus = "";
		}
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		if(this.getLoginUser().getRoleid()==1){
			map.put("uid", null);
		}else{
			map.put("uid", uid);
		}
		map.put("sorderno",sorderno);
		map.put("sstatus",sstatus);
		String nowday = DateUtil.getToday();
		map.put("nowday",nowday);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = orderInfoService.getOrderInfoListCount(map);
		List<OrderInfo> orderinfolist = orderInfoService.getOrderInfoList(map);
		for(OrderInfo o:orderinfolist){
			List<ProductInfo> productinfolist = orderInfoService.getProductInfoList(o.getId()+"");
			Integer isluck=0;
			for(ProductInfo p:productinfolist){
				//是否抽奖订单验证
    			if(p.getOpprice().equals("0.0")||p.getOpprice().equals("0.00")||p.getOpprice().equals("0")){
    				//System.out.println(p.getOpprice()+"================");
    				isluck=1;
    				//System.out.println(isluck+"================");
    			}
			}
			o.setIsluck(isluck);
		}
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(orderinfolist);
		
		//返回页面参数
		request.setAttribute("sorderno", sorderno);
		request.setAttribute("sstatus", sstatus);
		request.setAttribute("page",page);
		
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",orderinfolist);
		String logintype=(String)request.getSession().getAttribute("logtype");
		request.setAttribute("logintype", logintype);
        return "/store/orderinfo/list";
    }
	/**
	 * 订单确认
	 * @return
	 */
	@RequestMapping("/cf")
    public String cf(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String orderno = this.getParaString("orderno");
		String sorderno = this.getParaString("sorderno");
		String sstatus = this.getParaString("sstatus");
		String currentPage=this.getParaString("currentPage");
		
		orderInfoService.orderInfoCF(id);
		//客户端推送
		//Order order=orderInfoService.getOrderbyId(Integer.parseInt(id));
		//JPushUtil.pushToAppUserAcceptOrder("app"+order.getUserid(), "您在 "+order.getSname()+" 的订单商家已经确认了，订单号:"+order.getOrderno());
	
		LogUtil.logOperation("确认订单:"+orderno+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		rs="1";
		
		return "redirect:list?currentPage="+currentPage+"&sorderno="+sorderno+"&sstatus="+sstatus+"&rs="+rs;
	}
	/**
	 * 取消页面
	 * @return
	 */
	@RequestMapping("/cancel")
    public String cancel(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		request.setAttribute("id",id);
        return "/store/orderinfo/cancel";
    }
	/**
	 * 取消保存页面
	 * @return
	 */
	@RequestMapping("/cancelsave")
    public String cancelsave(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		String id = this.getParaString("id");
		String reason = this.getParaString("reason");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		map.put("reason",reason);
		orderInfoService.orderInfoCancel(map);
		
		//商家确取消订单推送用户
		//Order order=orderInfoService.getOrderbyId(Integer.parseInt(id));
		//JPushUtil.pushToAppUserCancelOrder("app"+order.getUserid(), "您在 "+order.getSname()+" 的订单被商家取消了，原因："+reason+"，订单号:"+order.getOrderno());
	
		//查询订单信息
    	Order o=orderInfoService.getOrderbyOid(Integer.parseInt(id));
    	List<OrderProductGet> plist=orderInfoService.getProductListByOid(Integer.parseInt(id));
    	//增加商家库存
    	Map<String,Object> smap=new HashMap<String,Object>();
    	smap.put("storeid", o.getSid());
    	for(OrderProductGet op:plist){
    		smap.put("pnum",op.getCount());
    		smap.put("productid",op.getGid());
    		orderInfoService.updateStoreSaleNum(smap);
    	}
    	LogUtil.logOperation("取消订单:"+o.getOrderno()+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		
		rs = "1";
		request.setAttribute("id",id);
		request.setAttribute("reason",reason);
		request.setAttribute("rs",rs);
        return "/store/orderinfo/cancel";
    }
	
	/**
	 * 完成页面
	 * @return
	 */
	@RequestMapping("/sure")
    public String sure(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		request.setAttribute("id",id);
		//查询订单信息
    	Order o=orderInfoService.getOrderbyOid(Integer.parseInt(id));
    	request.setAttribute("oinfo",o);
        return "/store/orderinfo/sure";
    }
	/**
	 * 完成保存页面
	 * @return
	 */
	@RequestMapping("/suresave")
    public String suresave(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		String id = this.getParaString("id");
		String goodscode=this.getParaString("goodscode");
		orderInfoService.orderInfoOver(id,goodscode);
		//查询订单信息
    	Order o=orderInfoService.getOrderbyOid(Integer.parseInt(id));
    	List<OrderProductGet> plist=orderInfoService.getProductListByOid(Integer.parseInt(id));
    	//增加商家库存
    	Map<String,Object> smap=new HashMap<String,Object>();
    	smap.put("storeid", o.getSid());
    	for(OrderProductGet op:plist){
    		smap.put("pnum",op.getCount());
    		smap.put("productid",op.getGid());
    		orderInfoService.updateStoreSaleNum(smap);
    	}
    	LogUtil.logOperation("退款订单:"+o.getOrderno()+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		
		rs = "1";
		request.setAttribute("id",id);
		request.setAttribute("rs",rs);
        return "/store/orderinfo/sure";
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
		Integer isluck=0;
		for(ProductInfo p:productinfolist){
			//是否抽奖订单验证
			if(p.getOpprice().equals("0.0")||p.getOpprice().equals("0.00")||p.getOpprice().equals("0")){
				//System.out.println(p.getOpprice()+"================");
				isluck=1;
				//System.out.println(isluck+"================");
			}
		}
		orderinfo.setIsluck(isluck);
		request.setAttribute("orderinfo",orderinfo);
		request.setAttribute("productinfolist",productinfolist);
		request.setAttribute("id",id);
        return "/store/orderinfo/info";
    }
}
