package com.limon.store.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.limon.base.common.LogUtil;
import com.limon.base.controller.BaseController;
import com.limon.base.model.FileInfo;
import com.limon.manage.model.ProductBrand;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.ProductType;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.manage.service.ProductBrandService;
import com.limon.manage.service.ProductInfoService;
import com.limon.manage.service.ProductTypeService;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.MyProductService;
import com.limon.store.service.SelfStoreService;
import com.limon.store.service.ShelvesService;
import com.limon.store.service.StoreAdService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;

/**
 * 产品管理->我的产品
 * 项目名称：limon   
 * 类名称：MyProductController
 * 创建人：WN	
 * 创建时间：2015年7月11日 下午10:12:26   
 * @version v1.0
 */
@Controller
@RequestMapping("/myproduct")
public class MyProductController extends BaseController{
	@Autowired
	private MyProductService myproductService;
	@Autowired
	private ShelvesService shelvesService;
	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private SelfStoreService selfStoreService;
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private StoreAdService storeAdService;
	/**
	 * 我的产品信息列表
	 * @return
	 */
	@RequestMapping("/list")
    public String myproductList(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		StoreInfo store=storeInfoService.getStoreInfoById(uid);
		//参数接收
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String sisofficial = this.getParaString("sisofficial");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("sname",sname);
		map.put("stype",stype);
		map.put("sbrand",sbrand);
		map.put("sisofficial",sisofficial);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord = myproductService.getProductInfoListCount(map);
		List<ProductInfo> productinfolist = myproductService.getProductInfoList(map);
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
		request.setAttribute("sisofficial", sisofficial);
		request.setAttribute("page",page);
		
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		request.setAttribute("store",store);
		request.setAttribute("datas",productinfolist);
        return "/store/myproduct/list";
    }
	/**
	 * 售价设置
	 * @return
	 */
	@RequestMapping("/edit")
    public String myproductedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		String sid = this.getParaString("sid");
		String name = this.getParaString("name");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		Integer uid = this.getLoginUser().getId();
		map.put("storeid",uid);
		ProductInfo productinfo = shelvesService.getProductInfo(map);
		request.setAttribute("id",id);
		request.setAttribute("sid",sid);
		request.setAttribute("name",name);
		request.setAttribute("sprice",productinfo.getSprice());
		request.setAttribute("productinfo",productinfo);
        return "/store/myproduct/edit";
    }
	/**
	 * 售价保存
	 * @return
	 */
	@RequestMapping("/save")
    public String myproductsave(HttpServletRequest request, HttpServletResponse response){
		String rs = "2";
		String id = this.getParaString("id");//产品id
		String sid = this.getParaString("sid");//我的产品id
		String saleprice = this.getParaString("saleprice");//售价
		String salenum = this.getParaString("salenum");//数量
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",sid);
		map.put("saleprice",saleprice);
		map.put("salenum",salenum);
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		//if(productinfo.getSprice()==null || productinfo.getSprice().equals("") || Double.parseDouble(saleprice)<=productinfo.getSprice()){
			myproductService.storeProductUpdate(map);
			rs = "1";
		//}else{
		//	rs = "3";
		//}
		request.setAttribute("id",id);
		request.setAttribute("sid",sid);
		request.setAttribute("rs",rs);
		
		Map<String,Object> amap=new HashMap<String,Object>();
		amap.put("id", id);
		Integer uid = this.getLoginUser().getId();
		amap.put("storeid",uid);
		productinfo = myproductService.getProductInfo(amap);
		request.setAttribute("productinfo",productinfo);
		
		return "/store/myproduct/edit";
		
    }
	
	/**
	 * 产品下架
	 * @return
	 */
	@RequestMapping("/del")
    public String myproductdel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String name = this.getParaString("name");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String currentPage=this.getParaString("currentPage");
		
		myproductService.storeProductDel(id);
		LogUtil.logOperation("下架产品:"+name+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		rs="1";
		
		return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&rs="+rs;
	}
	
	/**
	 * 产品信息详情页面
	 * @return
	 */
	@RequestMapping("/info")
    public String myproductinfo(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		String name = this.getParaString("name");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		Integer uid = this.getLoginUser().getId();
		map.put("storeid",uid);
		ProductInfo productinfo = myproductService.getProductInfo(map);
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
        return "/store/myproduct/info";
    }
	
	/**
	 * 产品批量下架
	 * @return
	 */
	@RequestMapping("/alldel")
    public String myproductalldel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String ids = this.getParaString("ids");
		String name = this.getParaString("name");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String currentPage=this.getParaString("currentPage");
		String[] idsarray = ids.split(",");
		for (int i = 0; i < idsarray.length; i++) {
			String id = idsarray[i];
			if(id!=null && !id.equals("")){
				myproductService.storeProductDel(id);
				LogUtil.logOperation("下架产品:"+name+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			}
		}
		rs="1";
		return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&rs="+rs;
	}
	
	/**
	 * 添加折扣信息
	 * @return
	 */
	@RequestMapping("/saleadd")
    public String addsale(HttpServletRequest request, HttpServletResponse response){
		Integer id = this.getParaInteger("id");
		Integer storeid = this.getParaInteger("storeid");
		String name = this.getParaString("name");
		request.setAttribute("productid", id);
		request.setAttribute("storeid", storeid);
		request.setAttribute("productname", name);
        return "/store/storeinfo/saleadd";
    }
	
	/**
	 * 添加广告信息
	 * @return
	 */
	@RequestMapping("/adadd")
    public String adadd(HttpServletRequest request, HttpServletResponse response){
		Integer id = this.getParaInteger("id");
		Integer storeid = this.getParaInteger("storeid");
		String name = this.getParaString("name");
		request.setAttribute("productid", id);
		request.setAttribute("storeid", storeid);
		request.setAttribute("productname", name);
        return "/store/storeinfo/adadd";
    }
	
	/**
	 * 保存折扣信息
	 * @return
	 */
	@RequestMapping("/salesave")
    public String salesave(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		Integer productid = this.getParaInteger("productid");
		Integer storeid = this.getParaInteger("storeid");
		String discount = this.getParaString("discount");
		try{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("productid", productid);
			map.put("storeid", storeid);
			map.put("discount", discount);
			selfStoreService.addSaleInfo(map);
			rs="1";
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
        return "/store/storeinfo/saleadd";
    }
	
	/**
	 * 保存广告信息
	 * @return
	 */
	@RequestMapping("/adsave")
    public String adsave(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		Integer productid = this.getParaInteger("productid");
		Integer storeid = this.getParaInteger("storeid");
		String adinfo = this.getParaString("adinfo");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile0 = multipartRequest.getFile("adimg");
		FileInfo file = uploadFile(multipartFile0,"adimg");
		String adurl=file.getFilePath();
		try{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("productid", productid);
			map.put("storeid", storeid);
			map.put("adinfo", adinfo);
			map.put("adurl", adurl);
			selfStoreService.addAdInfo(map);
			rs="1";
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
        return "/store/storeinfo/adadd";
    }
	
	/**
	 * 取消折扣
	 * @return
	 */
	@RequestMapping("/saledel")
    public String saledel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="2";
		Integer saleid = this.getParaInteger("saleid");
		String name = this.getParaString("name");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String currentPage=this.getParaString("currentPage");
		try{
			selfStoreService.delSaleInfoById(saleid);
			LogUtil.logOperation("产品:"+name+"取消折扣成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			rs="3";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&rs="+rs;
	}
	
	/**
	 * 取消广告
	 * @return
	 */
	@RequestMapping("/addel")
    public String addel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="4";
		Integer adid = this.getParaInteger("adid");
		String name = this.getParaString("name");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String currentPage=this.getParaString("currentPage");
		try{
			selfStoreService.delAdInfoById(adid);
			LogUtil.logOperation("产品:"+name+"取消广告成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			rs="5";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&rs="+rs;
	}
	
	
	
	/**
	 * ajax售价保存
	 * @return
	 */
	@RequestMapping("/saveprice")
    public void saveprice(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		String id = this.getParaString("id");//产品id
		String sid = this.getParaString("sid");//我的产品id
		String saleprice = this.getParaString("saleprice");//售价
		
		if(isDouble(saleprice)||isInteger(saleprice)){
			ProductInfo productinfo = productInfoService.getProductInfo(id);
			if(productinfo.getSprice()==null || productinfo.getSprice().equals("") || Double.parseDouble(saleprice)<=productinfo.getSprice()){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id",sid);
				map.put("saleprice",saleprice);
				myproductService.storeProductUpdatePrice(map);
				rs = "1";
			}else{
				rs = "3";
			}
			
		}else{
			rs = "2";
		}
		
		try {
			response.getWriter().write(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * ajax库存保存
	 * @return
	 */
	@RequestMapping("/savenum")
    public void savenum(HttpServletRequest request, HttpServletResponse response){
		String rs = "0";
		//String id = this.getParaString("id");//产品id
		String sid = this.getParaString("sid");//我的产品id
		String salenum = this.getParaString("salenum");//库存
		
		if(isInteger(salenum)){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",sid);
			map.put("salenum",salenum);
			myproductService.storeProductUpdateNum(map);
			rs = "1";
		}else{
			rs = "2";
		}
		
		try {
			response.getWriter().write(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
}
