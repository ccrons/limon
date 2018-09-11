package com.limon.manage.controller;

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
import com.limon.manage.model.Recruit;
import com.limon.manage.service.RecruitService;
import com.limon.util.CommonUtil;

/**
 * 
 * 项目名称：limon
 * 类名称：RecruitController
 * 创建人：WN
 * 创建时间：2015年11月3日下午10:39:07
 * @version v1.0
 */
@Controller
@RequestMapping("/recruit")
public class RecruitController extends BaseController{
	@Autowired
	private RecruitService recruitService;
	
	/**
	 * 招聘列表
	 * @return
	 */
	@RequestMapping("/list")
    public String recruitList(HttpServletRequest request, HttpServletResponse response){
		Integer uid = this.getLoginUser().getId();
		//参数接收
		String sretitle = this.getParaString("sretitle");
		//查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sretitle",sretitle);
		map.put("uid",uid);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		Integer totalRecord=recruitService.getRecruitListCount(map);
		List<Recruit> recruitlist = recruitService.getRecruitList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(recruitlist);
		//返回页面参数
		request.setAttribute("sretitle", sretitle);
		request.setAttribute("page",page);
				
		//弹出信息标志位
		String rs = this.getParaString("rs");
		request.setAttribute("rs", rs);
				
		request.setAttribute("datas",recruitlist);
        return "/manage/recruit/list";
    }
	/**
	 * 招聘编辑页面
	 * @return
	 */
	@RequestMapping("/edit")
    public String recruitedit(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		Recruit recruit = recruitService.getRecruit(id);
		request.setAttribute("recruit",recruit);
        return "/manage/recruit/edit";
    }
	/**
	 * 招聘新增页面
	 * @return
	 */
	@RequestMapping("/add")
    public String recruitadd(HttpServletRequest request, HttpServletResponse response){
		return "/manage/recruit/add";
    }
	
	/**
	 * 招聘保存
	 * @return
	 */
	@RequestMapping("/save")
    public String recruitsave(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		String returnurl="";
		String id = this.getParaString("id");
		String retitle = this.getParaString("retitle");
		String recontent = this.getParaString("recontent");
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("retitle", retitle);
			map.put("recontent", recontent);
			
			if(id==null || "".equals(id)){
				map.put("createtime", new Date());
				recruitService.recruitAdd(map);
				LogUtil.logOperation("新增招聘:"+retitle+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="1";
				returnurl="/manage/recruit/add";
			}else{
				map.put("id", id);
				recruitService.recruitUpdate(map);
				LogUtil.logOperation("修改招聘:"+retitle+"成功!", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="1";
				returnurl="/manage/recruit/edit";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		Recruit recruit = new Recruit();
		if(id!=null && !"".equals(id)){
			recruit.setId(Integer.parseInt(id));
		}
		request.setAttribute("recruit",recruit);
		request.setAttribute("rs", rs);
		return returnurl;
	}
	
	/**
	 * 招聘删除
	 * @return
	 */
	@RequestMapping("/del")
    public String recruitdel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String id = this.getParaString("id");
		String retitle = this.getParaString("retitle");
		String sretitle = this.getParaString("sretitle");
		String currentPage=this.getParaString("currentPage");
		
		recruitService.recruitDel(id);
		LogUtil.logOperation("删除招聘:"+retitle+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
		rs="1";
		
		return "redirect:list?currentPage="+currentPage+"&sretitle="+URLEncoder.encode(sretitle,"UTF-8")+"&rs="+rs;
	}
	
	/**
	 * 招聘上线
	 * @return
	 */
	@RequestMapping("/up")
    public String recruitup(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Integer uid = this.getLoginUser().getId();
		String rs="2";
		String id = this.getParaString("id");
		String isup = this.getParaString("isup");
		String retitle = this.getParaString("retitle");
		String sretitle = this.getParaString("sretitle");
		String currentPage=this.getParaString("currentPage");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isup", isup);
		map.put("uid", uid);
		Integer num = recruitService.getRecruitUpCount(map);
		if(isup.equals("1") && num>=3){
			rs="5";//上线失败 最多上线3条
		}else{
			recruitService.recruitUp(map);
			if(isup.equals("1")){
				LogUtil.logOperation("上线招聘:"+retitle+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="3";//上线成功
			}else{
				LogUtil.logOperation("下线招聘:"+retitle+"成功", this.getLoginUser().getUsername(), CommonUtil.getIpAddr(request));
				rs="4";//下线成功
			}
		}
		
		return "redirect:list?currentPage="+currentPage+"&sretitle="+URLEncoder.encode(sretitle,"UTF-8")+"&rs="+rs;
	}
	
	/**
	 * 招聘图片上传
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
    public String recruitupload(HttpServletRequest request, HttpServletResponse response) {
		//上传图片
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile0 = multipartRequest.getFile("imgFile");
		FileInfo fi0 = uploadFile(multipartFile0,"recruit");
		
		JSONObject obj = new JSONObject();   
        obj.put("error", 0);   
        obj.put("url", basePath + "/" + fi0.getFilePath() );   
        return obj.toString();
	}
	
	/**
	 * 招聘查看页面
	 * @return
	 */
	@RequestMapping("/info")
    public String recruitinfo(HttpServletRequest request, HttpServletResponse response){
		String id = this.getParaString("id");
		Recruit recruit = recruitService.getRecruit(id);
		request.setAttribute("recruit",recruit);
        return "/manage/recruit/info";
    }
}
