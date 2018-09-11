package com.limon.manage.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.limon.base.controller.BaseController;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.service.OrderStatisticsService;
import com.limon.store.model.AreaCity;
import com.limon.store.model.AreaCounty;
import com.limon.store.model.AreaProvince;
import com.limon.store.model.OrderInfo;
import com.limon.store.model.OrderInfoExport;
import com.limon.store.service.AreaCityService;
import com.limon.store.service.AreaCountyService;
import com.limon.store.service.AreaProvinceService;
import com.limon.util.DateUtil;
import com.limon.util.JSONUtil;
import com.limon.wx.service.ShopService;

/**
 * 数据统计->订单统计
 * 项目名称：limon   
 * 类名称：OrderStatisticsController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:23:04   
 * @version v1.0
 */
@Controller
@RequestMapping("/orderstatistics")
public class OrderStatisticsController extends BaseController{
	@Autowired
	private OrderStatisticsService orderStatisticsService;
	@Autowired
	private AreaProvinceService areaProvinceService;
	@Autowired
	private AreaCityService areaCityService;
	@Autowired
	private AreaCountyService areaCountyService;
	@Autowired
	private ShopService shopService;
	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping("/list")
    public String OrderStatisticsList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sstorename = this.getParaString("sstorename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String orderno=this.getParaString("orderno");
		String isself = this.getParaString("isself");
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
		map.put("isself",isself);
		map.put("orderno",orderno);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = orderStatisticsService.getOrderInfoListCount(map);
		List<OrderInfo> orderlist = orderStatisticsService.getOrderInfoList(map);
		Double allprice = orderStatisticsService.getOrderInfoListCountPrice(map);
		Double allsendprice= orderStatisticsService.getOrderInfoListCountSendPrice(map);
		Double allproxylirun=0.0;
		for(OrderInfo o:orderlist){
			List<OrderProductGet> plist=shopService.getProductListByOid(o.getId());
			//计算每个产品的代理利润然后求和
			for(OrderProductGet og:plist){
				ProductInfo p=shopService.getGoosById(og.getGid());
				//售价-代理价=代理利润
				allproxylirun+=(Double.parseDouble(og.getOpprice())-p.getProxyprice())*og.getCount();
			}
		}
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(orderlist);
		
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
		request.setAttribute("isself", isself);
		request.setAttribute("orderno", orderno);
		request.setAttribute("allprice", allprice);
		request.setAttribute("allsendprice", allsendprice);
		request.setAttribute("allproxylirun", allproxylirun);
		request.setAttribute("page",page);
		
		request.setAttribute("datas",orderlist);
        return "/manage/orderstatistics/list";
    }
	/**
	 * 订单导出
	 * @return
	 */
	@RequestMapping("/exportorders")
    public String exportorders(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String stime = this.getParaString("stime");
		String etime = this.getParaString("etime");
		String sstorename = this.getParaString("sstorename");
		Integer provincecode = this.getParaInteger("provincecode");
		Integer citycode = this.getParaInteger("citycode");
		Integer countycode = this.getParaInteger("countycode");
		String orderno=this.getParaString("orderno");
		String isself = this.getParaString("isself");
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
		map.put("isself",isself);
		map.put("orderno",orderno);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = orderStatisticsService.getOrderInfoListCount(map);
		List<OrderInfo> orderlist = orderStatisticsService.getOrderInfoList(map);
		Double allprice = orderStatisticsService.getOrderInfoListCountPrice(map);
		
		
		List<OrderInfoExport> list=new ArrayList<OrderInfoExport>();
		for(OrderInfo o:orderlist){
			List<OrderProductGet> plist=shopService.getProductListByOid(o.getId());
			for(OrderProductGet og:plist){
				OrderInfoExport oe=new OrderInfoExport();
				oe.setProductid(og.getGid()+"");
				oe.setProductname(og.getName());
				oe.setProductnum(og.getCount()+"");
				oe.setProductprice(og.getOpprice());
				//
				oe.setAcceptname(o.getAcceptname());
				oe.setAccepttel(o.getAccepttel());
				oe.setAddress(o.getAddress());
				oe.setAddrid(o.getAddrid());
				oe.setCity(o.getCity());
				oe.setContact(o.getContact());
				oe.setCounty(o.getCounty());
				oe.setCreatetime(o.getCreatetime());
				oe.setGoodscode(o.getGoodscode());
				oe.setId(o.getId());
				oe.setIsdel(o.getIsdel());
				oe.setIsluck(o.getIsluck());
				oe.setMobile(o.getMobile());
				oe.setNickname(o.getNickname());
				oe.setOrderno(o.getOrderno());
				oe.setOrderprice(o.getOrderprice());
				oe.setPayno(o.getPayno());
				oe.setPaytype(o.getPaytype());
				oe.setProvince(o.getProvince());
				oe.setReason(o.getReason());
				oe.setRemark(o.getRemark());
				oe.setReturn_id(o.getReturn_id());
				oe.setSendprice(o.getSendprice());
				oe.setSendtime(o.getSendtime());
				oe.setStatus(o.getStatus());
				oe.setStorename(o.getStorename());
				oe.setUserid(o.getUserid());
				oe.setUsername(o.getUsername());
				list.add(oe);
			}
			
		}
		//导出excel
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("订单统计");  
        sheet.setColumnWidth(0, 5500);
        sheet.setColumnWidth(1, 5500);
        sheet.setColumnWidth(2, 5500);
        sheet.setColumnWidth(3, 5500);
        sheet.setColumnWidth(4, 5500);
        sheet.setColumnWidth(5, 5500);
        sheet.setColumnWidth(6, 5500);
        sheet.setColumnWidth(7, 5500);
        sheet.setColumnWidth(8, 5500);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 400);
        
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("订单创建时间");  
        cell.setCellStyle(style); 
        cell = row.createCell(1);  
        cell.setCellValue("收货人");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("收货人电话");  
        cell.setCellStyle(style);  
        cell = row.createCell(3);  
        cell.setCellValue("收货人地址");  
        cell.setCellStyle(style); 
        cell = row.createCell(4);  
        cell.setCellValue("订单号");  
        cell.setCellStyle(style);
        cell = row.createCell(5);  
        cell.setCellValue("商品ID");  
        cell.setCellStyle(style);
        cell = row.createCell(6);  
        cell.setCellValue("商品名称");  
        cell.setCellStyle(style); 
        cell = row.createCell(7);  
        cell.setCellValue("商品数量");  
        cell.setCellStyle(style);
        cell = row.createCell(8);  
        cell.setCellValue("商品价格");  
        cell.setCellStyle(style);
  
