package com.limon.store.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.controller.BaseController;
import com.limon.manage.model.ProductBrand;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.ProductType;
import com.limon.manage.service.ProductBrandService;
import com.limon.manage.service.ProductInfoService;
import com.limon.manage.service.ProductTypeService;
import com.limon.util.DateUtil;

/**
 * 数据统计->销售额统计
 * 项目名称：limon   
 * 类名称：StatisticsController
 * 创建人：WN	
 * 创建时间：2015年7月12日 下午10:14:24   
 * @version v1.0
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController{
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private ProductTypeService productTypeService;
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
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String paytype = this.getParaString("paytype");
		String sbrand = this.getParaString("sbrand");
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
		map.put("sname",sname);
		map.put("stype",stype);
		map.put("sbrand",sbrand);
		map.put("paytype",paytype);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		ProductInfo price = productInfoService.getProductInfoListStatisticsPrice(map);
		ProductInfo sendprice = productInfoService.getProductInfoListStatisticsSendPrice(map);
		Integer totalRecord = productInfoService.getProductInfoListStatisticsCount(map);
		List<ProductInfo> productinfolist = productInfoService.getProductInfoListStatistics(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(productinfolist);
		
		//类型品牌列表
		List<ProductBrand> productbrandlist = productBrandService.getProductBrandListAll();
		List<ProductType> producttypelist = productTypeService.getTwoLevelProductTypeList();
		request.setAttribute("productbrandlist",productbrandlist);
		request.setAttribute("producttypelist",producttypelist);
		//返回页面参数
		request.setAttribute("stime", stime);
		request.setAttribute("etime", etime);
		request.setAttribute("sname", sname);
		request.setAttribute("stype", stype);
		request.setAttribute("paytype", paytype);
		Double allorderprice=0.0;
		if(price!=null){
			allorderprice=price.getAllprice();
		}
		request.setAttribute("price", allorderprice);
		
		Double allsendprice=0.0;
		if(sendprice!=null){
			allsendprice=sendprice.getAllsendprice();
		}
		request.setAttribute("sendprice", allsendprice);
		
		BigDecimal b1 = new BigDecimal(allorderprice);  
		BigDecimal b2 = new BigDecimal(allsendprice);  
		Double allprice=new Double(b1.add(b2).doubleValue()); 
		
		request.setAttribute("allprice", allprice);
		
		request.setAttribute("sbrand", sbrand);
		request.setAttribute("page",page);
		
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",productinfolist);
        return "/store/statistics/list";
    }
	
}
