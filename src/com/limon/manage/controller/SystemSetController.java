package com.limon.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.limon.base.controller.BaseController;
import com.limon.base.model.FileInfo;
import com.limon.base.model.SysConfig;
import com.limon.base.model.SysLog;
import com.limon.base.model.SysMenu;
import com.limon.base.model.SysUser;
import com.limon.base.service.SysConfigService;
import com.limon.base.service.SysLogService;
import com.limon.base.service.SysUserService;
import com.limon.manage.model.SysMessage;
import com.limon.manage.model.SysRole;
import com.limon.manage.model.SysVersion;
import com.limon.manage.service.SysMessageService;
import com.limon.manage.service.SysRoleService;
import com.limon.manage.service.SysUserMngService;
import com.limon.manage.service.SysVersionService;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;
import com.limon.util.ConfigUtil;
import com.limon.util.DateUtil;
/**
 * @author gqf
 *
 * 系统设置
 * 2015-2-25 下午02:40:46
 */
@Controller
@RequestMapping("/sysset")
public class SystemSetController extends BaseController{
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private SysVersionService sysVersionService;
	@Autowired
	private SysMessageService sysMessageService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserMngService sysUserMngService;
	@Autowired
	private SysConfigService sysConfigService;
	/**
	 * 日志查询
	 * @return
	 */
	@RequestMapping("/loglist")
    public String loglist(HttpServletRequest request, HttpServletResponse response){
		//查询参数接收
		String logcontent=this.getParaString("logcontent");
		String logtype=this.getParaString("logtype");
		String logstime=this.getParaString("logstime");
		if(logstime.equals("")){
			logstime=DateUtil.getLastWeek();
		}
		String logetime=this.getParaString("logetime");
		if(logetime.equals("")){
			logetime=DateUtil.getToday();
		}
		
		//查询条件设置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("logclass","INFO");
		map.put("logcontent",logcontent);
		map.put("logtype",logtype);
		if(!logstime.equals("")){
			map.put("logstime",logstime+" 00:00:00");
		}else{
			map.put("logstime",logstime);
		}
		if(!logetime.equals("")){
			map.put("logetime",logetime+" 23:59:59");
		}else{
			map.put("logetime",logetime);
		}
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		//分页数据查询
		Integer totalRecord=sysLogService.getLogListCount(map);
		List<SysLog> loglist=sysLogService.getLogList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(loglist);
		
		//返回页面参数
		request.setAttribute("logcontent", logcontent);
		request.setAttribute("logtype",logtype);
		request.setAttribute("logstime",logstime);
		request.setAttribute("logetime",logetime);
		request.setAttribute("page",page);
		
        return "manage/sysset/loglist";
	}
	
	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping("/tochangepassword")
    public String tochangepassword(HttpServletRequest request, HttpServletResponse response){
		SysUser loginuser=this.getLoginUser();
		String logtype=(String) request.getSession().getAttribute("logtype");
		//返回页面参数
		String rs="";
		request.setAttribute("userid", loginuser.getId());
		request.setAttribute("logtype", logtype);
		request.setAttribute("rs", rs);
        return "manage/sysset/changepassword";
	}
	
	/**
	 * 修改密码保存
	 * @return
	 */
	@RequestMapping("/changepassword")
    public String changepassword(HttpServletRequest request, HttpServletResponse response){
		String userid=this.getParaString("userid");
		String logtype=this.getParaString("logtype");
		String newpassword=this.getParaString("password");
		boolean result=false;
		if(logtype.equals("0")){
			//便利店登录修改密码
			result=storeInfoService.updatePassword(userid,CommonUtil.md5(newpassword));
		}else if(logtype.equals("1")){
			//管理员登录修改密码
			result=sysUserService.updatePassword(userid,CommonUtil.md5(newpassword));
		}
		String rs="0";
		if(result){
			rs="0";
		}else{
			rs="1";
		}
		request.setAttribute("rs",rs);
        return "manage/sysset/changepassword";
	}
	
	/**
	 * 企业账户信息
	 * @return
	 */
	@RequestMapping("/companyinfo")
    public String companyinfo(HttpServletRequest request, HttpServletResponse response){
		String companyname=ConfigUtil.getConfig("company_name").getConfig_value();
		String companyaccount=ConfigUtil.getConfig("company_account").getConfig_value();
		String companyaddress=ConfigUtil.getConfig("company_address").getConfig_value();
		String companytelphone=ConfigUtil.getConfig("company_telphone").getConfig_value();
		request.setAttribute("companyname",companyname);
		request.setAttribute("companyaccount",companyaccount);
		request.setAttribute("companyaddress",companyaddress);
		request.setAttribute("companytelphone",companytelphone);
        return "manage/sysset/companyinfo";
	}
	
