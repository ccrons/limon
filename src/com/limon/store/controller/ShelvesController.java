package com.limon.store.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.ProductType;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.manage.service.ProductBrandService;
import com.limon.manage.service.ProductInfoService;
import com.limon.manage.service.ProductTypeService;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.ShelvesService;
import com.limon.store.service.StoreAdService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;

/**
 * 产品管理->产品信息
 * 项目名称：limon   
 * 类名称：ShelvesController
 * 创建人：WN	
 * 创建时间：2015年7月11日 下午10:12:54   
 * @version v1.0
 */
@Controller
@RequestMapping("/shelves")
public class ShelvesController extends BaseController{
	@Autowired
	private ShelvesService shelvesService;
	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private StoreInfoService storeInfoService;

	@Autowired
	private StoreAdService storeAdService;
	/**
	 * 待上架产品信息列表
	 * @return
	 */
	@RequestMapping("/list")
    public String shelvesList(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		StoreInfo storeInfo = storeInfoService.getStoreInfoById(uid);
		//参数接收
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String sj = this.getParaString("sj");
		String sisofficial = this.getParaString("sisofficial");
		if(sj.equals("")){
			sj="2";
		}else if(sj.equals("0")){
			sj="";
		}
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("sname",sname);
		map.put("stype",stype);
		map.put("sbrand",sbrand);
		map.put("isself",storeInfo.getIsself()+"");
		map.put("sj",sj);
		map.put("sisofficial",sisofficial);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = shelvesService.getProductInfoListCount(map);
		List<ProductInfo> productinfolist = shelvesService.getProductInfoList(map);
		for (int i = 0; i < productinfolist.size(); i++) {
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(productinfolist.get(i).getId()+"");
			if(ap!=null){
				productinfolist.get(i).setAdid(ap.getAdid()+"");
				productinfolist.get(i).setAdprice(ap.getAdprice());
			}
			TimeLimit tl = storeAdService.getTimeLimitByProductId(productinfolist.get(i).getId()+"");
			if(tl!=null){
				productinfolist.get(i).setTltime(tl.getTltime());
				productinfolist.get(i).setTlprice(tl.getTlprice());
			}
		}
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(productinfolist);
		
		//类型品牌列表
		List<ProductBrand> productbrandlist = productBrandService.getProductBrandListAll();
		List<ProductType> producttypelist = productTypeService.getTwoLevelProductTypeList();
		request.setAttribute("productbrandlist",productbrandlist);
		request.setAttribute("producttypelist",producttypelist);
		//返回页面参数
		request.setAttribute("sname", sname);
		request.setAttribute("stype", stype);
		request.setAttribute("sbrand", sbrand);
		request.setAttribute("sj", sj);
		request.setAttribute("sisofficial", sisofficial);
		request.setAttribute("page",page);
		
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",productinfolist);
        return "/store/shelves/list";
    }
	/**
	 * 产品信息上架页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String shelvesedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		String name = this.getParaString("name");
		String price = this.getParaString("price");
		String sprice = this.getParaString("sprice");
		String isofficial = this.getParaString("isofficial");
		
		request.setAttribute("id",id);
		request.setAttribute("name",name);
		request.setAttribute("price",price);
		request.setAttribute("sprice",sprice);
		request.setAttribute("isofficial",isofficial);
        return "/store/shelves/edit";
    }
	/**
	 * 产品信息上架保存
	 * @return
	 */
	@RequestMapping("/save")
    public String shelvessave(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		String id = this.getParaString("id");//产品id
		String salenum = this.getParaString("salenum");//数量
		String price = this.getParaString("price");//数量
		String tag = this.getParaString("tag");//标志位如果为1 即为详情页保存
		Integer uid = this.getLoginUser().getId();
		//判断是否已经上架过
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("id",id);
		map.put("salenum",salenum);
		map.put("saleprice",price);
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		if(productinfo.getSprice()==null || productinfo.getSprice().equals("") || Double.parseDouble(price)<=productinfo.getSprice()){
			Integer num = shelvesService.getStoreProductCount(map);
			if(num==0){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				String createtime = sdf.format(new Date());
				map.put("createtime",createtime);
				//上架
				rs = "1";
				shelvesService.storeProductAdd(map);
			}else{
				//已上过架
				rs = "2";
			}
		}else{
			rs = "3";//售价不能高于原价
		}
		request.setAttribute("id",id);
		request.setAttribute("salenum",salenum);
		request.setAttribute("rs",rs);
		if(tag.equals("1")){
			Map<String,Object> smap=new HashMap<String,Object>();
			smap.put("id", id);
			smap.put("storeid",uid);
			productinfo = shelvesService.getProductInfo(smap);
			request.setAttribute("productinfo",productinfo);
			return "/store/shelves/info";
		}else{
			return "/store/shelves/edit";
		}
    }
	
	/**
	 * 产品信息批量上架保存
	 * @return
	 */
	@RequestMapping("/upsale")
    public String upsale(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		String pids = this.getParaString("id");//产品id
		String currentPage = this.getParaString("currentPage");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String sj = this.getParaString("sj");
		String[] ids=pids.split(",");
		//String salenum=ConfigUtil.getConfig("product_upnum").getConfig_value();
		Integer uid = this.getLoginUser().getId();
		for(String id:ids){
			ProductInfo p=productInfoService.getProductInfo(id);
			//判断是否已经上架过
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("uid", uid);
			map.put("id",id);
			map.put("salenum",p.getUpnum());
			map.put("saleprice",p.getPrice());
			Integer num = shelvesService.getStoreProductCount(map);
			if(num==0){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				String createtime = sdf.format(new Date());
				map.put("createtime",createtime);
				shelvesService.storeProductAdd(map);
			}
		}
		//上架
		rs = "3";
		try {
			return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&sj="+sj+"&rs="+rs;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
    }
	
	/**
	 * 产品下架
	 * @return
	 */
	@RequestMapping("/del")
    public String shelvesdel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String name = this.getParaString("name");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String currentPage=this.getParaString("currentPage");
		
		shelvesService.storeProductDel(id);
		LogUtil.logOperation("下架产品:"+name+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		rs="1";
		
		return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&rs="+rs;
	}
	
	/**
	 * 产品信息详情页面
	 * @return
	 */
	@RequestMapping("/info")
    public String shelvesinfo(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		String name = this.getParaString("name");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		Integer uid = this.getLoginUser().getId();
		map.put("storeid",uid);
		ProductInfo productinfo = shelvesService.getProductInfo(map);
		StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(productinfo.getId()+"");
		if(ap!=null){
			productinfo.setAdid(ap.getAdid()+"");
			productinfo.setAdprice(ap.getAdprice());
		}
		TimeLimit tl = storeAdService.getTimeLimitByProductId(productinfo.getId()+"");
		if(tl!=null){
			productinfo.setTltime(tl.getTltime());
			productinfo.setTlprice(tl.getTlprice());
		}
		request.setAttribute("id",id);
		request.setAttribute("name",name);
		request.setAttribute("productinfo",productinfo);
		System.out.println(uid+"========"+id);
        return "/store/shelves/info";
    }
}
