package com.limon.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.limon.wx.model.AccessToken;
import com.limon.wx.model.PaySingInfo;
import com.limon.wx.model.SignInfo;
import com.limon.wx.model.Ticket;
import com.limon.wx.model.WXUserInfo;
import com.limon.wx.model.WxConfig;
import com.limon.wx.service.WxConfigService;

@Component
public class TicketUtil {
	 private static SignInfo si=null;
	 public static String appid=CommonUtil.getConfig("appid");
	 public static String secret=CommonUtil.getConfig("secret");
	 public static String backurl=CommonUtil.getConfig("backurl");
	 public static String mch_id = CommonUtil.getConfig("mch_id");
	 public static String SIGN_KEY = CommonUtil.getConfig("SIGN_KEY");// 签名密钥
	 @Autowired
	 private WxConfigService wxConfigService;
	 private static TicketUtil ticketUtil;  
	 
	 /**
	  * 公众号获取签名
	  * @return
	  */
	 public static SignInfo getSign(String url){
		 WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		 WxConfigService wxConfigService = (WxConfigService)wac.getBean("wxConfigService");
		 String accesstoken = "";
		 boolean first=false;
		 if(si==null){
			 si=new SignInfo();
			 first=true;
		 }
		 //获取数据库中的token 
		 WxConfig config =  wxConfigService.getAccess_token();
		 
		 long ts=(new Date().getTime()/1000)-config.getCreatetime().getTime()/1000;// si.getTimestamp();
		 if(ts>7200 || first){
			 accesstoken=getAccessToken();
			 wxConfigService.updateAccess_token(accesstoken);
			 //System.out.println("wx微信接口获取的accesstoken="+accesstoken);
		 }else{
			 accesstoken= config.getCvalue();
			 //System.out.println("mysql数据库中的accesstoken="+accesstoken);
		 }
		 
		 //取ticket
		 String ticket=getTicket(accesstoken);
		 if(ticket == null){
			 accesstoken=getAccessToken();
			 wxConfigService.updateAccess_token(accesstoken);
			 ticket=getTicket(accesstoken);
		 }
		 
		 Map<String, String> ret = sign(ticket, url);
		 
		 //System.out.println("公众号获取签名参数=====================url="+url);
		 //System.out.println("jsapi_ticket============="+ret.get("jsapi_ticket"));
		 //System.out.println("nonceStr=============="+ret.get("nonceStr"));
		 //System.out.println("timestamp==============="+ret.get("timestamp"));
		 //System.out.println("signature==============="+ret.get("signature"));
		
		 si.setJsapi_ticket(ret.get("jsapi_ticket"));
		 si.setNonceStr(ret.get("nonceStr"));
		 si.setSignature(ret.get("signature"));
		 si.setTimestamp(Long.parseLong(ret.get("timestamp")));
		 si.setUrl(ret.get("url"));
		 si.setAppid(appid);
		 return si;
	 }
	 
 
	 
	 /**
	  * 获取accesstoken
	  * @return
	  */
	 public static String getAccessToken(){
		String token="";
	 	try{
			String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
			HttpGet httpGet = new HttpGet(url);
			HttpClient httpclient = new DefaultHttpClient(); 
			HttpResponse response = httpclient.execute(httpGet);
		    HttpEntity entity = response.getEntity();            
		    String rsp = EntityUtils.toString(entity,"utf-8");
		    System.out.println("获取公用access_token:"+rsp);
		    AccessToken t=JSON.parseObject(rsp,AccessToken.class);
		    token=t.getAccess_token();
		}catch(Exception e){
			e.printStackTrace();
		}
		return token;
	 }
	 
	
	 
	 /**
	  * 获取ticket
	  * @return
	  */
	 public static String getTicket(String accesstoken){
			String ticket="";
		 	try{
				String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accesstoken+"&type=jsapi";
				HttpGet httpGet = new HttpGet(url);
				HttpClient httpclient = new DefaultHttpClient(); 
				HttpResponse response = httpclient.execute(httpGet);
			    HttpEntity entity = response.getEntity();            
			    String rsp = EntityUtils.toString(entity,"utf-8");
			    Ticket t=JSON.parseObject(rsp,Ticket.class);
			    ticket=t.getTicket();
			}catch(Exception e){
				e.printStackTrace();
			}
			return ticket;
	 }
	 