        //数据样式
        HSSFCellStyle rowstyle = wb.createCellStyle();  
        rowstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        rowstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        rowstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        rowstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        rowstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        rowstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        
        for (int i = 0; i < list.size(); i++){  
            row = sheet.createRow( i + 1);  
            row.setHeight((short) 400);
            
            OrderInfoExport orderinfo = (OrderInfoExport) list.get(i);  
            // 第四步，创建单元格，并设置值  
            HSSFCell cell0=row.createCell(0);  
            cell0.setCellValue(orderinfo.getCreatetime());
            cell0.setCellStyle(rowstyle);
            
            HSSFCell cell1=row.createCell(1);  
            cell1.setCellValue(orderinfo.getAcceptname()); 
            cell1.setCellStyle(rowstyle);
            
            HSSFCell cell2=row.createCell(2); 
            cell2.setCellValue(orderinfo.getAccepttel()); 
            cell2.setCellStyle(rowstyle);
            
            HSSFCell cell3=row.createCell(3); 
            cell3.setCellValue(orderinfo.getProvince()+" "+orderinfo.getCity()+" "+orderinfo.getCounty()+" "+orderinfo.getAddress());
            cell3.setCellStyle(rowstyle);
            
            HSSFCell cell4=row.createCell(4); 
            cell4.setCellValue(orderinfo.getOrderno()); 
            cell4.setCellStyle(rowstyle);
            
            HSSFCell cell5=row.createCell(5); 
            cell5.setCellValue(orderinfo.getProductid()); 
            cell5.setCellStyle(rowstyle);
            
            HSSFCell cell6=row.createCell(6); 
            cell6.setCellValue(orderinfo.getProductname()); 
            cell6.setCellStyle(rowstyle);
            
            HSSFCell cell7=row.createCell(7); 
            cell7.setCellValue(orderinfo.getProductnum()); 
            cell7.setCellStyle(rowstyle);
            
            HSSFCell cell8=row.createCell(8); 
            cell8.setCellValue(orderinfo.getProductprice()); 
            cell8.setCellStyle(rowstyle);
        }  
        // 第六步，将文件存到指定位置  
        try{  
        	response.setContentType("octets/stream");
            response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("订单统计","utf-8")+".xls");
            wb.write(response.getOutputStream());
        }catch (Exception e){  
            e.printStackTrace();  
        }  
    
		return null;
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