	/**
	 * 默认上架数量
	 * @return
	 */
	@RequestMapping("/configlist")
    public String configlist(HttpServletRequest request, HttpServletResponse response){
		SysConfig config=ConfigUtil.getConfig("product_upnum");
		request.setAttribute("config",config);
        return "manage/sysset/configlist";
	}
	
	/**
	 * 保存默认上架数量
	 * @return
	 */
	@RequestMapping("/saveconfig")
    public String saveconfig(HttpServletRequest request, HttpServletResponse response){
		Integer configid=this.getParaInteger("configid");
		String configkey=this.getParaString("configkey");
		String configvalue=this.getParaString("configvalue");
		String configtype=this.getParaString("configtype");
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("config_key",configkey);
		map.put("config_value",configvalue);
		map.put("config_type",configtype);
		map.put("id",configid);
		sysConfigService.updateSysConfigById(map);
		SysConfig config=ConfigUtil.getConfig("product_upnum");
		request.setAttribute("config",config);
		request.setAttribute("rs",1);
        return "manage/sysset/configlist";
	}
	
	
	/**
	 * app更新列表
	 * @return
	 */
	@RequestMapping("/versionlist")
    public String versionlist(HttpServletRequest request, HttpServletResponse response){
		
		//查询参数接收
		String rs=this.getParaString("rs");
		
		String version=this.getParaString("version");
		String os=this.getParaString("os");
		String stime=this.getParaString("stime");
		if(stime.equals("")){
			stime=DateUtil.getLastWeek();
		}
		String etime=this.getParaString("etime");
		if(etime.equals("")){
			etime=DateUtil.getToday();
		}
		
		//查询条件设置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("version",version);
		map.put("os",os);
		if(!stime.equals("")){
			map.put("stime",stime+" 00:00:00");
		}else{
			map.put("stime",stime);
		}
		if(!etime.equals("")){
			map.put("etime",etime+" 23:59:59");
		}else{
			map.put("etime",etime);
		}
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		//分页数据查询
		Integer totalRecord=sysVersionService.getVersionListCount(map);
		List<SysVersion> loglist=sysVersionService.getVersionList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(loglist);
		
		//返回页面参数
		String serverurl = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort(); 
		request.setAttribute("serverurl", serverurl);
		request.setAttribute("version", version);
		request.setAttribute("os",os);
		request.setAttribute("stime",stime);
		request.setAttribute("etime",etime);
		request.setAttribute("page",page);
		request.setAttribute("rs",rs);
        return "manage/sysset/versionlist";
	}
	
	/**
	 * 版本添加页面
	 * @return
	 */
	@RequestMapping("/toaddversion")
    public String toaddversion(HttpServletRequest request, HttpServletResponse response){
        return "manage/sysset/addversion";
	}
	
	/**
	 * 版本添加
	 * @return
	 */
	@RequestMapping("/saveversion")
    public String saveversion(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String version=this.getParaString("version");
			String description=this.getParaString("description");
			String os=this.getParaString("os");
			String type=this.getParaString("type");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile0 = multipartRequest.getFile("app_path");
			FileInfo file = uploadFile(multipartFile0,"apppath");
			String app_path=file.getFilePath();
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("version", version);
			map.put("description", description);
			map.put("os", os);
			map.put("type", type);
			map.put("app_path", app_path);
			map.put("createtime", DateUtil.getTodayTime());
			sysVersionService.sysVersionAdd(map);
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		return "manage/sysset/addversion";
	}
	
	/**
	 * 版本添加页面
	 * @return
	 */
	@RequestMapping("/toeditversion")
    public String toeditversion(HttpServletRequest request, HttpServletResponse response){
		String id=this.getParaString("id");
		SysVersion version=sysVersionService.getSysVersionById(id);
		String serverurl = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort(); 
		request.setAttribute("serverurl", serverurl);
		request.setAttribute("version",version);
        return "manage/sysset/editversion";
	}
	
