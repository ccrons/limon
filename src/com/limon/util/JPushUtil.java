package com.limon.util;

import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {
	private static final Logger log = Logger.getLogger(JPushUtil.class);
	private static String store_appKey=CommonUtil.getConfig("jpush_store_appKey");
	private static String store_masterSecret=CommonUtil.getConfig("jpush_store_masterSecret");
	private static String appuser_appKey=CommonUtil.getConfig("jpush_appuser_appKey");
	private static String appuser_masterSecret=CommonUtil.getConfig("jpush_appuser_masterSecret");
	private static JPushClient jpushAppClient = new JPushClient(appuser_masterSecret, appuser_appKey);
	private static JPushClient jpushStoreClient = new JPushClient(store_masterSecret, store_appKey);
	
	/**
	 * 群发文字消息推送用户（可按照社区推送）
	 * @param regids
	 */
	public static String pushToAppUserTag(String tags,String message){
		String rs="";
		try{
			String[] ts=tags.split(",");
			//定义Audience
			Audience audience=Audience.tag(ts);
			//定义PushPayload.Builder
			PushPayload.Builder builder=PushPayload.newBuilder();
			builder.setAudience(audience);
			//定义Platform
			Platform platform=Platform.all();
			builder.setPlatform(platform);
			//定义Options
			Options options=Options.newBuilder().build();
			options.setApnsProduction(true);
			builder.setOptions(options);
			//定义Notification
			Notification notify=Notification.alert(message);
			builder.setNotification(notify);
			//定义PushPayload
			PushPayload pushPayload=builder.build();
			log.info(pushPayload.toString());
			PushResult result=jpushAppClient.sendPush(pushPayload);
			log.info("Got pushToAppUsers result - " + result);
			rs="success";
		 }catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	     }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	     }
	     return rs;
	}
	
	/**
	 * 群发文字消息推送用户（全体推送）
	 * @param regids
	 */
	public static String pushToAppUserAll(String message){
		String rs="";
		try{
			PushPayload pushPayload=PushPayload.alertAll(message);
			log.info(pushPayload.toString());
			PushResult result=jpushAppClient.sendPush(pushPayload);
			log.info("Got pushToAppUsers result - " + result);
			rs="success";
		 }catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	     }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	     }
	     return rs;
	}
	
	/**
	 * 群发文字消息推送用户
	 * @param regids
	 */
	public static String pushToAppUsers(String regids,String message){
		String rs="";
		try{
			//定义Audience
			Audience audience=null;
			String[] s=regids.split(",");
			if(s.length>1000){
				//大于1000拆分数组
				for(int i=0;i<(s.length/1000)+1;i++){
					//定义子数组
					String[] subs=null;
					if(i==(s.length/1000)){
						//最后不足1000的部分
						subs=new String[s.length%1000];
					}else{
						//整除1000的部分
						subs=new String[1000];
					}
					//子数组赋值
					for(int j=0;j<subs.length;j++){
						subs[j]=s[i];
					}
					//push子数组内的用户
					audience=Audience.alias(subs);
				}
			}else{
				//小于1000直接push
				audience=Audience.alias(s);
			}
			//定义PushPayload.Builder
			PushPayload.Builder builder=PushPayload.newBuilder();
			builder.setAudience(audience);
			//定义Platform
			Platform platform=Platform.all();
			builder.setPlatform(platform);
			//定义Options
			Options options=Options.newBuilder().build();
			options.setApnsProduction(true);
			builder.setOptions(options);
			//定义Notification
			Notification notify=Notification.alert(message);
			builder.setNotification(notify);
			//定义PushPayload
			PushPayload pushPayload=builder.build();
			log.info(pushPayload.toString());
			PushResult result=jpushAppClient.sendPush(pushPayload);
			log.info("Got pushToAppUsers result - " + result);
			rs="success";
		 }catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	     }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	     }
	     return rs;
	}
	
	/**
	 * 商家取消接单，推送通知用户
	 * @param regid
	 */
	public static String pushToAppUserCancelOrder(String regid,String message){
		String rs="";
		try{
			//定义Audience
			Audience audience=Audience.alias(regid);
			//定义PushPayload.Builder
			PushPayload.Builder builder=PushPayload.newBuilder();
			builder.setAudience(audience);
			//定义Platform
			Platform platform=Platform.all();
			builder.setPlatform(platform);
			//定义Options
			Options options=Options.newBuilder().build();
			options.setApnsProduction(true);
			builder.setOptions(options);
			//定义Notification
			Notification notify=Notification.alert(message);
			builder.setNotification(notify);
			//定义PushPayload
			PushPayload pushPayload=builder.build();
			log.info(pushPayload.toString());
			PushResult result=jpushAppClient.sendPush(pushPayload);
			log.info("Got pushToAppUserCancelOrder result - " + result);
			rs="success";
		}catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	    }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	    }
	    return rs;
	}
	
	/**
	 * 商家接收订单，推送通知用户
	 * @param regid
	 */
	public static String pushToAppUserAcceptOrder(String regid,String message){
		String rs="";
		try{
			//定义Audience
			Audience audience=Audience.alias(regid);
			//定义PushPayload.Builder
			PushPayload.Builder builder=PushPayload.newBuilder();
			builder.setAudience(audience);
			//定义Platform
			Platform platform=Platform.all();
			builder.setPlatform(platform);
			//定义Options
			Options options=Options.newBuilder().build();
			options.setApnsProduction(true);
			builder.setOptions(options);
			//定义Notification
			Notification notify=Notification.alert(message);
			builder.setNotification(notify);
			//定义PushPayload
			PushPayload pushPayload=builder.build();
			log.info(pushPayload.toString());
			PushResult result=jpushAppClient.sendPush(pushPayload);
			log.info("Got pushToAppUserAcceptOrder result - " + result);
			rs="success";
		}catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	    }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	    }
	    return rs;
	}
	
	/**
	 * 群发消息推送商家
	 * @param regids
	 */
	public static String pushToStore(String regids,String message){
		String rs="";
		try{
			//定义Audience
			Audience audience=null;
			String[] s=regids.split(",");
			if(s.length>1000){
				//大于1000拆分数组
				for(int i=0;i<(s.length/1000)+1;i++){
					//定义子数组
					String[] subs=null;
					if(i==(s.length/1000)){
						//最后不足1000的部分
						subs=new String[s.length%1000];
					}else{
						//整除1000的部分
						subs=new String[1000];
					}
					//子数组赋值
					for(int j=0;j<subs.length;j++){
						subs[j]=s[i];
					}
					//push子数组内的用户
					audience=Audience.alias(subs);
				}
			}else{
				//小于1000直接push
				audience=Audience.alias(s);
			}
			//定义PushPayload.Builder
			PushPayload.Builder builder=PushPayload.newBuilder();
			builder.setAudience(audience);
			//定义Platform
			Platform platform=Platform.all();
			builder.setPlatform(platform);
			//定义Options
			Options options=Options.newBuilder().build();
			options.setApnsProduction(true);
			builder.setOptions(options);
			//定义Notification
			Notification notify=Notification.alert(message);
			builder.setNotification(notify);
			//定义PushPayload
			PushPayload pushPayload=builder.build();
			log.info(pushPayload.toString());
			PushResult result=jpushStoreClient.sendPush(pushPayload);
			log.info("Got pushToStore result - " + result);
			rs="success";
		}catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	    }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	    }
	    return rs;
	}
	
	/**
	 * 提醒商家新订单推送
	 * @param regid
	 */
	public static String pushToStoreNewOrder(String regid,String message){
		String rs="";
		try{
			//定义Audience
			Audience audience=Audience.alias(regid);
			//定义PushPayload.Builder
			PushPayload.Builder builder=PushPayload.newBuilder();
			builder.setAudience(audience);
			//定义Platform
			Platform platform=Platform.all();
			builder.setPlatform(platform);
			//定义Options
			Options options=Options.newBuilder().build();
			options.setApnsProduction(true);
			builder.setOptions(options);
			//定义Notification
			Notification notify=Notification.alert(message);
			builder.setNotification(notify);
			//定义PushPayload
			PushPayload pushPayload=builder.build();
			log.info(pushPayload.toString());
			PushResult result=jpushStoreClient.sendPush(pushPayload);
			log.info("Got pushToStoreNewOrder result - " + result);
			rs="success";
		}catch (APIConnectionException e) {
			log.error("Connection error, should retry later", e);
			rs="fail";
	    }catch (APIRequestException e) {
	    	log.error("Should review the error, and fix the request", e);
	        log.info("HTTP Status: " + e.getStatus());
	        log.info("Error Code: " + e.getErrorCode());
	        log.info("Error Message: " + e.getErrorMessage());
	        rs="fail";
	    }
	    return rs;
	}
	
}	
