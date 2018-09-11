package com.limon.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.limon.base.controller.BaseController;
import com.limon.util.CommonUtil;
import com.limon.util.Signature;
import com.limon.util.TicketUtil;
import com.limon.wx.model.UserAccessToken;
import com.limon.wx.model.UserInfo;
import com.limon.wx.model.WXUserInfo;
import com.limon.wx.service.UserInfoService;

/**
 * @author gqf
 *
 * 用户登录流程
 * 2015-2-10 上午10:32:56
 */
@Controller
@RequestMapping("/wx")
public class WxMainController extends BaseController{	
	@Autowired
	private UserInfoService userinfoService;
	
	@RequestMapping("/wxindex")
	public void index(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String scope="snsapi_userinfo";//snsapi_base
			//oauth2.0授权引导页
			String state=new Date().getTime()+"";
			String codeurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonUtil.getConfig("appid")+"&redirect_uri="+URLEncoder.encode(CommonUtil.getConfig("backurl"),"utf-8")+"&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
			response.sendRedirect(codeurl);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/callback")
	public String callback(HttpServletRequest request, HttpServletResponse response){
		String code=this.getParaString("code");
		try{
			String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+CommonUtil.getConfig("appid")+"&secret="+CommonUtil.getConfig("secret")+"&code="+code+"&grant_type=authorization_code";
			HttpGet httpGet = new HttpGet(url);
			HttpClient httpclient = new DefaultHttpClient(); 
			HttpResponse responseHttp = httpclient.execute(httpGet);
		    HttpEntity entity = responseHttp.getEntity();            
		    String rsp = EntityUtils.toString(entity,"utf-8");
		    UserAccessToken t=JSON.parseObject(rsp,UserAccessToken.class);
		    
			//获取微信用户基本信息	
			WXUserInfo wxinfo = TicketUtil.getWXUserInfo(TicketUtil.getAccessToken(),t.getOpenid());
			
		    //判断当前微信用户是否已经注册
		    String username = t.getOpenid()+"@weixin";
		    int userid = 0;
		    
		    Map<String,Object> map = new HashMap<String,Object>();
		    map.put("username", username);

		    UserInfo user=userinfoService.getUserInfoByUserName(map);
		    if(user != null){
		    	//获取到登录信息并登录
		    	userid=user.getId();
			    user.setUserOpenid(t.getOpenid());
			    user.setAccesstoken(t.getAccess_token());
			    map.put("userid", userid);
			    map.put("access_token", t.getAccess_token());
	        	map.put("refresh_token",t.getRefresh_token());
	        	map.put("expires_in", t.getExpires_in());
	        	map.put("generated_time", new Date());
	        	map.put("headimg", wxinfo.getHeadimgurl());//用户头像
	        	map.put("nickname", wxinfo.getNickname());//用户昵称
	        	//更新用户昵称、头像
	        	userinfoService.updateUserInfo(map);
	        	
	        	Integer ret=userinfoService.findWXToken(map);
	        	if(ret >0){
	        		//更新用户token
	        		userinfoService.updateWXToken(map);
	        	}else{
	        		//添加用户token
	        		userinfoService.insertWXToken(map);
	        	}
		    }else{
		    	//新增用户
	        	map.put("password","21218cca77804d2ba1922c33e0151105");
	        	map.put("mobile","");
	        	map.put("headimg", wxinfo.getHeadimgurl());//用户头像
	        	map.put("nickname",wxinfo.getNickname());//用户昵称

	        	//增加微信用户
	        	userinfoService.insertUserInfo(map);
	        	
	        	user = userinfoService.getUserInfoByUserName(map);
	        	userid=user.getId(); 
	        	map.put("userid",userid);
	        	map.put("access_token", t.getAccess_token());
	        	map.put("refresh_token",t.getRefresh_token());
	        	map.put("expires_in", t.getExpires_in());
	        	//增加用户token
	        	userinfoService.insertWXToken(map);
		    }
		    user.setUserOpenid(t.getOpenid());
		    user.setNickname(wxinfo.getNickname());
		    request.getSession().setAttribute("wxloginUser",user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//登录后跳转到首页
		return "redirect:index";
	}
	
	/**
	 * 微信服务器验证
	 */
	@RequestMapping("/check")
	public void urlRedirect(){
		// 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out;
		try {
			out = response.getWriter();
			 // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
	        if (Signature.checkSignature(signature, timestamp, nonce)) {
	            out.print(echostr);
	            System.out.println("微信服务验证成功！");
	        }
	        out.close();
	        out = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
