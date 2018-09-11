package com.limon.store.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.limon.base.common.LogUtil;
import com.limon.base.controller.BaseController;
import com.limon.base.model.FileInfo;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.manage.model.TimeLimit;
import com.limon.store.model.StoreAd;
import com.limon.store.service.StoreAdService;
import com.limon.util.CommonUtil;

/**
 * 店铺管理->广告管理
 * 项目名称：limon   
 * 类名称：StoreAdController
 * 创建人：WN	
 * 创建时间：2015年9月1日 下午9:16:56   
 * @version v1.0
 */
@Controller
@RequestMapping("/storead")
public class StoreAdController extends BaseController{
	@Autowired
	private StoreAdService storeAdService;
	
	/**
	 * 广告列表
	 * @return
	 */
	@RequestMapping("/list")
    public String storeAdList(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		//参数接收
		String sadname = this.getParaString("sadname");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sadname",sadname);
		map.put("uid",uid);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=storeAdService.getStoreAdListCount(map);
		List<StoreAd> storeadlist = storeAdService.getStoreAdList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(storeadlist);
		//返回页面参数
		request.setAttribute("sadname", sadname);
		request.setAttribute("page",page);
				
		//弹出信息标志位
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
				
		request.setAttribute("datas",storeadlist);
        return "/store/storead/list";
    }
	/**
	 * 广告编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String storeadedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		StoreAd storead = storeAdService.getStoreAd(id);
		request.setAttribute("storead",storead);
        return "/store/storead/edit";
    }
	/**
	 * 广告新增页面
	 * @return
	 */
	@RequestMapping("/add")
    public String storeadadd(HttpServletRequest request, HttpServletResponse response){
		return "/store/storead/add";
    }
	
	/**
	 * 广告保存
	 * @return
	 */
	@RequestMapping("/save")
    public String storeadsave(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		String rs="0";
		String returnurl="";
		String id = this.getParaString("id");
		String adname = this.getParaString("adname");
		String adcontent = this.getParaString("adcontent");
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adname", adname);
			map.put("adcontent", adcontent);
			map.put("storeid", uid);
			
			//上传图片
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile0 = multipartRequest.getFile("file0");
			String imgurl = this.getParaString("imgurl");
			if(imgurl.equals("1")){//当值为1的时候保持不变
				
			}else if(imgurl.equals("") && (multipartFile0.getOriginalFilename()==null || multipartFile0.getOriginalFilename().equals(""))){//当值为空时 数据置空
				map.put("imgurl", "");
			}else{//上传图片
				FileInfo fi0 = uploadFile(multipartFile0,"storead");
				map.put("imgurl", fi0.getFilePath());
			}
			
			if(id==null || "".equals(id)){
				map.put("createtime", new Date());
				storeAdService.storeAdAdd(map);
				LogUtil.logOperation("新增广告:"+adname+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="1";
				returnurl="/store/storead/add";
			}else{
				map.put("id", id);
				storeAdService.storeAdUpdate(map);
				LogUtil.logOperation("修改广告:"+adname+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="1";
				returnurl="/store/storead/edit";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		StoreAd storead=new StoreAd();
		if(id!=null && !"".equals(id)){
			storead.setId(Integer.parseInt(id));
		}
		storead.setAdname(adname);
		request.setAttribute("storead",storead);
		request.setAttribute("rs", rs);
		return returnurl;
	}
	
	/**
	 * 广告删除
	 * @return
	 */
	@RequestMapping("/del")
    public String storeaddel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String adname = this.getParaString("adname");
		String sadname = this.getParaString("sadname");
		String currentPage=this.getParaString("currentPage");
		
		storeAdService.storeAdDel(id);
		LogUtil.logOperation("删除广告:"+adname+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		rs="1";
		
		return "redirect:list?currentPage="+currentPage+"&sadname="+URLEncoder.encode(sadname,"UTF-8")+"&rs="+rs;
	}
	
	/**
	 * 广告上线
	 * @return
	 */
	@RequestMapping("/up")
    public String storeadup(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Integer uid = this.getLoginUser().getId();
		String rs="2";
		String id = this.getParaString("id");
		String isup = this.getParaString("isup");
		String adname = this.getParaString("adname");
		String sadname = this.getParaString("sadname");
		String currentPage=this.getParaString("currentPage");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isup", isup);
		map.put("uid", uid);
		Integer num = storeAdService.getStoreAdUpCount(map);
		if(isup.equals("1") && num>=5){
			rs="5";//上线失败 最多上线3条
		}else{
			storeAdService.storeAdUp(map);
			if(isup.equals("1")){
				LogUtil.logOperation("上线广告:"+adname+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="3";//上线成功
			}else{
				LogUtil.logOperation("下线广告:"+adname+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="4";//下线成功
			}
		}
		
		return "redirect:list?currentPage="+currentPage+"&sadname="+URLEncoder.encode(sadname,"UTF-8")+"&rs="+rs;
	}
	
	/**
	 * 广告图片上传
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
    public String storeadupload(HttpServletRequest request, HttpServletResponse response) {
		//上传图片
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile0 = multipartRequest.getFile("imgFile");
		FileInfo fi0 = uploadFile(multipartFile0,"storead");
		
		JSONObject obj = new JSONObject();   
        obj.put("error", 0);   
        obj.put("url", basePath + "/" + fi0.getFilePath() );   
        return obj.toString();
	}
	
	/**
	 * 广告查看页面
	 * @return
	 */
	@RequestMapping("/info")
    public String storeadinfo(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		StoreAd storead = storeAdService.getStoreAd(id);
		request.setAttribute("storead",storead);
        return "/store/storead/info";
    }
	
	/**
	 * 广告wap页面
	 * @return
	 */
	@RequestMapping("/show")
    public String show(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		Integer sid=this.getParaInteger("sid");
		StoreAd storead = storeAdService.getStoreAd(id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sid",sid);
		map.put("aid",id);
		List<StoreAdProductInfo> saplist=storeAdService.getStoreAdProductByStoreIdMap(map);
		for(StoreAdProductInfo sap:saplist){
			//活动验证
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(sap.getId()+"");
			if(ap!=null){
				sap.setAdid(ap.getAdid()+"");
				sap.setAdprice(ap.getAdprice());
			}
			TimeLimit tl = storeAdService.getTimeLimitByProductId(sap.getId()+"");
			if(tl!=null){
				sap.setTltime(tl.getTltime());
				sap.setTlprice(tl.getTlprice());
			}
		}
		request.setAttribute("storead",storead);
		request.setAttribute("saplist",saplist);
		request.setAttribute("sid", sid);
        return "/store/storead/wapad";
    }
}
