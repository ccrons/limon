package com.limon.manage.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.limon.base.controller.BaseController;
import com.limon.base.model.FileInfo;
import com.limon.base.service.SysConfigService;
import com.limon.manage.model.ProductBrand;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.ProductType;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.manage.service.ProductBrandService;
import com.limon.manage.service.ProductInfoService;
import com.limon.manage.service.ProductTypeService;
import com.limon.store.model.StoreAd;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.ShelvesService;
import com.limon.store.service.StoreAdService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.ConfigUtil;
/**
 * 活动管理
 * 项目名称：project
 * 类名称：ActivityController
 * 创建人：WN
 * 创建时间：2015年11月14日下午8:02:24
 * @version v1.0
 */
@Controller
@RequestMapping("/luck")
public class LuckController extends BaseController{
	
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private StoreAdService storeAdService;
	@Autowired
	private SysConfigService sysconfigService;
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private ShelvesService shelvesService;
	/**
	 * 产品信息列表
	 * @return
	 */
	@RequestMapping("/list")
    public String productInfoList(HttpServletRequest request, HttpServletResponse response){
		//参数接收
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String sisself = this.getParaString("sisself");
		String sisofficial = this.getParaString("sisofficial");
		String ad0tl = this.getParaString("ad0tl");
		
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sname",sname);
		map.put("stype",stype);
		map.put("sbrand",sbrand);
		map.put("sisself",sisself);
		map.put("sisofficial",sisofficial);
		map.put("ad0tl",ad0tl);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=productInfoService.getProductInfoListCount(map);
		List<ProductInfo> productinfolist = productInfoService.getProductInfoList(map);
		for (int i = 0; i < productinfolist.size(); i++) {
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(productinfolist.get(i).getId()+"");
			if(ap!=null){
				productinfolist.get(i).setAdid(ap.getAdid()+"");
			}
			TimeLimit tl = storeAdService.getTimeLimitByProductId(productinfolist.get(i).getId()+"");
			if(tl!=null){
				productinfolist.get(i).setTltime(tl.getTltime());
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
		List<StoreAd> storeadlist = storeAdService.getStoreAdListAll();
		request.setAttribute("storeadlist",storeadlist);
		//返回页面参数
		request.setAttribute("sname", sname);
		request.setAttribute("stype", stype);
		request.setAttribute("sbrand", sbrand);
		request.setAttribute("sisself", sisself);
		request.setAttribute("sisofficial", sisofficial);
		request.setAttribute("ad0tl", ad0tl);
		request.setAttribute("page",page);
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",productinfolist);
        return "/manage/activity/lucklist";
    }
	/**
	 * 产品信息详情页面
	 * @return
	 */
	@RequestMapping("/info")
    public String productinfoinfo(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		ProductBrand productbrand = productBrandService.getProductBrand(productinfo.getBrand()+"");
		ProductType producttype = productTypeService.getProductType(productinfo.getType()+"");
		productinfo.setBrandname(productbrand.getBrandname());
		productinfo.setTypename(producttype.getTypename());
		request.setAttribute("productinfo",productinfo);
        return "/manage/activity/info";
    }
	
	/**
	 * 广告活动
	 * @return
	 */
	@RequestMapping("/edit1")
    public String edit1(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(id);
		if(ap!=null){
			productinfo.setAdid(ap.getAdid()+"");
			productinfo.setAdprice(ap.getAdprice());
		}
		List<StoreAd> storeadlist = storeAdService.getStoreAdListAll();
		
		request.setAttribute("storeadlist",storeadlist);
		request.setAttribute("productinfo",productinfo);
		return "/manage/activity/edit1";
    }
	
	/**
	 * 广告活动保存
	 * @return
	 */
	@RequestMapping("/save1")
    public String save1(HttpServletRequest request, HttpServletResponse response){
		String productid = this.getParaString("id");
		String adid = this.getParaString("adid");
		String adprice = this.getParaString("adprice");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productid",productid);
		map.put("adid",adid);
		map.put("adprice",adprice);
		storeAdService.storeAdProductDel(map);
		if (!"0".equals(adid)) {
			storeAdService.storeAdProductSave(map);
		}
		request.setAttribute("rs", 1);
		ProductInfo productinfo = productInfoService.getProductInfo(productid);
		StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(productid);
		if(ap!=null){
			productinfo.setAdid(ap.getAdid()+"");
			productinfo.setAdprice(ap.getAdprice());
		}
		List<StoreAd> storeadlist = storeAdService.getStoreAdListAll();
		
		request.setAttribute("storeadlist",storeadlist);
		request.setAttribute("productinfo",productinfo);
		
		return "/manage/activity/edit1";
    }
	
	
	/**
	 * 0元购
	 * @return
	 */
	@RequestMapping("/edit2")
    public String edit2(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		
		request.setAttribute("productinfo",productinfo);
		return "/manage/activity/edit2";
    }
	
	/**
	 * 0元购保存
	 * @return
	 */
	@RequestMapping("/save2")
    public String save2(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		String isfrist = this.getParaString("isfrist");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",id);
		map.put("isfrist",isfrist);
		productInfoService.updateProduntInfoIsFristById(map);
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		request.setAttribute("productinfo",productinfo);
		request.setAttribute("rs", 1);
		
		return "/manage/activity/edit2";
    }
	
	/**
	 * 抽奖
	 * @return
	 */
	@RequestMapping("/edit4")
    public String edit4(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		
		request.setAttribute("productinfo",productinfo);
		return "/manage/activity/edit4";
    }
	
	/**
	 * 抽奖保存
	 * @return
	 */
	@RequestMapping("/save4")
    public String save4(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		String isluck = this.getParaString("isluck");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",id);
		map.put("isluck",isluck);
		productInfoService.updateProduntInfoIsLuckById(map);
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		request.setAttribute("productinfo",productinfo);
		request.setAttribute("rs", 1);
		
		return "/manage/activity/edit4";
    }
	
	/**
	 * 限时抢购
	 * @return
	 */
	@RequestMapping("/edit3")
    public String edit3(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		TimeLimit tl = storeAdService.getTimeLimitByProductId(id);
		String fstime1 = ConfigUtil.getConfig("fstime1").getConfig_value();
		String fetime1 = ConfigUtil.getConfig("fetime1").getConfig_value();
		String fstime2 = ConfigUtil.getConfig("fstime2").getConfig_value();
		String fetime2 = ConfigUtil.getConfig("fetime2").getConfig_value();
		String fstime3 = ConfigUtil.getConfig("fstime3").getConfig_value();
		String fetime3 = ConfigUtil.getConfig("fetime3").getConfig_value();
		if(tl!=null){
			productinfo.setTlnum(tl.getTlnum());
			productinfo.setBuynum(tl.getBuynum());
			productinfo.setTlprice(tl.getTlprice());
			productinfo.setTltime(tl.getTltime());
			for (int i = 0; i < tl.getTltime().split(",").length; i++) {
				if("1".equals(tl.getTltime().split(",")[i])){
					productinfo.setTltime1("1");
				}if("2".equals(tl.getTltime().split(",")[i])){
					productinfo.setTltime2("1");
				}if("3".equals(tl.getTltime().split(",")[i])){
					productinfo.setTltime3("1");
				}
			}
		}
		request.setAttribute("fstime1",fstime1);
		request.setAttribute("fetime1",fetime1);
		request.setAttribute("fstime2",fstime2);
		request.setAttribute("fetime2",fetime2);
		request.setAttribute("fstime3",fstime3);
		request.setAttribute("fetime3",fetime3);
		request.setAttribute("productinfo",productinfo);
		return "/manage/activity/edit3";
    }
	
	/**
	 * 限时抢购保存
	 * @return
	 */
	@RequestMapping("/save3")
    public String save3(HttpServletRequest request, HttpServletResponse response){
		String productid = this.getParaString("id");
		String flag = this.getParaString("flag");
		String tltime1 = this.getParaString("tltime1");
		String tltime2 = this.getParaString("tltime2");
		String tltime3 = this.getParaString("tltime3");
		String tlprice = this.getParaString("tlprice");
		String tlnum = this.getParaString("tlnum");
		String buynum = this.getParaString("buynum");
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productid",productid);
		map.put("tlprice",tlprice);
		map.put("tlnum",tlnum);
		map.put("buynum",buynum);
		if("1".equals(flag)){//参与限时抢购
			String tltime = "";
			if("1".equals(tltime1)){
				tltime += "1,";
			}
			if("1".equals(tltime2)){
				tltime += "2,";
			}
			if("1".equals(tltime3)){
				tltime += "3,";
			}
			map.put("tltime",tltime);
			TimeLimit tl = storeAdService.getTimeLimitByProductId(productid);
			//查询判断是否存在 如果存在更新 不存在新增
			if(tl!=null){//更新
				storeAdService.timeLimitupdate(map);
			}else{//新增
				storeAdService.timeLimitSave(map);
			}
		}else{//不参与 删除
			storeAdService.timeLimitdel(map);
		}
		request.setAttribute("rs", 1);
		
		
		
		ProductInfo productinfo = productInfoService.getProductInfo(productid);
		TimeLimit tl = storeAdService.getTimeLimitByProductId(productid);
		if(tl!=null){
			productinfo.setTlnum(tl.getTlnum());
			productinfo.setBuynum(tl.getBuynum());
			productinfo.setTlprice(tl.getTlprice());
			productinfo.setTltime(tl.getTltime());
			for (int i = 0; i < tl.getTltime().split(",").length; i++) {
				if("1".equals(tl.getTltime().split(",")[i])){
					productinfo.setTltime1("1");
				}if("2".equals(tl.getTltime().split(",")[i])){
					productinfo.setTltime2("1");
				}if("3".equals(tl.getTltime().split(",")[i])){
					productinfo.setTltime3("1");
				}
			}
		}
		request.setAttribute("productinfo",productinfo);
		
		return "/manage/activity/edit3";
    }
	
	/**
	 * 配置页面
	 * @return
	 */
	@RequestMapping("/actconfig")
    public String topicconfig(HttpServletRequest request, HttpServletResponse response){
		String tlpic = ConfigUtil.getConfig("tlpic").getConfig_value();
		String fpic = ConfigUtil.getConfig("fpic").getConfig_value();
		String recpic = ConfigUtil.getConfig("recpic").getConfig_value();
		
		String fstime1 = ConfigUtil.getConfig("fstime1").getConfig_value();
		String fetime1 = ConfigUtil.getConfig("fetime1").getConfig_value();
		String fstime2 = ConfigUtil.getConfig("fstime2").getConfig_value();
		String fetime2 = ConfigUtil.getConfig("fetime2").getConfig_value();
		String fstime3 = ConfigUtil.getConfig("fstime3").getConfig_value();
		String fetime3 = ConfigUtil.getConfig("fetime3").getConfig_value();
		//返回页面参数
		request.setAttribute("tlpic",tlpic);
		request.setAttribute("fpic",fpic);
		request.setAttribute("recpic",recpic);
		request.setAttribute("fstime1",fstime1);
		request.setAttribute("fetime1",fetime1);
		request.setAttribute("fstime2",fstime2);
		request.setAttribute("fetime2",fetime2);
		request.setAttribute("fstime3",fstime3);
		request.setAttribute("fetime3",fetime3);
		
		return "manage/activity/actconfig";
	}
	
	/**
	 * 配置保存
	 * @return
	 */
	@RequestMapping("/actconfigsave")
    public String actconfigsave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		Map<String, Object> map = new HashMap<String, Object>();
		
		//上传限时抢购图片
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile1 = multipartRequest.getFile("file1");
		map.put("config_key", "tlpic");
		String tlpic = this.getParaString("tlpic");
		if(tlpic.equals("1")){//当值为1的时候保持不变
			
		}else if(tlpic.equals("") && (multipartFile1.getOriginalFilename()==null || multipartFile1.getOriginalFilename().equals(""))){//当值为空时 数据置空
			map.put("config_value", "");
			sysconfigService.updateSysConfigByKey(map);
		}else{//上传图片
			FileInfo fi1 = uploadFile(multipartFile1,"activity");
			map.put("config_value", fi1.getFilePath());
			sysconfigService.updateSysConfigByKey(map);
		}
		
		//上传0元购图片
		MultipartFile multipartFile2 = multipartRequest.getFile("file2");
		map = new HashMap<String, Object>();
		map.put("config_key", "fpic");
		String fpic = this.getParaString("fpic");
		if(fpic.equals("1")){//当值为1的时候保持不变
			
		}else if(fpic.equals("") && (multipartFile2.getOriginalFilename()==null || multipartFile2.getOriginalFilename().equals(""))){//当值为空时 数据置空
			map.put("config_value", "");
			sysconfigService.updateSysConfigByKey(map);
		}else{//上传图片
			FileInfo fi2 = uploadFile(multipartFile2,"activity");
			map.put("config_value", fi2.getFilePath());
			sysconfigService.updateSysConfigByKey(map);
		}
		
		
		//上传招募令图片
		MultipartFile multipartFile3 = multipartRequest.getFile("file3");
		map = new HashMap<String, Object>();
		map.put("config_key", "recpic");
		String recpic = this.getParaString("recpic");
		if(recpic.equals("1")){//当值为1的时候保持不变
			
		}else if(recpic.equals("") && (multipartFile3.getOriginalFilename()==null || multipartFile3.getOriginalFilename().equals(""))){//当值为空时 数据置空
			map.put("config_value", "");
			sysconfigService.updateSysConfigByKey(map);
		}else{//上传图片
			FileInfo fi3 = uploadFile(multipartFile3,"activity");
			map.put("config_value", fi3.getFilePath());
			sysconfigService.updateSysConfigByKey(map);
		}
		
		String fstime1 = this.getParaString("fstime1");
		map = new HashMap<String, Object>();
		map.put("config_key", "fstime1");
		map.put("config_value", fstime1);
		sysconfigService.updateSysConfigByKey(map);
		
		String fetime1 = this.getParaString("fetime1");
		map = new HashMap<String, Object>();
		map.put("config_key", "fetime1");
		map.put("config_value", fetime1);
		sysconfigService.updateSysConfigByKey(map);
		
		String fstime2 = this.getParaString("fstime2");
		map = new HashMap<String, Object>();
		map.put("config_key", "fstime2");
		map.put("config_value", fstime2);
		sysconfigService.updateSysConfigByKey(map);
		
		String fetime2 = this.getParaString("fetime2");
		map = new HashMap<String, Object>();
		map.put("config_key", "fetime2");
		map.put("config_value", fetime2);
		sysconfigService.updateSysConfigByKey(map);
		
		String fstime3 = this.getParaString("fstime3");
		map = new HashMap<String, Object>();
		map.put("config_key", "fstime3");
		map.put("config_value", fstime3);
		sysconfigService.updateSysConfigByKey(map);
		
		String fetime3 = this.getParaString("fetime3");
		map = new HashMap<String, Object>();
		map.put("config_key", "fetime3");
		map.put("config_value", fetime3);
		sysconfigService.updateSysConfigByKey(map);
		
		rs = "1";
		request.setAttribute("rs",rs);
		
		
		tlpic = ConfigUtil.getConfig("tlpic").getConfig_value();
		fpic = ConfigUtil.getConfig("fpic").getConfig_value();
		recpic = ConfigUtil.getConfig("recpic").getConfig_value();
		
		fstime1 = ConfigUtil.getConfig("fstime1").getConfig_value();
		fetime1 = ConfigUtil.getConfig("fetime1").getConfig_value();
		fstime2 = ConfigUtil.getConfig("fstime2").getConfig_value();
		fetime2 = ConfigUtil.getConfig("fetime2").getConfig_value();
		fstime3 = ConfigUtil.getConfig("fstime3").getConfig_value();
		fetime3 = ConfigUtil.getConfig("fetime3").getConfig_value();
		//返回页面参数
		request.setAttribute("tlpic",tlpic);
		request.setAttribute("fpic",fpic);
		request.setAttribute("recpic",recpic);
		request.setAttribute("fstime1",fstime1);
		request.setAttribute("fetime1",fetime1);
		request.setAttribute("fstime2",fstime2);
		request.setAttribute("fetime2",fetime2);
		request.setAttribute("fstime3",fstime3);
		request.setAttribute("fetime3",fetime3);
        return "manage/activity/actconfig";
	}

	/**
	 * ajax同步
	 * 将所有官方商品同步到所有便利店,如果已存在跳过否则上架
	 * @return
	 */
	@RequestMapping("/tb")
    public void tb(HttpServletRequest request, HttpServletResponse response){
		System.out.println("===================开始同步=================");
		String str = "1";
		try{
			//所有店铺
			List<StoreInfo> sil = storeInfoService.getStoreInfoListAll();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String creattime = sdf.format(new Date());
			Map<String, Object> map = new HashMap<String, Object>();
			//循环所有店铺
			for (int i = 0; i < sil.size(); i++) {
				//查询所有的官方商品并左关联上架信息
				map.put("storeid", sil.get(i).getId());
				List<ProductInfo> pil = productInfoService.getOfficialProductInfoLeftStoreProductList(map);
				for (int j = 0; j < pil.size(); j++) {
					if(pil.get(j).getStoreid()==null){
						System.out.println(sil.get(i).getStorename()+"=====未上架====="+pil.get(j).getName());
						
						Map<String, Object> spmap = new HashMap<String, Object>();
						spmap.put("uid", sil.get(i).getId());
						spmap.put("id", pil.get(j).getId());
						spmap.put("salenum", pil.get(j).getUpnum());
						spmap.put("saleprice", pil.get(j).getPrice());
						spmap.put("createtime", creattime);
						spmap.put("isdel", "0");
						shelvesService.storeProductAdd(spmap);//上架
					}
				}
			}
			response.setContentType("text/plain; charset=utf-8"); 
			response.getWriter().write(str);
			System.out.println("===================结束同步=================");
		}catch(Exception e){
			e.printStackTrace();
			try {
				str = "2";
				response.setContentType("text/plain; charset=utf-8"); 
				response.getWriter().write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
}