	/**
	 * 版本添加
	 * @return
	 */
	@RequestMapping("/updateversion")
    public String updateversion(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String id=this.getParaString("id");
			String oldpath=this.getParaString("oldpath");
			String version=this.getParaString("version");
			String description=this.getParaString("description");
			String os=this.getParaString("os");
			String type=this.getParaString("type");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile0 = multipartRequest.getFile("app_path");
			FileInfo file = uploadFile(multipartFile0,"apppath");
			String app_path=file.getFilePath();
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			map.put("version", version);
			map.put("description", description);
			map.put("os", os);
			map.put("type", type);
			if(app_path!=null&&!app_path.equals("")){
				map.put("app_path", app_path);
			}else{
				map.put("app_path", oldpath);
			}
			sysVersionService.sysVersionUpdate(map);
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		
		request.setAttribute("rs", rs);
		return "manage/sysset/editversion";
	}
	
	/**
	 * 安装包下载
	 */
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response){
		String id=this.getParaString("id");
		SysVersion version=sysVersionService.getSysVersionById(id);
		String filepath=request.getSession().getServletContext().getRealPath("/")+version.getApp_path();
		//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("application/octet-stream");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+filepath.substring(filepath.lastIndexOf("/"),filepath.length()));
        ServletOutputStream out=null;
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
        File file = new File(filepath);
        try {
			FileInputStream inputStream = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b)) > 0){
				out.write(b, 0, len);
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 版本删除
	 * @return
	 */
	@RequestMapping("/delversion")
    public String delversion(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String currentPage = this.getParaString("currentPage");
		String id=this.getParaString("id");
		String version=this.getParaString("version");
		String stime=this.getParaString("stime");
		String etime=this.getParaString("etime");
		String os=this.getParaString("os");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			sysVersionService.sysVersionDel(id);
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		System.out.println("redirect:versionlist?currentPage=" + currentPage + "&version="+ URLEncoder.encode(version, "UTF-8") + "&os=" + os + "&rs=" + rs);
		return "redirect:versionlist?currentPage=" + currentPage + "&version="+ URLEncoder.encode(version, "UTF-8") + "&os=" + os + "&rs=" + rs +"&stime=" +stime+"&etime="+etime;
	}
	
	/**
	 * 版本删除
	 * @return
	 */
	@RequestMapping("/changestatus")
    public String changestatus(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="2";
		String currentPage = this.getParaString("currentPage");
		String id=this.getParaString("id");
		String version=this.getParaString("version");
		String stime=this.getParaString("stime");
		String etime=this.getParaString("etime");
		String os=this.getParaString("os");
		String isopen=this.getParaString("isopen");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			map.put("isopen", isopen);
			sysVersionService.sysVersionUpdateStatus(map);
			rs="3";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:versionlist?currentPage=" + currentPage + "&version="+ URLEncoder.encode(version, "UTF-8") + "&os=" + os + "&rs=" + rs +"&stime=" +stime+"&etime="+etime;
	}
	
	
	/**
	 * 消息列表
	 * @return
	 */
	@RequestMapping("/messagelist")
    public String messagelist(HttpServletRequest request, HttpServletResponse response){
		
		//查询参数接收
		String rs=this.getParaString("rs");
		
		String content=this.getParaString("content");
		String stime=this.getParaString("stime");
		if(stime.equals("")){
			stime=DateUtil.getLastWeek();
		}
		String etime=this.getParaString("etime");
		if(etime.equals("")){
			etime=DateUtil.getToday();
		}
		
		//查询条件设置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("content",content);
		if(!stime.equals("")){
			map.put("stime",stime+" 00:00:00");
		}else{
			map.put("stime",stime);
		}
		if(!etime.equals("")){
			map.put("etime",etime+" 23:59:59");
		}else{
			map.put("etime",etime);
		}
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		
		//分页数据查询
		Integer totalRecord=sysMessageService.getMessageListCount(map);
		List<SysMessage> loglist=sysMessageService.getMessageList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(loglist);
		
		//返回页面参数
		request.setAttribute("content",content);
		request.setAttribute("stime",stime);
		request.setAttribute("etime",etime);
		request.setAttribute("page",page);
		request.setAttribute("rs",rs);
        return "manage/sysset/messagelist";
	}
	
	/**
	 * 版本添加页面
	 * @return
	 */
	@RequestMapping("/toaddmessage")
    public String toaddmessage(HttpServletRequest request, HttpServletResponse response){
        return "manage/sysset/addmessage";
	}
	
	/**
	 * 消息发送
	 * @return
	 */
	@RequestMapping("/savemessage")
    public String savemessage(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String content=this.getParaString("content");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("content", content);
			map.put("createtime", DateUtil.getTodayTime());
			sysMessageService.sysMessageAdd(map);
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		return "manage/sysset/addmessage";
	}
	
	/**
	 * 消息删除
	 * @return
	 */
	@RequestMapping("/delmessage")
    public String delmessage(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String currentPage = this.getParaString("currentPage");
		String id=this.getParaString("id");
		String content=this.getParaString("content");
		String stime=this.getParaString("stime");
		String etime=this.getParaString("etime");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			sysMessageService.sysMessageDel(id);
			rs="1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:messagelist?currentPage=" + currentPage + "&content="+ URLEncoder.encode(content, "UTF-8") +"&stime=" +stime+"&etime="+etime+"&rs="+rs;
	}
	
	

	
	/**
	 * 角色列表
	 * @return
	 */
	@RequestMapping("/rolelist")
    public String rolelist(HttpServletRequest request, HttpServletResponse response){
		//查询参数接收
		String rs=this.getParaString("rs");
		String rolename=this.getParaString("rolename");
		//查询条件设置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rolename",rolename);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		//分页数据查询
		Integer totalRecord=sysRoleService.getRoleListCount(map);
		List<SysRole> rolelist=sysRoleService.getRoleList(map);
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(rolelist);
		//返回页面参数
		request.setAttribute("rolename",rolename);
		request.setAttribute("page",page);
		request.setAttribute("rs",rs);
        return "manage/sysset/rolelist";
	}
	
	/**
	 * 角色添加页面
	 * @return
	 */
	@RequestMapping("/toaddrole")
    public String toaddrole(HttpServletRequest request, HttpServletResponse response){
		List<SysMenu> menulist=sysRoleService.getMenuList("1");
		request.setAttribute("menulist",menulist);
        return "manage/sysset/addrole";
	}
	
	/**
	 * 角色添加
	 * @return
	 */
	@RequestMapping("/saverole")
    public String saverole(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String rolename=this.getParaString("rolename");
			String description=this.getParaString("description");
			String menuids=this.getParaString("menuids");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", "0");
			map.put("rolename", rolename);
			map.put("description", description);
			sysRoleService.sysRoleAdd(map);
			Integer roleid=Integer.parseInt((String) map.get("id"));
			if(roleid!=null&&roleid>0){
				Map<String,Object> mmap=new HashMap<String,Object>();
				String[] mids=menuids.split(",");
				for(int i=0;i<mids.length;i++){
					mmap.put("roleid", roleid);
					mmap.put("menuid", mids[i]);
					sysRoleService.sysRoleMenuAdd(mmap);
				}
			}
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		return "manage/sysset/addrole";
	}
	
	/**
	 * 角色修改页面
	 * @return
	 */
	@RequestMapping("/toeditrole")
    public String toeditrole(HttpServletRequest request, HttpServletResponse response){
		String id=this.getParaString("id");
		SysRole role=sysRoleService.getSysRoleById(id);
		List<SysMenu> mlist=sysRoleService.getMenuList(id);
		role.setMenulist(mlist);
		List<SysMenu> menulist=sysRoleService.getMenuList("1");
		request.setAttribute("menulist",menulist);
		request.setAttribute("role",role);
        return "manage/sysset/editrole";
	}
	
	/**
	 * 角色修改
	 * @return
	 */
	@RequestMapping("/updaterole")
    public String updaterole(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String id=this.getParaString("id");
			String rolename=this.getParaString("rolename");
			String description=this.getParaString("description");
			String menuids=this.getParaString("menuids");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			map.put("rolename", rolename);
			map.put("description", description);
			sysRoleService.sysRoleUpdate(map);
			//删除菜单信息
			sysRoleService.sysRoleMenuDel(id);
			//增加菜单信息
			Map<String,Object> mmap=new HashMap<String,Object>();
			String[] mids=menuids.split(",");
			for(int i=0;i<mids.length;i++){
				mmap.put("roleid", id);
				mmap.put("menuid", mids[i]);
				sysRoleService.sysRoleMenuAdd(mmap);
			}
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		
		request.setAttribute("rs", rs);
		return "manage/sysset/editrole";
	}
	
	/**
	 * 角色删除
	 * @return
	 */
	@RequestMapping("/delrole")
    public String delrole(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String currentPage = this.getParaString("currentPage");
		String id=this.getParaString("id");
		String rolename=this.getParaString("rolename");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			Integer roleUserCount=sysRoleService.getUserRoleCount(id);
			if(roleUserCount>0){
				rs="4";
			}else{
				sysRoleService.sysRoleDel(id);
				rs="1";
			}
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		return "redirect:rolelist?currentPage=" + currentPage + "&rolename="+ URLEncoder.encode(rolename, "UTF-8") + "&rs=" + rs ;
	}
	
	
	
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping("/userlist")
    public String userlist(HttpServletRequest request, HttpServletResponse response){
		//查询参数接收
		String rs=this.getParaString("rs");
		String username=this.getParaString("username");
		//查询条件设置
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username",username);
		map.put("pageStart",page.getPagestart());
		map.put("pageSize",page.getPageSize());
		//分页数据查询
		Integer totalRecord=sysUserMngService.getSysUserListCount(map);
		List<SysUser> loglist=sysUserMngService.getSysUserList(map);
		
		//分页信息设置
		page.setTotalRecord(totalRecord);
		page.setList(loglist);
		
		//返回页面参数
		request.setAttribute("username",username);
		request.setAttribute("page",page);
		request.setAttribute("rs",rs);
        return "manage/sysset/userlist";
	}
	
	/**
	 * 用户添加页面
	 * @return
	 */
	@RequestMapping("/toadduser")
    public String toadduser(HttpServletRequest request, HttpServletResponse response){
		List<SysRole> rolelist=sysUserMngService.getAllRoleList();
		request.setAttribute("rolelist", rolelist);
        return "manage/sysset/adduser";
	}
	
	/**
	 * 用户添加
	 * @return
	 */
	@RequestMapping("/saveuser")
    public String saveuser(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			Integer roleid=this.getParaInteger("roleid");
			String username=this.getParaString("username");
			String password=this.getParaString("password");
			String realname=this.getParaString("realname");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", "0");
			map.put("username", username);
			map.put("password", CommonUtil.md5(password));
			map.put("realname", realname);
			map.put("createtime", DateUtil.getTodayTime());
			sysUserMngService.sysUserAdd(map);
			Integer userid=Integer.parseInt((String) map.get("id"));
			if(userid!=null&&userid>0){
				Map<String,Object> rolemap=new HashMap<String,Object>();
				rolemap.put("userid", userid);
				rolemap.put("roleid", roleid);
				sysUserMngService.sysUserRoleAdd(rolemap);
			}
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		request.setAttribute("rs", rs);
		return "manage/sysset/adduser";
	}
	
	/**
	 * 用户修改页面
	 * @return
	 */
	@RequestMapping("/toedituser")
    public String toedituser(HttpServletRequest request, HttpServletResponse response){
		String id=this.getParaString("id");
		SysUser user=sysUserMngService.getSysUserById(id);
		List<SysRole> rolelist=sysUserMngService.getAllRoleList();
		request.setAttribute("rolelist", rolelist);
		request.setAttribute("user",user);
        return "manage/sysset/edituser";
	}
	
	/**
	 * 用户修改
	 * @return
	 */
	@RequestMapping("/updateuser")
    public String updateuser(HttpServletRequest request, HttpServletResponse response){
		String rs="0";
		try {
			String id=this.getParaString("id");
			Integer roleid=this.getParaInteger("roleid");
			String realname=this.getParaString("realname");
			String password=this.getParaString("password");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			map.put("realname", realname);
			map.put("password", CommonUtil.md5(password));
			map.put("roleid", roleid);
			map.put("userid", id);
			if(password.equals("")){
				sysUserMngService.sysUserUpdateNoPwd(map);
			}else{
				sysUserMngService.sysUserUpdate(map);
			}
			Integer rid=sysUserMngService.getUserRoleById(id);
			if(rid!=null&&rid>0){
				sysUserMngService.sysUserRoleUpdate(map);
			}else{
				sysUserMngService.sysUserRoleAdd(map);
			}
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		
		request.setAttribute("rs", rs);
		return "manage/sysset/edituser";
	}
	
	/**
	 * 用户删除
	 * @return
	 */
	@RequestMapping("/deluser")
    public String deluser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String rs="0";
		String currentPage = this.getParaString("currentPage");
		String id=this.getParaString("id");
		String username=this.getParaString("username");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			sysUserMngService.sysUserDel(id);
			sysUserMngService.sysUserRoleDel(Integer.parseInt(id));
			rs="1";
		} catch (Exception e) {
			rs="0";
			e.printStackTrace();
		}
		return "redirect:userlist?currentPage=" + currentPage + "&username="+ URLEncoder.encode(username, "UTF-8") + "&rs=" + rs;
	}
	
	/**
	 * 用户名校验
	 * @return
	 */
	@RequestMapping("/validUserName")
    public void validUserName(HttpServletRequest request, HttpServletResponse response){
		try{
			String testname=this.getParaString("param");
			Integer num=sysUserMngService.getIsUsedUserName(testname);
			if(num==0){
				response.getWriter().write("y");
			}else{
				response.getWriter().write("用户名已存在");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
