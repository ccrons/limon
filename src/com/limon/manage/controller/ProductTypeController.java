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

import com.limon.base.common.LogUtil;
import com.limon.base.controller.BaseController;
import com.limon.manage.model.ProductType;
import com.limon.manage.service.ProductInfoService;
import com.limon.manage.service.ProductTypeService;
import com.limon.util.CommonUtil;

/**
 * 产品库管理->产品类型
 * 项目名称：limon   
 * 类名称：ProductTypeController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午9:14:36   
 * @version v1.0
 */
@Controller
@RequestMapping("/producttype")
public class ProductTypeController extends BaseController{
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 产品类型列表
	 * @return
	 */
	@RequestMapping("/list")
    public String productTypeList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String stypename = this.getParaString("stypename");
		Integer pid = this.getParaInteger("pid");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("stypename",stypename);
		map.put("pid",pid);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=productTypeService.getProductTypeListCount(map);
		List<ProductType> producttypelist = productTypeService.getProductTypeList(map);
		
		//查询一级分类列表
		map.put("pid",0);
		List<ProductType> typelist = productTypeService.getProductTypeListByPid(map);
		request.setAttribute("typelist",typelist);
		request.setAttribute("pid", pid);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(producttypelist);
		
		//返回页面参数
		request.setAttribute("stypename", stypename);
		request.setAttribute("page",page);
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",producttypelist);
		
        return "/manage/producttype/list";
    }
	/**
	 * 产品类型编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String producttypeedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductType producttype = productTypeService.getProductType(id);
		request.setAttribute("producttype",producttype);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pid","0");
		List<ProductType> producttypelist = productTypeService.getProductTypeListByPid(map);
		request.setAttribute("producttypelist",producttypelist);
		
        return "/manage/producttype/edit";
    }
	/**
	 * 产品类型新增页面
	 * @return
	 */
	@RequestMapping("/add")
    public String producttypeadd(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pid","0");
		List<ProductType> producttypelist = productTypeService.getProductTypeListByPid(map);
		request.setAttribute("producttypelist",producttypelist);
		return "/manage/producttype/add";
    }
	
	/**
	 * 产品类型保存
	 * @return
	 */
	@RequestMapping("/save")
    public String producttypesave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		String returnurl="";
		String id = this.getParaString("id");
		String typename = this.getParaString("typename");
		System.out.println(typename+"===========");
		Integer pid = this.getParaInteger("pid");
		Integer sort = this.getParaInteger("sort");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typename", typename);
			map.put("pid", pid);
			map.put("sort", sort);
			if(id==null || "".equals(id)){
				Integer inte = productTypeService.productTypeAdd(map);
				if(inte>0){
					LogUtil.logOperation("新增产品类型:"+typename+"失败,产品类型名称重复!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="2";
				}else{
					LogUtil.logOperation("新增产品类型:"+typename+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="1";
				}
				returnurl="/manage/producttype/add";
			}else{
				map.put("id", id);
				Integer inte = productTypeService.productTypeUpdate(map);
				if(inte>0){
					rs="2";
					LogUtil.logOperation("修改产品类型:"+typename+"失败,产品类型名称重复!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				}else{
					rs="1";
					LogUtil.logOperation("修改产品类型:"+typename+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				}
				returnurl="/manage/producttype/edit";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		
		ProductType producttype=new ProductType();
		if(id!=null && !"".equals(id)){
			producttype.setId(Integer.parseInt(id));
		}
		producttype.setTypename(typename);
		request.setAttribute("producttype",producttype);
		request.setAttribute("rs", rs);
		return returnurl;
	}
	
	/**
	 * 产品类型删除
	 * @return
	 */
	@RequestMapping("/del")
    public String producttypedel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String pid = this.getParaString("pid");
		String typename = this.getParaString("typename");
		String stypename = this.getParaString("stypename");
		String currentPage=this.getParaString("currentPage");
		//查询品牌是否被商品使用
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("typepid",id);
		Integer num=productInfoService.getProductInfoListCount(map);
		if(num==null || num==0){
			map.put("pid", id);
			List<ProductType> ptl = productTypeService.getProductTypeListByPid(map);
			if(ptl==null || ptl.size()==0){
				productTypeService.productTypeDel(id);
				LogUtil.logOperation("删除产品类型:"+typename+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="1";
			}else{
				LogUtil.logOperation("删除产品类型:"+typename+"失败,请先删除下级类型", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="3";
			}
		}else{
			LogUtil.logOperation("删除产品类型:"+typename+"失败,此类型已被商品使用", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			rs="2";
		}
		return "redirect:list?currentPage="+currentPage+"&stypename="+URLEncoder.encode(stypename,"UTF-8")+"&pid="+pid+"&rs="+rs;
    }
}
