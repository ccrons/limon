package com.limon.base.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.limon.base.common.PageModel;
import com.limon.base.model.FileInfo;
import com.limon.base.model.SysUser;
import com.limon.util.CommonUtil;

/**
 * @author gqf
 * 
 * 控制层基类
 * 2015-2-10 下午01:38:00
 */
@Controller
public class BaseController {
	protected HttpServletRequest request; 
	protected HttpServletResponse response;
	protected String basePath;
	protected String pageTitle;
	protected String defaultPassword;
	//分页模型设置
	protected PageModel page=new PageModel();
	protected String allPath;
	
	/**
	 * 全局参数加载
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{  
        this.request = request;
        this.request.setCharacterEncoding("UTF-8");
        this.response = response; 
        this.response.setContentType("text/html;charset=UTF-8");
        this.basePath = request.getContextPath();
        this.allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
        this.pageTitle = "榴莲购服务管理平台"; 
        this.defaultPassword="123456";
        //应用程序根目
        request.setAttribute("basePath", basePath);
        request.setAttribute("allPath", allPath);
        //网页title
        request.setAttribute("pageTitle", pageTitle);
        
        page=new PageModel();
        //当前页面信息
		if(request.getParameter("currentPage")!=null&&!request.getParameter("currentPage").equals("")){
			page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage").trim()));
		}
    }
	
	/**
	 * 获取String类型参数
	 * @param para
	 * @return
	 */
	public String getParaString(String para){
		String p="";
		if(request.getParameter(para)!=null){
			p=request.getParameter(para.trim());
		}
		return p;
	}
	
	/**
	 * 获取Integer类型参数
	 * @param para
	 * @return
	 */
	public Integer getParaInteger(String para){
		Integer p=0;
		if(request.getParameter(para)!=null&&!request.getParameter(para).equals("")){
			try{
				p=Integer.parseInt(request.getParameter(para).trim());
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		return p;
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public SysUser getLoginUser(){
		return (SysUser)request.getSession().getAttribute("loginUser");
	}
	
	/**
	 * 上传文件
	 * @return
	 */
	//文件上传,不指定存储名,默认用yyyyMMddHHmmssSSS毫秒时间戳
	protected FileInfo uploadFile(MultipartFile multipartFile,String savepath){
		//定义文件属性对象
		FileInfo fileInfo=new FileInfo();
		
		//文件上传成功
		if(multipartFile!=null){
			System.out.println("上传文件:"+multipartFile.getOriginalFilename());
			try{
				Date d=new Date();
				SimpleDateFormat sdf=CommonUtil.getDateFormatMillisecond();
				SimpleDateFormat ny = new SimpleDateFormat("yyyyMM");
				SimpleDateFormat r = new SimpleDateFormat("dd"); 
				String path = System.getProperty("catalina.home")+"/webapps/ROOT/upload"; 
				File file = new File(path+"/"+savepath+"/"+ny.format(d)+"/"+r.format(d));
				System.out.println(path+"/"+savepath+"/"+ny.format(d)+"/"+r.format(d));
				file.mkdirs();
				//上传文件并捕捉异常
				String fileName = multipartFile.getOriginalFilename();
				fileName = sdf.format(d)+fileName.substring(fileName.lastIndexOf("."),fileName.length());
				SaveFileFromInputStream(multipartFile.getInputStream(),path+"/"+savepath+"/"+ny.format(d)+"/"+r.format(d),fileName);
				
				fileInfo.setFileName(multipartFile.getOriginalFilename());
				fileInfo.setFileStoreName("");
				fileInfo.setFilePath("upload/"+savepath+"/"+ny.format(d)+"/"+r.format(d)+"/"+fileName);
				fileInfo.setFailReason("");
				fileInfo.setUploadResult(1);
				fileInfo.setFileType(multipartFile.getContentType());
				fileInfo.setUploadDate(new Date());
				fileInfo.setContentType(multipartFile.getContentType());
				fileInfo.setFileSize(CommonUtil.formatFileSize(multipartFile.getSize()));
				fileInfo.setFileLongSize(multipartFile.getSize());
			
			}catch(Exception e){
				fileInfo.setUploadResult(0);
				fileInfo.setFailReason(e.getMessage());
			}
		}else{
			fileInfo.setUploadResult(0);
			fileInfo.setFailReason("系统错误");
		}
		return fileInfo;
	}
	
	/**保存文件
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
	public void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException{      
        FileOutputStream fs=new FileOutputStream( path + "/"+ filename);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0 ;
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1){
           bytesum+=byteread;
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();      
    }
	//删除指定目录文件
	protected boolean removeFile(String fileFullPath){
		File f=new File(fileFullPath);
		return f.delete();
	}
	/**
	 * 判断字符串是否是浮点数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains(".")){
				return true;
			}else{
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public PageModel getPage() {
		return page;
	}

	public void setPage(PageModel page) {
		this.page = page;
	}  
}
