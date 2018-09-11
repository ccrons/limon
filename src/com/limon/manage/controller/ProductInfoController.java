package com.limon.manage.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
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
import com.limon.store.service.ShelvesService;
import com.limon.store.service.StoreAdService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.JSONUtil;

/**
 * 产品库管理->产品信息
 * 项目名称：limon   
 * 类名称：ProductInfoController
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午9:14:20   
 * @version v1.0
 */
@Controller
@RequestMapping("/productinfo")
public class ProductInfoController extends BaseController{
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private ShelvesService shelvesService;
	@Autowired
	private StoreAdService storeAdService;
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
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sname",sname);
		map.put("stype",stype);
		map.put("sbrand",sbrand);
		map.put("sisself",sisself);
		map.put("sisofficial",sisofficial);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=productInfoService.getProductInfoListCount(map);
		List<ProductInfo> productinfolist = productInfoService.getProductInfoList(map);
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
		request.setAttribute("sisself", sisself);
		request.setAttribute("sisofficial", sisofficial);
		request.setAttribute("page",page);
		
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
		
		request.setAttribute("datas",productinfolist);
        return "/manage/productinfo/list";
    }
	/**
	 * 产品信息编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String productinfoedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		ProductInfo productinfo = productInfoService.getProductInfo(id);
		ProductType pt = productTypeService.getProductType(productinfo.getType()+"");
		//判断还可上传几张图片
		int uploadLimit = 0;
		if(productinfo.getPic1() == null || productinfo.getPic1().equals("")){uploadLimit++;}
		if(productinfo.getPic2() == null || productinfo.getPic2().equals("")){uploadLimit++;}
		if(productinfo.getPic3() == null || productinfo.getPic3().equals("")){uploadLimit++;}
		if(productinfo.getPic4() == null || productinfo.getPic4().equals("")){uploadLimit++;}
		if(productinfo.getPic5() == null || productinfo.getPic5().equals("")){uploadLimit++;}
		request.setAttribute("uploadLimit",uploadLimit);
		System.out.println("还可以上传:"+uploadLimit+"张图片");
		List<ProductBrand> productbrandlist = productBrandService.getProductBrandListAll();
		List<ProductType> producttypelist = productTypeService.getTwoLevelProductTypeList();
		request.setAttribute("productbrandlist",productbrandlist);
		request.setAttribute("producttypelist",producttypelist);
		request.setAttribute("productinfo",productinfo);
		request.setAttribute("pt",pt);
        return "/manage/productinfo/edit";
    }
	/**
	 * 产品信息新增页面
	 * @return
	 */
	@RequestMapping("/add")
    public String productinfoadd(HttpServletRequest request, HttpServletResponse response){
		List<ProductBrand> productbrandlist = productBrandService.getProductBrandListAll();
		List<ProductType> producttypelist = productTypeService.getTwoLevelProductTypeList();
		request.setAttribute("productbrandlist",productbrandlist);
		request.setAttribute("producttypelist",producttypelist);
		
		return "/manage/productinfo/add";
    }
	/**
	 * ajax获取二级分类
	 * @return
	 */
	@RequestMapping("/getTypeLevel2")
    public void getTypeLevel2(HttpServletRequest request, HttpServletResponse response){
		try{
			String pid=this.getParaString("pid");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("pid",pid);
			List<ProductType> typelist=new ArrayList<ProductType>(0);
			if(pid!=""){
				typelist=productTypeService.getProductTypeListByPid(map);
			}
			String json=JSON.toJSONString(typelist,JSONUtil.features);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	/**
	 * 产品信息保存
	 * @return
	 */
	@RequestMapping("/save")
    public String productinfosave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		String returnurl="";
		try {
			String id = this.getParaString("id");
			String name = this.getParaString("name");
			String type = this.getParaString("type");
			String brand = this.getParaString("brand");
			String price = this.getParaString("price");
			String sprice = this.getParaString("sprice");
			String proxyprice = this.getParaString("proxyprice");
			String stockprice = this.getParaString("stockprice");
			String unit = this.getParaString("unit");
			String weight = this.getParaString("weight");
			String paddress = this.getParaString("paddress");
			String isself = this.getParaString("isself");
			String upnum = this.getParaString("upnum");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String creattime = sdf.format(new Date());
			String description = this.getParaString("description");
			String isonline = this.getParaString("isonline");
			
			String isrec = this.getParaString("isrec");
			//String status = this.getParaString("status");
			String isofficial = this.getParaString("isofficial");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("type", type);
			map.put("brand", brand);
			map.put("price", price);
			map.put("unit", unit);
			map.put("weight", weight);
			map.put("creattime", creattime);
			map.put("description", description);
			map.put("isonline", isonline);
			map.put("isdel", 0);
			map.put("isself", isself);
			map.put("upnum", upnum);
			
			map.put("sprice", sprice);	
			map.put("proxyprice", proxyprice);
			map.put("stockprice", stockprice);	
			map.put("paddress", paddress);
			map.put("isrec", isrec);
			map.put("status", "0");
			map.put("isofficial", isofficial);
			
			//上传图片
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile0 = multipartRequest.getFile("file0");
			
			String imgurl = this.getParaString("imgurl");
			if(imgurl.equals("1")){//当值为1的时候保持不变
				
			}else if(imgurl.equals("") && (multipartFile0.getOriginalFilename()==null || multipartFile0.getOriginalFilename().equals(""))){//当值为空时 数据置空
				map.put("imgurl", "");
			}else{//上传图片
				FileInfo fi0 = uploadFile(multipartFile0,"product");
				map.put("imgurl", fi0.getFilePath());
			}
			
			
			String pic1 = this.getParaString("pic1");
			String pic2 = this.getParaString("pic2");
			String pic3 = this.getParaString("pic3");
			String pic4 = this.getParaString("pic4");
			String pic5 = this.getParaString("pic5");
			// /temp/20150706212616635.png
			Date d=new Date();//当前时间
			SimpleDateFormat ny = new SimpleDateFormat("yyyyMM");//年月
			SimpleDateFormat r = new SimpleDateFormat("dd"); //日
			//获取物理地址
			String realpath = request.getSession().getServletContext().getRealPath("/");
			//拼写新的存储地址 priduct/年月/日
			String path = "product/"+ny.format(d)+"/"+r.format(d)+"/";
			// 创建文件夹   
			File file = new File(realpath+"upload/"+path);     
			if (!file.exists()) {     
				file.mkdirs();  
			}
			//System.out.println(realpath+"upload"+pic1+"========="+realpath+"upload/"+path+pic1.replace("/temp/", ""));
			
			//处理图片1    值为1时保持不变
			if(!pic1.equals("")&&!pic1.equals("1")){
				//把文件从临时文件夹移动到正式文件夹
				CommonUtil.copyFile(realpath+"upload"+pic1, realpath+"upload/"+path+pic1.replace("/temp/", ""));
				map.put("pic1", "upload/"+path+pic1.replace("/temp/", ""));
				//删除临时文件
				this.removeFile(realpath+"upload"+pic1);
			}else if(pic1.equals("")){
				map.put("pic1", "");
			}
			
			//处理图片2
			if(!pic2.equals("")&&!pic2.equals("1")){
				CommonUtil.copyFile(realpath+"upload"+pic2, realpath+"upload/"+path+pic2.replace("/temp/", ""));
				map.put("pic2", "upload/"+path+pic2.replace("/temp/", ""));
				this.removeFile(realpath+"upload"+pic2);
			}else if(pic2.equals("")){
				map.put("pic2", "");
			}

			//处理图片3
			if(!pic3.equals("")&&!pic3.equals("1")){
				CommonUtil.copyFile(realpath+"upload"+pic3, realpath+"upload/"+path+pic3.replace("/temp/", ""));
				map.put("pic3", "upload/"+path+pic3.replace("/temp/", ""));
				this.removeFile(realpath+"upload"+pic3);
			}else if(pic3.equals("")){
				map.put("pic3", "");
			}

			//处理图片4
			if(!pic4.equals("")&&!pic4.equals("1")){
				CommonUtil.copyFile(realpath+"upload"+pic4, realpath+"upload/"+path+pic4.replace("/temp/", ""));
				map.put("pic4", "upload/"+path+pic4.replace("/temp/", ""));
				this.removeFile(realpath+"upload"+pic4);
			}else if(pic4.equals("")){
				map.put("pic4", "");
			}

			//处理图片5
			if(!pic5.equals("")&&!pic5.equals("1")){
				CommonUtil.copyFile(realpath+"upload"+pic5, realpath+"upload/"+path+pic5.replace("/temp/", ""));
				map.put("pic5", "upload/"+path+pic5.replace("/temp/", ""));
				this.removeFile(realpath+"upload"+pic5);
			}else if(pic5.equals("")){
				map.put("pic5", "");
			}
			
			if(id==null || "".equals(id)){//新增
				int inte = productInfoService.productInfoAdd(map);
				
				if(inte>0){
					LogUtil.logOperation("新增产品信息:"+name+"失败,同类型同品牌下产品信息名称不可重复!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="2";
				}else{
					if(isofficial.equals("1")){//如果为官方供应 在所有便利店上架
						//查询所有的店铺
						List<StoreInfo> sil = storeInfoService.getStoreInfoListAll();
						for (int i = 0; i < sil.size(); i++) {
							Map<String, Object> spmap = new HashMap<String, Object>();
							spmap.put("uid", sil.get(i).getId());
							spmap.put("id", map.get("id"));
							spmap.put("salenum", upnum);
							spmap.put("saleprice", price);
							spmap.put("createtime", creattime);
							spmap.put("isdel", "0");
							shelvesService.storeProductAdd(spmap);//上架
						}
					}
					LogUtil.logOperation("新增产品信息:"+name+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="1";
				}
				returnurl="/manage/productinfo/add";
			}else{//编辑
				ProductInfo productinfoold = productInfoService.getProductInfo(id);//未修改以前的产品信息
				Integer inte = productInfoService.productInfoUpdate(map);
				if(inte>0){
					LogUtil.logOperation("修改产品信息:"+name+"失败,同类型同品牌下产品信息名称不可重复!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="2";
				}else{
					//System.out.println(productinfoold.getIsofficial()+"==============="+isofficial);
					if(productinfoold.getIsofficial()==0 && isofficial.equals("1")){//如果原先为非官方改为官方供应 在所有便利店上架
						//查询所有的店铺
						List<StoreInfo> sil = storeInfoService.getStoreInfoListAll();
						for (int i = 0; i < sil.size(); i++) {
							//判断如果已经上架了 不进行操作
							Map<String, Object> smap = new HashMap<String, Object>();
							smap.put("uid", sil.get(i).getId());
							smap.put("id", map.get("id"));
							int snum = shelvesService.getStoreProductCount(smap);
							System.out.println(snum+"=============================");
							if(snum==0){
								Map<String, Object> spmap = new HashMap<String, Object>();
								spmap.put("uid", sil.get(i).getId());
								spmap.put("id", map.get("id"));
								spmap.put("salenum", upnum);
								spmap.put("saleprice", price);
								spmap.put("createtime", creattime);
								spmap.put("isdel", "0");
								
								shelvesService.storeProductAdd(spmap);//上架
							}
						}
					}
					
					if(productinfoold.getIsofficial()==1 && isofficial.equals("0")){//如果原先为官方改为非官方供应 在所有便利店下架
						//shelvesService.storeProductDelByProductid(map.get("id")+"");//下架
					}
					LogUtil.logOperation("修改产品信息:"+name+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
					rs="1";
				}
				returnurl="/manage/productinfo/edit";
			}
			ProductInfo productinfo = productInfoService.getProductInfo(id);
			request.setAttribute("productinfo",productinfo);
			request.setAttribute("uploadLimit","0");
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		List<ProductBrand> productbrandlist = productBrandService.getProductBrandListAll();
		List<ProductType> producttypelist = productTypeService.getTwoLevelProductTypeList();
		request.setAttribute("productbrandlist",productbrandlist);
		request.setAttribute("producttypelist",producttypelist);
		request.setAttribute("rs", rs);
		return returnurl;
	}
	
	/**
	 * 产品信息删除
	 * @return
	 */
	@RequestMapping("/del")
    public String productinfodel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String name = this.getParaString("name");
		String sname = this.getParaString("sname");
		String stype = this.getParaString("stype");
		String sbrand = this.getParaString("sbrand");
		String currentPage=this.getParaString("currentPage");
		try {
			String [] ids=id.split(",");
			for(String is:ids){
				productInfoService.productInfoDel(is);
			}
			LogUtil.logOperation("删除产品信息:"+name+"等", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
			rs = "1";
		} catch (Exception e) {
			rs = "0";
		}
		return "redirect:list?currentPage="+currentPage+"&sname="+URLEncoder.encode(sname,"UTF-8")+"&stype="+stype+"&sbrand="+sbrand+"&rs="+rs;
		
    }
	
	/**
	 * 导入页面
	 * @return
	 */
	@RequestMapping("/import")
    public String productinfoimport(HttpServletRequest request, HttpServletResponse response){
		return "/manage/productinfo/import";
    }
	
	/**
	 * 导入保存
	 * @return
	 */
	@RequestMapping("/importsave")
    public String productinfoimportsave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		//成功条数
		int OKNUM = 0;
		//成功行号
		String OKXH = "";
		//失败条数
		int NOTOKNUM = 0;
		//失败行号
		String NOTOKXH = "";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("productfile");
			if(multipartFile.getOriginalFilename()!=""){
				//查询所有的类型
				Map<String, Integer> typemap = new HashMap<String, Integer>();
				List<ProductType> producttypelist = productTypeService.getTwoLevelProductTypeList();
				for (int i = 0; i < producttypelist.size(); i++) {
					for (int j = 0; j < producttypelist.get(i).getChildproductpypelist().size(); j++) {
						typemap.put(producttypelist.get(i).getChildproductpypelist().get(j).getTypename(), producttypelist.get(i).getChildproductpypelist().get(j).getId());

						System.out.println(producttypelist.get(i).getChildproductpypelist().get(j).getTypename()+"======"+producttypelist.get(i).getChildproductpypelist().get(j).getId());
					}
				}
				//查询所有的品牌
				Map<String, Integer> brandmap = new HashMap<String, Integer>();
				List<ProductBrand> productbrandlist = productBrandService.getProductBrandListAll();
				for (int i = 0; i < productbrandlist.size(); i++) {
					brandmap.put(productbrandlist.get(i).getBrandname(), productbrandlist.get(i).getId());
				}
				
				
				//创建日期
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				String creattime = sdf.format(new Date());
				
				InputStream is = multipartFile.getInputStream();
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	
		        ProductInfo productinfo = null;
		        //List<ProductInfo> productinfolist = new ArrayList<ProductInfo>();
		        // 循环工作表Sheet
		        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
		            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
		            if (hssfSheet == null) {
		                continue;
		            }
		            // 循环行Row
		            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		                if (hssfRow == null) {
		                	continue;
		                }
		                productinfo = new ProductInfo();
		                // 循环列Cell
		                //0产品名称 不为空 并且长度小于100
		                HSSFCell name = hssfRow.getCell(0);
		                if (name == null || getValue(name).length()>100) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品名称为空或超过100字符,";
		                    continue;
		                }
		                productinfo.setName(getValue(name));
		                
		                //1产品类型	
		                HSSFCell type = hssfRow.getCell(1);
		                if (type == null) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品类型为空,";
		                    continue;
		                }
		                Integer typeid = typemap.get(getValue(type));
		                if (typeid == null || typeid.equals("")) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品类型未查到,";
		                    continue;
		                }
		                productinfo.setType(typeid);
		                
		                //2产品品牌
		                HSSFCell brand = hssfRow.getCell(2);
		                if (brand == null) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品品牌为空,";
		                    continue;
		                }
		                Integer brandid = brandmap.get(getValue(brand));
		                if (brandid == null) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品品牌未查到,";
		                    continue;
		                }
		                productinfo.setBrand(brandid);
		                
		                
		                //3产品价格	
		                HSSFCell price = hssfRow.getCell(3);
		                if (price == null || getValue(price).length()>10) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品价格为空或超过10字符,";
		                    continue;
		                }
		                if(isInteger(getValue(price)) || isDouble(getValue(price))){
		                	productinfo.setPrice(Double.parseDouble(getValue(price)));	
		                }else{
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品价格不是数字,";
		                    continue;
		                }
		                
		                //4销售价格	
		                HSSFCell sprice = hssfRow.getCell(4);
		                if (sprice == null || getValue(sprice).length()>10) {
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：销售价格为空或超过10字符,";
		                    continue;
		                }
		                if(isInteger(getValue(sprice)) || isDouble(getValue(sprice))){
		                	productinfo.setSprice(Double.parseDouble(getValue(sprice)));	
		                }else{
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：销售价格不是数字,";
		                    continue;
		                }
		                
		                //5产品规格	
		                HSSFCell unit = hssfRow.getCell(5);
		                if(unit == null){
		                	productinfo.setUnit("");
		                }else{
		                	if (getValue(unit).length()>100) {
		                		NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品规格超过100字符,";
			                    continue;
			                }
		                	productinfo.setUnit(getValue(unit));
		                }
		                
		                //6产品描述
		                HSSFCell description = hssfRow.getCell(6);
		                if(description == null){
		                	productinfo.setDescription("");
		                }else{
		                	if (getValue(description).length()>500) {
		                		NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品描述超过500字符,";
			                    continue;
			                }
		                	productinfo.setDescription(getValue(description));
		                }
		                
		                //7产品分类
		                HSSFCell isself = hssfRow.getCell(7);
		                if(isself == null){
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品分类为空";
		                    continue;
		                }else{
		                	if (getValue(isself).equals("便利店商品")) {
		                		productinfo.setIsself(0);
			                }else if(getValue(isself).equals("自营店商品")){
			                	productinfo.setIsself(1);
			                }else{
			                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品分类错误";
			                    continue;
			                }
		                }
		                
		                //8默认库存
		                HSSFCell upnum = hssfRow.getCell(8);
		                if(upnum == null){
		                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：默认库存为空";
		                    continue;
		                }else{
		                	String u=getValue(upnum);
		                	if(u.lastIndexOf(".")!=-1&&u.length()>0){
		                		u=u.substring(0,u.lastIndexOf("."));
		                	}
		                	if(isInteger(u)){
			                	productinfo.setUpnum(Integer.parseInt(u));	
			                }else{
			                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：默认库存不是数字,";
			                    continue;
			                }
		                }
		                
		                //9产地
		                HSSFCell paddress = hssfRow.getCell(9);
		                if(paddress == null){
		                	productinfo.setPaddress("");
		                }else{
		                	if (getValue(paddress).length()>100) {
		                		NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产地超过100字符,";
			                    continue;
			                }
		                	productinfo.setPaddress(getValue(paddress));
		                }
		                
		                //10是否是官方商品
		                HSSFCell isofficial = hssfRow.getCell(10);
		                if(isofficial == null){
		                	productinfo.setIsofficial(0);
		                }else{
		                	String u=getValue(isofficial);
		                	if(u.lastIndexOf(".")!=-1&&u.length()>0){
		                		u=u.substring(0,u.lastIndexOf("."));
		                	}
		                	if(isInteger(u)){
			                	productinfo.setIsofficial(Integer.parseInt(u));	
			                }else{
			                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：官方商品不是数字,";
			                    continue;
			                }
		                }
		                
		                //11是否是推荐商品
		                HSSFCell isrec = hssfRow.getCell(11);
		                if(isrec == null){
		                	productinfo.setIsrec(0);
		                }else{
		                	String u=getValue(isrec);
		                	if(u.lastIndexOf(".")!=-1&&u.length()>0){
		                		u=u.substring(0,u.lastIndexOf("."));
		                	}
		                	if(isInteger(u)){
			                	productinfo.setIsrec(Integer.parseInt(u));	
			                }else{
			                	NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：推荐商品不是数字,";
			                    continue;
			                }
		                }

						//12产品重量
						HSSFCell weight = hssfRow.getCell(12);
						if(weight == null){
							productinfo.setWeight(0);
						}else{
							if (getValue(weight).length()>10) {
								NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：产品规格超过10字符,";
								continue;
							}
							productinfo.setWeight(Integer.parseInt(getValue(weight)));
						}

						//13进货价
						HSSFCell stockprice = hssfRow.getCell(13);
						if(stockprice == null){
							productinfo.setStockprice(0.0);
						}else{
							if (getValue(stockprice).length()>10) {
								NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：进货价超过10字符,";
								continue;
							}
							productinfo.setStockprice(Double.parseDouble(getValue(stockprice)));
						}
						
						//14代理价
						HSSFCell proxyprice = hssfRow.getCell(14);
						if(proxyprice == null){
							productinfo.setProxyprice(0.0);
						}else{
							if (getValue(proxyprice).length()>10) {
								NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：代理价超过10字符,";
								continue;
							}
							productinfo.setProxyprice(Double.parseDouble(getValue(proxyprice)));
						}
						

		                //导入数据
			        	Map<String, Object> map = new HashMap<String, Object>();
						map.put("name", productinfo.getName());
						map.put("type", productinfo.getType());
						map.put("stype", productinfo.getType());
						map.put("brand", productinfo.getBrand());
						map.put("sbrand", productinfo.getBrand());
						map.put("price", productinfo.getPrice());
						map.put("sprice", productinfo.getSprice());
						map.put("proxyprice", productinfo.getProxyprice());
						map.put("stockprice", productinfo.getStockprice());
						map.put("unit", productinfo.getUnit());
						map.put("weight", productinfo.getWeight());
						map.put("creattime", creattime);
						map.put("description", productinfo.getDescription());
						map.put("isself", productinfo.getIsself());
						map.put("upnum", productinfo.getUpnum());
						map.put("paddress", productinfo.getPaddress());
						map.put("isofficial", productinfo.getIsofficial());
						map.put("isrec", productinfo.getIsrec());
						Integer samenum = productInfoService.getProductInfoListCount(map);
						if(samenum==0){
							System.out.println("产品名称："+map.get("name"));
				        	System.out.println("产品类型："+map.get("type"));
				        	System.out.println("产品品牌："+map.get("brand"));
				        	System.out.println("产品价钱："+map.get("price"));
				        	System.out.println("销售价钱："+map.get("sprice"));
				        	System.out.println("产品规格："+map.get("unit"));
							System.out.println("产品重量："+map.get("weight"));
				        	System.out.println("产品描述："+map.get("description"));
				        	System.out.println("产品分类："+map.get("isself"));
				        	System.out.println("默认库存："+map.get("upnum"));
				        	System.out.println("创建时间："+map.get("creattime"));
				        	System.out.println("---------------------------------");
				        	productInfoService.productInfoImport(map);
						}else{
							NOTOKNUM++;NOTOKXH = NOTOKXH + "第" + (rowNum+1) + "行：同类型同品牌下产品名称不能相同,";
		                    continue;
						}
		                
		                //记录成功数量
		                OKNUM++;OKXH = OKXH + (rowNum+1) + ",";
		            }
		        }
		        
		        //输出导入失败原因
		        System.out.println("导入失败行数和原因");
		        System.out.println(NOTOKXH);
		        
		        //记录日志
		        if(!NOTOKXH.equals("")){
		        	LogUtil.logOperation("导入失败行数和原因:"+NOTOKXH, this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		        }else{
		        	LogUtil.logOperation("导入产品信息成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		        }
		        if(OKNUM!=0){
		        	rs="1";
		        }else{
		        	rs="2";
	            }
		    }else{
		    	rs="2";
			}

		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		request.setAttribute("OKNUM", OKNUM);
		request.setAttribute("NOTOKNUM", NOTOKNUM);
		request.setAttribute("NOTOKXH",NOTOKXH);
		return "/manage/productinfo/import";
    }
	/**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
       } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
           // 返回数值类型的值
           return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
           return String.valueOf(hssfCell.getStringCellValue());
        }
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
		request.setAttribute("productinfo",productinfo);
        return "/manage/productinfo/info";
    }
	/**
	 * ajax删除图片
	 * 1 失败 2 成功
	 * @throws Exception
	 */
	@RequestMapping("/imgdel")
	public String imgdel(HttpServletRequest request, HttpServletResponse response){
		String type = "1";
		String id = this.getParaString("id");
		String m = this.getParaString("m");
		//System.out.println(id+"------------"+m);
		if(!id.equals("")&&!m.equals("")){
			ProductInfo productinfo = productInfoService.getProductInfo(id);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put(m, "1");
			try {
				productInfoService.productInfoImgDel(map);
				removeFile(productinfo.getImgurl());
				type="2";
			} catch (Exception e) {
				
			}
		}
		//request.setAttribute("type",type);
		return type;
	}
	
	/**
	 * 批量文件上传
	 * @return
	 */
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response){
		String responseStr="";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			String path = request.getSession().getServletContext().getRealPath("upload"); 
			String newFileName = CommonUtil.getDateFormatMillisecond().format(new Date());
			String savepath = "/temp";
			String ctxPath=path + savepath;
			File file = new File(ctxPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = null;
			String fileType = null;
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				// 上传文件   
				MultipartFile mf = entity.getValue();
				fileName = mf.getOriginalFilename();
				fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
				newFileName = newFileName + fileType;
				System.out.println("路径:"+ctxPath + "/" + newFileName);
				//获取原文件名  
				File uploadFile = new File(ctxPath + "/" + newFileName); 

				FileCopyUtils.copy(mf.getBytes(), uploadFile);      
				responseStr = savepath + "/" + newFileName;      
			}    
			response.getWriter().write(responseStr);
		} catch (IOException e) {
			e.printStackTrace();
			responseStr="上传失败";
			try {
				response.getWriter().write(responseStr);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