	 /**
	  * 获取用户基本信息
	  * @return
	  */
	 public static WXUserInfo getWXUserInfo(String accesstoken,String openid){
		 WXUserInfo info=new WXUserInfo();
		 	try{
				String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accesstoken+"&openid="+openid+"&lang=zh_CN";
				HttpGet httpGet = new HttpGet(url);
				HttpClient httpclient = new DefaultHttpClient(); 
				HttpResponse response = httpclient.execute(httpGet);
			    HttpEntity entity = response.getEntity();            
			    String rsp = EntityUtils.toString(entity,"utf-8");
			    System.out.println(rsp);
			    info=JSON.parseObject(rsp,WXUserInfo.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			return info;
	 }
	 
	 /**
	  * 获取 授权用户信息
	  * @return
	  */
	 public static WXUserInfo getSNSUserInfo(String accesstoken,String openid){
		 WXUserInfo info=new WXUserInfo();
		 	try{
				String url="https://api.weixin.qq.com/sns/userinfo?access_token="+accesstoken+"&openid="+openid+"&lang=zh_CN";
				HttpGet httpGet = new HttpGet(url);
				HttpClient httpclient = new DefaultHttpClient(); 
				HttpResponse response = httpclient.execute(httpGet);
			    HttpEntity entity = response.getEntity();            
			    String rsp = EntityUtils.toString(entity,"utf-8");
			    info=JSON.parseObject(rsp,WXUserInfo.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			return info;
	 }
	 
	 public static void main(String[] args) {
		String msg = "{\"action_name\": \"QR_LIMIT_STR_SCENE\",\"action_info\": {\"scene\": {\"scene_str\": \"7\"}   }	}"; 
		 
		System.out.println(getAccessToken());
		//getWXQRcode(getAccessToken(),msg);
		
		String nonce_str =UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(nonce_str+"=="+nonce_str.length());
	}
	
	 /**
	  * 生成微信签名
	  * @param jsapi_ticket
	  * @param url
	  * @return
	  */
	 public static Map<String, String> sign(String jsapi_ticket, String url) {
			Map<String, String> ret = new HashMap<String, String>();
			String nonce_str = create_nonce_str();
			String timestamp = create_timestamp();
			String string1;
			String signature = "";

			//注意这里参数名必须全部小写，且必须有序
			string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
					+ "&timestamp=" + timestamp + "&url=" + url;
			//System.out.println(string1);

			try {
				MessageDigest crypt = MessageDigest.getInstance("SHA-1");
				crypt.reset();
				crypt.update(string1.getBytes("UTF-8"));
				signature = byteToHex(crypt.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			ret.put("url", url);
			ret.put("jsapi_ticket", jsapi_ticket);
			ret.put("nonceStr", nonce_str);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);

			return ret;
	}

	 
	 /**
	  * 生成微信支付签名
	  * @param jsapi_ticket
	  * @param url
	  * @return
	  */
	 public static PaySingInfo paySign(String packageStr) {
			System.out.println("packageStr="+packageStr);
			
			
			String nonce_str = create_nonce_str();// 随机字符串
			String timestamp = create_timestamp();// 时间戳
			
			packageStr ="prepay_id="+packageStr;
			// 构造签名
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("appId", appid);
			resMap.put("nonceStr", nonce_str);
			resMap.put("package", packageStr);
			resMap.put("signType", "MD5");
			resMap.put("timeStamp", timestamp);
			
			//签名
			String sign = Signature.getSign(resMap);
			
			PaySingInfo singInfo = new PaySingInfo();
			singInfo.setAppId(appid);
			singInfo.setNonceStr(nonce_str);
			singInfo.setPackageStr(packageStr);
			singInfo.setSignType("MD5");
			singInfo.setTimestamp(timestamp);
			singInfo.setPaySign(sign);
			return singInfo;
	}
	 
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
		
	/**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(resultString.getBytes("UTF-8"));
            resultString = byteArrayToHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }
    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};
	
    
    @PostConstruct  
	public void init() {  
		ticketUtil = this;  
		ticketUtil.wxConfigService = ticketUtil.wxConfigService;  
	} 
    
    /** 
     * 发送HttpPost请求 
     * @param strURL 
     * @param params   json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String postJson(String strURL, String params) {  
    	//System.out.println(strURL);  
        //System.out.println(params);  
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = (int) connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                //System.out.println(result);  
                return result;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }  
}
