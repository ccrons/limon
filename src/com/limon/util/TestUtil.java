package com.limon.util;

import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.TagListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.report.MessagesResult;
import cn.jpush.api.report.ReceivedsResult;

public class TestUtil {
	private static final Logger log = Logger.getLogger(TestUtil.class);
	private static String store_appKey=CommonUtil.getConfig("jpush_store_appKey");
	private static String store_masterSecret=CommonUtil.getConfig("jpush_store_masterSecret");
	private static String appuser_appKey=CommonUtil.getConfig("jpush_appuser_appKey");
	private static String appuser_masterSecret=CommonUtil.getConfig("jpush_appuser_masterSecret");
	private static JPushClient jpushAppClient = new JPushClient(appuser_masterSecret, appuser_appKey);
	private static JPushClient jpushStoreClient = new JPushClient(store_masterSecret, store_appKey);
	public static void main(String[] args) {
		/*
		String[] s=new String[4567];
		for(int i=0;i<4567;i++){
			s[i]=(i+1)+"";
		}
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
				System.out.println(subs.length+"=========");
			}
		}else{
			//小于1000直接push
			System.out.println(s.length);
		}
		*/
		JPushUtil.pushToAppUsers("app22", "宫庆峰测试alias");
		//buildPushObject_all_all_alert();
	}
	
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("宫庆峰测试111");
    }
	
}	
