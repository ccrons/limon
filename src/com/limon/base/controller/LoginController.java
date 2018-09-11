package com.limon.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.common.LogUtil;
import com.limon.base.model.SysUser;
import com.limon.base.service.SysUserService;
import com.limon.store.model.StoreInfo;
import com.limon.store.service.StoreInfoService;
import com.limon.util.CommonUtil;

/**
 * @author gqf
 *
 * 登录相关流程
 * 2015-2-10 上午10:32:56
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private StoreInfoService storeInfoService;
	
	@RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response){
		String error=this.getParaString("error");
		String logtype=this.getParaString("logtype");
		String message="";
		if(error.equals("1")){
			message="登录信息失效，请重新登录";
		}
		request.setAttribute("message",message);
		request.setAttribute("logtype",logtype);
        return "login";
    }
	
	@RequestMapping("loginCheck")
    public String loginCheck(HttpServletRequest request, HttpServletResponse response){
		String username=this.getParaString("username");
		String password=this.getParaString("password");
		String logtype=this.getParaString("logtype");
		Map<String,String> map=new HashMap<String,String>();
		map.put("username", username);
		map.put("password", CommonUtil.md5(password));
		
		SysUser user=null;
		if(logtype.equals("0")){
			StoreInfo store=storeInfoService.getStoreByUserNameAndPassword(map);
			if(store!=null){
				user=new SysUser();
				user.setId(store.getId());
				user.setRoleid(store.getRoleid());
				user.setRolename(store.getRolename());
				user.setUsername(store.getUsername());
			}
		}else if(logtype.equals("1")){
			user=sysUserService.getUserByUserNameAndPassword(map);
		}
		
		if(user==null){
			//返回错误提示信息
			request.setAttribute("message","用户名或密码错误");
			request.setAttribute("logtype",logtype);
			//返回登录页
			return "login";
		}else{		
			//登录信息存入session
			request.getSession().setAttribute("loginUser", user);
			request.getSession().setAttribute("logtype", logtype);
			//记录登录日志
			String logcontent="";
			Integer ltype=0;
			if(logtype.equals("0")){
				logcontent="便利店 "+user.getUsername() +" 登录";
				ltype=4;
			}else{
				logcontent="管理员 "+user.getUsername() +" 登录";
				ltype=3;
			}
			if(!logcontent.equals("")){
				LogUtil.logLogin(logcontent,user.getUsername(),CommonUtil.getIpAddr(request),ltype);
			}
			
			//跳转到首页
			return "redirect:/main";
		}
    }
	
	@RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
		//退出前取出当前登录用户名
		String logtype=(String) request.getSession().getAttribute("logtype");
		//删除登录信息
		request.getSession().removeAttribute("loginUser");
		request.getSession().removeAttribute("logtype");
		//退出消息
		String message="您已安全退出";
		
		request.setAttribute("message",message);
		request.setAttribute("logtype",logtype);
        return "login";
    }
}
