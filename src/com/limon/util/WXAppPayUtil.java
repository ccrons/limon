package com.limon.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.limon.wx.model.OrderInfo;
import com.limon.wx.model.PayInfo;
import com.limon.wx.model.PayResult;


/**
 * 微信app支付工具类
 */
public class WXAppPayUtil {
	/** 签名. */
	//public static String trade_type = "JSAPI";
	public static String appid =  CommonUtil.getConfig("appid");
	public static String mch_id = CommonUtil.getConfig("mch_id");
	public static String device_info = "WEB";
	public static String notify_url =   CommonUtil.getConfig("notify_url");
	public static String SIGN_KEY = CommonUtil.getConfig("SIGN_KEY");// 签名密钥
	
 
	 /**
	  * 获取统一下单
	  * @return
	  */
	 public static String getPayInfo(String openid,String body,String out_trade_no,Integer total_fee,String trade_type,String clientip){
			System.out.println("******获取统一下单**");
			String resData = null;
			try {
				String nonce_str =UUID.randomUUID().toString().substring(0, 31);
				// 构造需要传输的对象
				Map map = new HashMap();
				map.put("appid", appid);
				map.put("mch_id", mch_id);
				map.put("nonce_str", nonce_str);
				map.put("body", body);
				map.put("out_trade_no", out_trade_no);
				map.put("total_fee", total_fee);
				map.put("spbill_create_ip", clientip);
				map.put("notify_url", notify_url);
				map.put("trade_type", trade_type);
				map.put("openid", openid);
				
				System.out.println("map="+map.toString());
				
				//签名
				String sign = Signature.getSign(map);
				
				PayInfo payinfo =  new PayInfo("");
				payinfo.setAppid(appid);
				payinfo.setMch_id(mch_id);
				payinfo.setNonce_str(nonce_str);
				payinfo.setBody(body);
				payinfo.setOut_trade_no(out_trade_no);
				payinfo.setTotal_fee(total_fee);
				payinfo.setSpbill_create_ip(clientip);
				payinfo.setNotify_url(notify_url);
				payinfo.setTrade_type(trade_type);
				payinfo.setOpenid(openid);
				payinfo.setSign(sign);
				
				// 对象需要转换成xml
				String postDataXML = XMLParser.toXML(payinfo); //new XmlCoder().encode(reqData);
				
				//统一下单
				//System.out.println("post数据=="+postDataXML);
				
				// 调用下单接口返回数据
				String url="https://api.mch.weixin.qq.com/pay/unifiedorder";
				HttpPost httpPost = new HttpPost(url);
				StringEntity stringEntity = new StringEntity(postDataXML, "UTF-8");
				stringEntity.setContentType("text/xml");
				stringEntity.setContentEncoding("UTF-8");
				httpPost.setEntity(stringEntity);
				HttpResponse response =  new DefaultHttpClient().execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();
				//System.out.println("statusCode="+statusCode);
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
		        
				System.out.println("==统一下单接口=返回结果：appid="+appid+"\n"+result+"");
				resData = result;
				return resData;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return resData;
	}

	 /**
	  * 查询订单
	  * @return
	  */
	 public static PayResult queryOrder(String out_trade_no){
			System.out.println("******查询订单**");
			PayResult pr=null;
			try {
				String nonce_str =UUID.randomUUID().toString().substring(0, 31);
				// 构造需要传输的对象
				Map map = new HashMap();
				map.put("appid", appid);
				map.put("mch_id", mch_id);
				map.put("nonce_str", nonce_str);
				map.put("out_trade_no", out_trade_no);
				
				System.out.println("map="+map.toString());
				
				//签名
				String sign = Signature.getSign(map);
				
				OrderInfo payinfo =  new OrderInfo("");
				payinfo.setAppid(appid);
				payinfo.setMch_id(mch_id);
				payinfo.setNonce_str(nonce_str);
				payinfo.setOut_trade_no(out_trade_no);
				payinfo.setSign(sign);
				
				// 对象需要转换成xml
				String postDataXML = XMLParser.toXML(payinfo); //new XmlCoder().encode(reqData);
				
				//查询订单
				System.out.println("post数据=="+postDataXML);
				
				// 调用下单接口返回数据
				String url="https://api.mch.weixin.qq.com/pay/orderquery";
				HttpPost httpPost = new HttpPost(url);
				StringEntity stringEntity = new StringEntity(postDataXML, "UTF-8");
				stringEntity.setContentType("text/xml");
				stringEntity.setContentEncoding("UTF-8");
				httpPost.setEntity(stringEntity);
				HttpResponse response =  new DefaultHttpClient().execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode="+statusCode);
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
		        
				System.out.println("==查询订单接口=返回结果：appid="+appid+"\n"+result+"");
				Map resmap = XMLParser.getMapFromXML(result);
			    String result_code	=	(String) resmap.get("result_code");  
				String transaction_id=(String) resmap.get("transaction_id");  
				String time_end=(String) resmap.get("time_end");
				String total_fee=(String) resmap.get("total_fee");
				out_trade_no=(String) resmap.get("out_trade_no");
				String trade_state=(String) resmap.get("trade_state");
				pr=new PayResult();
				pr.setOut_trade_no(out_trade_no);
				pr.setResult_code(result_code);
				pr.setTime_end(time_end);
				pr.setTotal_fee(total_fee);
				pr.setTransaction_id(transaction_id);
				pr.setTrade_state(trade_state);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return pr;
	}
}
