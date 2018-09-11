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
import com.limon.manage.model.ProductBrand;
import com.limon.manage.service.ProductBrandService;
import com.limon.manage.service.ProductInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.PinYinUtil;

/**
 * 产品库管理->产品品牌
 * 项目名称：limon   
 * 类名称：ProductBrandController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午9:13:56   
 * @version v1.0
 */
@Controller
@RequestMapping("/productbrand")
public class ProductBrandController extends BaseController{
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ProductBrandService productBrandService;
	
	/**
	 * 产品品牌列表
	 * @return
	 */
	@RequestMapping("/list")
    public String productBrandList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String sbrandname = this.getParaString("sbrandname");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sbrandname",sbrandname);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=productBrandService.getProductBrandListCount(map);
		List<ProductBrand> productbrandlist = productBrandService.getProductBrandList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(productbrandlist);
		//返回页面参数
		request.setAttribute("sbrandname", sbrandname);
		request.setAttribute("page",page);
				
		//弹出信息标志位
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
				
		request.setAttribute("datas",productbrandlist);
        return "/manage/productbrand/list";
    }
	/**
	 * 产品品牌编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String productbrandedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductBrand productbrand = productBrandService.getProductBrand(id);
		request.setAttribute("productbrand",productbrand);
        return "/manage/productbrand/edit";
    }
	/**
	 * 产品品牌编辑页面
	 * @return
	 */
	@RequestMapping("/add")
    public String productbrandadd(HttpServletRequest request, HttpServletResponse response){
		return "/manage/productbrand/add";
    }
	
	/**
	 * 产品品牌保存
	 * @return
	 */
	@RequestMapping("/save")
    public String productbrandsave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		String returnurl="";
		String id = this.getParaString("id");
		String brandname = this.getParaString("brandname");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("brandname", brandname);
			map.put("initial", PinYinUtil.converterToFirstSpellZM(brandname));
			if(id==null || "".equals(id)){
				Integer inte = productBrandService.productBrandAdd(map);
				if(inte>0){
					LogUtil.logOperation("新增产品品牌:"+brandname+"失败,产品品牌名称重复!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="2";
				}else{
					LogUtil.logOperation("新增产品品牌:"+brandname+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="1";
				}
				returnurl="/manage/productbrand/add";
			}else{
				map.put("id", id);
				Integer inte = productBrandService.productBrandUpdate(map);
				if(inte>0){
					LogUtil.logOperation("修改产品品牌:"+brandname+"失败,产品品牌名称重复!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="2";
				}else{
					LogUtil.logOperation("修改产品品牌:"+brandname+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="1";
				}
				returnurl="/manage/productbrand/edit";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		ProductBrand productbrand=new ProductBrand();
		if(id!=null && !"".equals(id)){
			productbrand.setId(Integer.parseInt(id));
		}
		productbrand.setBrandname(brandname);
		request.setAttribute("productbrand",productbrand);
		request.setAttribute("rs", rs);
		return returnurl;
	}
	
	/**
	 * 产品品牌删除
	 * @return
	 */
	@RequestMapping("/del")
    public String productbranddel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String brandname = this.getParaString("brandname");
		String sbrandname = this.getParaString("sbrandname");
		String currentPage=this.getParaString("currentPage");
		
		//查询品牌是否被商品使用
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sbrand",id);
		Integer num=productInfoService.getProductInfoListCount(map);
		if(num==0){
			productBrandService.productBrandDel(id);
			LogUtil.logOperation("删除产品品牌:"+brandname+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			rs="1";
		}else{
			LogUtil.logOperation("删除产品品牌:"+brandname+"失败", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			rs="2";
		}
		return "redirect:list?currentPage="+currentPage+"&sbrandname="+URLEncoder.encode(sbrandname,"UTF-8")+"&rs="+rs;
	}
}
