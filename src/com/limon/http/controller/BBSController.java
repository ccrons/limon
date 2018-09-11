package com.limon.http.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import api.ucenter.Client;
import api.ucenter.XMLHelper;

import com.limon.base.controller.BaseController;
import com.limon.util.CommonUtil;

/**
 * @author gqf
 * 
 *         http接口 2015-2-27 上午10:52:19
 */
@Controller
public class BBSController extends BaseController {
	private static final Logger log = Logger.getLogger(BBSController.class);

	@RequestMapping("/tobbs")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String password="1qaz@wsx";
		String uid=this.getParaString("uid");
		
		Client e = new Client();
		
		//登录bbs
		String result = e.uc_user_login(uid,"1qaz@wsx");
		LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		String errorInfo="";
		if(rs.size()>0){
			int $uid = Integer.parseInt(rs.get(0));
			String $username = rs.get(1);
			String $password = rs.get(2);
			String $email = rs.get(3);
			if($uid > 0) {
				Cookie auth=new Cookie("lemon_auth",e.uc_authcode(password+"\t"+uid, "ENCODE"));
				response.addHeader("P3P"," CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
				response.addCookie(auth);
				String bbsurl=CommonUtil.getConfig("BBS_URL");
				String $ucsynlogin = e.uc_user_synlogin($uid);
				String s="欢迎回来,2秒钟后自动跳转."+$ucsynlogin+"<br><meta http-equiv=\"refresh\" content=\"2; url="+bbsurl+"\"/>";		
				response.getWriter().write(s);
			} else if($uid == -1) {
				errorInfo = "用户不存在,或者被删除(bbs)";
			} else if($uid == -2) {
				errorInfo = "密码错(bbs)";
			} else {
				errorInfo = "未定义(bbs)";
			}
		}else{
			errorInfo = "未定义(bbs)";
		}  
		response.getWriter().write(errorInfo);
		return null;
	}
}
