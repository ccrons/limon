package com.limon.http.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.AddAddressReq;
import com.limon.http.coder.AddCarReq;
import com.limon.http.coder.AppUpdateCheckReq;
import com.limon.http.coder.DelAddressReq;
import com.limon.http.coder.DelCarAllReq;
import com.limon.http.coder.DelCarReq;
import com.limon.http.coder.DelFavGoodsReq;
import com.limon.http.coder.DelOrderReq;
import com.limon.http.coder.FavGoodsReq;
import com.limon.http.coder.FeedBackReq;
import com.limon.http.coder.GetAddressReq;
import com.limon.http.coder.GetAllAddressReq;
import com.limon.http.coder.GetAllGoodsByShopReq;
import com.limon.http.coder.GetCarGoodsReq;
import com.limon.http.coder.GetCityReq;
import com.limon.http.coder.GetDistrictReq;
import com.limon.http.coder.GetFavGoodsReq;
import com.limon.http.coder.GetGoodsByTypeReq;
import com.limon.http.coder.GetGoodsFTypeReq;
import com.limon.http.coder.GetGoodsInfoReq;
import com.limon.http.coder.GetGoodsTypeReq;
import com.limon.http.coder.GetHistoryOrderReq;
import com.limon.http.coder.GetHpInfoReq;
import com.limon.http.coder.GetMessageReq;
import com.limon.http.coder.GetMyGoodsByShopReq;
import com.limon.http.coder.GetNearShopReq;
import com.limon.http.coder.GetPerInfoReq;
import com.limon.http.coder.GetProvinceReq;
import com.limon.http.coder.GetSendedOrderReq;
import com.limon.http.coder.GetSendingOrderReq;
import com.limon.http.coder.GetShopAdReq;
import com.limon.http.coder.GetShopAddressReq;
import com.limon.http.coder.GetShopGoodsBuyNumReq;
import com.limon.http.coder.GetShopGoodsInfoReq;
import com.limon.http.coder.GetShopInfoReq;
import com.limon.http.coder.GetShopReq;
import com.limon.http.coder.GetTotalOrderReq;
import com.limon.http.coder.GetUserOrderNumReq;
import com.limon.http.coder.GetWPayOrderReq;
import com.limon.http.coder.GetWReceiveOrderReq;
import com.limon.http.coder.GetWSendOrderReq;
import com.limon.http.coder.HttpContent;
import com.limon.http.coder.LoginReq;
import com.limon.http.coder.MessageType;
import com.limon.http.coder.NmSelfShopReq;
import com.limon.http.coder.PayOrderReq;
import com.limon.http.coder.RegisterReq;
import com.limon.http.coder.RetrievePwdReq;
import com.limon.http.coder.SearchGoodsReq;
import com.limon.http.coder.SearchShopReq;
import com.limon.http.coder.SendCheckCodeReq;
import com.limon.http.coder.SetHeadImgReq;
import com.limon.http.coder.SetPerInfoReq;
import com.limon.http.coder.SetShopAddressReq;
import com.limon.http.coder.ShopGoodsDownReq;
import com.limon.http.coder.ShopGoodsUpReq;
import com.limon.http.coder.ShopGoodsUpdateReq;
import com.limon.http.coder.ShopLoginReq;
import com.limon.http.coder.ShopUpdateOrderReq;
import com.limon.http.coder.ShopUpdatePwdReq;
import com.limon.http.coder.SubmitOrderReq;
import com.limon.http.coder.UpdateAddressReq;
import com.limon.http.coder.UpdateCarReq;
import com.limon.http.coder.UpdateOrderReq;
import com.limon.http.coder.UpdatePwdReq;
import com.limon.http.model.OrderProductInfo;
import com.limon.http.model.OrderProductList;
import com.limon.http.util.ByteUtil;
import com.limon.http.util.HttpDateUtil;
import com.limon.http.xml.XmlCoder;
import com.limon.util.Des3;
import com.limon.util.JSONUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;
/**
 * 
 * @author gqf
 *
 */
public class Test
{
    public static XmlCoder coder = new XmlCoder();
    private static final Logger log = Logger.getLogger(Test.class);
    //public static String posturl = "http://localhost:8080/limon/HttpService";
    //public static String posturl = "http://123.57.149.210:8088/limon/HttpService";
    public static String posturl = "http://101.200.1.181:80/HttpService";
    
    /**
     * 接口测试
     */
    public static void main(String[] args) throws Exception {
    	//Test.SendCheckCode();
        //Test.LoginReq();
        //Test.GetNearShopReq();
    	//Test.SearchShopReq();
    	//Test.GetGoodsTypeReq();
    	//Test.GetGoodsFTypeReq();
    	//Test.GetGoodsByTypeReq();
    	//Test.GetGoodsInfoReq();
    	//Test.AddCar();
    	//Test.UpdateCar();
    	//Test.DelCar();
    	//Test.GetCarGoods();
    	//Test.Register();
    	//Test.AppUpdateCheck();
    	//Test.UpdatePwd();
    	//Test.AddAddress();
    	//Test.UpdateAddress();
    	//Test.DelAddress();
    	//Test.GetAllAddress();
    	//Test.GetAddress();
    	//Test.SubmitOrder();
    	//Test.UpdateOrder();
    	//Test.DelOrder();
    	//Test.PayOrder();
    	//Test.NmSelfShop();
    	//Test.GetWPayOrder();
    	//Test.GetWReceiveOrder();
    	//Test.GetHistoryOrder();
    	//Test.FavGoods();
    	//Test.DelFavGoods();
    	//Test.GetFavGoods();
    	//Test.GetMessage();
    	//Test.FeedBack();
    	//Test.SearchGoods();
    	
    	//Test.RetrievePwd();
    	//Test.setShopAddress();
    	//Test.getShopAddress();
    	//Test.getShopInfo();
    	//Test.getShopAd();
    	//Test.shopLogin();
    	//Test.shopUpdatePwd();
    	Test.getAllGoodsByShop();
    	//Test.getMyGoodsByShop();
    	//Test.shopGoodsUp();
    	//Test.shopGoodsDown();
    	//Test.shopGoodsUpdate();
    	//Test.getWSendOrder();
    	//Test.getSendingOrder();
    	//Test.getSendedOrder();
    	//Test.shopUpdateOrder();
    	//Test.getTotalOrder();
    	//Test.getShopGoods();
    	//Test.GetProvince();
    	//Test.GetCity();
    	//Test.GetDistrict();
    	//Test.GetShop();
    	//Test.SetPerInfo();
    	//Test.GetPerInfo();
    	//Test.SetHeadImg();
    	
    	//Test.DelCarAll();
    	//Test.GetHpInfo();
    	//Test.GetUserOrderNum();
    	//Test.GetShopGoodsBuyNum();
    }	
    
    /**
     * 登录接口
     */
    public static void LoginReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            LoginReq l=new LoginReq();
            l.setUsername("15545587532");
            l.setCheckCode("123456");
            //请求类型
            req.setMessageType(MessageType.LoginReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取附近店铺接口
     */
    public static void GetNearShopReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetNearShopReq l=new GetNearShopReq();
            //l.setUid("1");
            l.setDistance("1000");
            l.setLat("126.64852");
            l.setLng("45.768272");
            //请求类型
            req.setMessageType(MessageType.GetNearShopReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 根据店铺获取产品类型
     */
    public static void GetGoodsTypeReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetGoodsTypeReq l=new GetGoodsTypeReq();
            //l.setUid("1");
            l.setSid("1");
            l.setFid("81");
            //请求类型
            req.setMessageType(MessageType.GetGoodsTypeReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 根据店铺获取产品类型一级
     */
    public static void GetGoodsFTypeReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetGoodsFTypeReq l=new GetGoodsFTypeReq();
            //l.setUid("1");
            l.setSid(33);
            //请求类型
            req.setMessageType(MessageType.GetGoodsFTypeReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 搜索店铺接口
     */
    public static void SearchShopReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            SearchShopReq l=new SearchShopReq();
            //l.setUid("1");
            l.setName("自");
            //请求类型
            req.setMessageType(MessageType.SearchShopReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 根据店铺和产品类型获取产品列表
     */
    public static void GetGoodsByTypeReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetGoodsByTypeReq l=new GetGoodsByTypeReq();
            //l.setUid("1");
            l.setSid("33");
            l.setType("96");
            l.setFid("95");
            l.setPage("1");
            l.setPageNum("10");
            //请求类型
            req.setMessageType(MessageType.GetGoodsByTypeReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 根据店铺和产品ID获取商品详情
     */
    public static void GetGoodsInfoReq() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetGoodsInfoReq l=new GetGoodsInfoReq();
            //l.setUid("1");
            l.setSid("33");
            l.setGid("863");
            //请求类型
            req.setMessageType(MessageType.GetGoodsInfoReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 添加购物车
     */
    public static void AddCar() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            AddCarReq l=new AddCarReq();
            //l.setUid("1");
            l.setGid(3);
            l.setSid(1);
            l.setNum(5);
            l.setUid(1);
            //请求类型
            req.setMessageType(MessageType.AddCarReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 修购物车
     */
    public static void UpdateCar() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            UpdateCarReq l=new UpdateCarReq();
            //l.setUid("1");
            l.setGid(3);
            l.setSid(1);
            l.setNum(9);
            l.setUid(1);
            //请求类型
            req.setMessageType(MessageType.UpdateCarReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 删除物车
     */
    public static void DelCar() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            DelCarReq l=new DelCarReq();
            //l.setUid("1");
            l.setGid("3");
            l.setSid(1);
            l.setUid(1);
            //请求类型
            req.setMessageType(MessageType.DelCarReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取购物车商品
     */
    public static void GetCarGoods() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetCarGoodsReq l=new GetCarGoodsReq();
            //l.setUid("1");
            l.setUid(29);
            l.setPage(1);
            l.setPageNum(10);
            //请求类型
            req.setMessageType(MessageType.GetCarGoodsReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 注册接口
     */
    public static void Register() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            RegisterReq l=new RegisterReq();
            l.setTel("15545587532");
            l.setPassWd("aaaaaa");
            //请求类型
            req.setMessageType(MessageType.RegisterReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 版本检测
     */
    public static void AppUpdateCheck() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            AppUpdateCheckReq l=new AppUpdateCheckReq();
            l.setOS("android");
            l.setVer("1.00");
            //请求类型
            req.setMessageType(MessageType.AppUpdateCheckReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 密码修改
     */
    public static void UpdatePwd() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            UpdatePwdReq l=new UpdatePwdReq();
            l.setUid("5");
            l.setOPasswd("123456");
            l.setNPasswd("aaaaaa");
            //请求类型
            req.setMessageType(MessageType.UpdatePwdReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 添加收货地址
     */
    public static void AddAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            AddAddressReq l=new AddAddressReq();
            l.setUid(4);
            l.setName("宫庆峰");
            l.setTel("15545587532");
            l.setProvice("北京市");
            l.setCity("市辖区");
            l.setDistrict("海淀区");
            l.setAddress("北三环西路甲18号");
            //请求类型
            req.setMessageType(MessageType.AddAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 修改收货地址
     */
    public static void UpdateAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            UpdateAddressReq l=new UpdateAddressReq();
            l.setAid(6);
            l.setUid(4);
            l.setName("宫庆峰11");
            l.setTel("15545587332");
            l.setProvice("北京市");
            l.setCity("市辖区");
            l.setDistrict("东城区");
            l.setAddress("东城西路甲18号");
            //请求类型
            req.setMessageType(MessageType.UpdateAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 删除收货地址
     */
    public static void DelAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            DelAddressReq l=new DelAddressReq();
            l.setAid(6);
            l.setUid(4);
            //请求类型
            req.setMessageType(MessageType.DelAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    
    /**
     * 获取全部收货地址
     */
    public static void GetAllAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetAllAddressReq l=new GetAllAddressReq();
        
            l.setUid(12);
            //请求类型
            req.setMessageType(MessageType.GetAllAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取指定收货地址
     */
    public static void GetAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetAddressReq l=new GetAddressReq();
            l.setAid(40);
            l.setUid(0);
            //请求类型
            req.setMessageType(MessageType.GetAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 提交订单
     */
    public static void SubmitOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            OrderProductList plist=new OrderProductList();
            
            List<OrderProductInfo> list=new ArrayList<OrderProductInfo>();
            OrderProductInfo op1=new OrderProductInfo();
            op1.setCount("2");
            op1.setId(862);
            op1.setPrice("5");
            OrderProductInfo op2=new OrderProductInfo();
            op2.setCount("3");
            op2.setId(863);
            op2.setPrice("12");
            
            list.add(op1);
            list.add(op2);
            plist.setList(list);
            
            SubmitOrderReq l=new SubmitOrderReq();
            l.setAid(43);
            l.setUid(29);
            l.setOrderPrice("48");
          
            l.setReMark("");
            l.setSendPrice("2");
            l.setSid(33);
            l.setGoodsList(JSON.toJSONString(plist,JSONUtil.features));
            
            //请求类型
            req.setMessageType(MessageType.SubmitOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 修改订单状态
     */
    public static void UpdateOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            UpdateOrderReq l=new UpdateOrderReq();
            l.setStatus(3);
            l.setUid("4");
            l.setOid("1");
            l.setReason("不想要了");
            
            //请求类型
            req.setMessageType(MessageType.UpdateOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 删除订单
     */
    public static void DelOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            DelOrderReq l=new DelOrderReq();
            l.setUid("4");
            l.setOid("1");
            
            //请求类型
            req.setMessageType(MessageType.DelOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 订单支付成功通知
     */
    public static void PayOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            PayOrderReq l=new PayOrderReq();
            l.setUid("13");
            l.setOid("53");
            l.setPayType("2");
            l.setPayNo("20150729163058");
            //请求类型
            req.setMessageType(MessageType.PayOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取自营店信息
     */
    public static void NmSelfShop() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            NmSelfShopReq l=new NmSelfShopReq();
            l.setUid("4");
            //请求类型
            req.setMessageType(MessageType.NmSelfShopReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取待付款订单列表
     */
    public static void GetWPayOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetWPayOrderReq l=new GetWPayOrderReq();
            l.setUid("33");
            l.setPage("1");
            l.setPageNum("10");
            //请求类型
            req.setMessageType(MessageType.GetWPayOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取待收货订单列表
     */
    public static void GetWReceiveOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetWReceiveOrderReq l=new GetWReceiveOrderReq();
            l.setUid("13");
            l.setPage("1");
            l.setPageNum("10");
            //请求类型
            req.setMessageType(MessageType.GetWReceiveOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取历史订单列表
     */
    public static void GetHistoryOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetHistoryOrderReq l=new GetHistoryOrderReq();
            l.setUid("13");
            l.setPage("1");
            l.setPageNum("10");
            //请求类型
            req.setMessageType(MessageType.GetHistoryOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取历史订单列表
     */
    public static void DelFavGoods() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            DelFavGoodsReq l=new DelFavGoodsReq();
            l.setUid("4");
            l.setGid("2");
            l.setSid("1");
            //请求类型
            req.setMessageType(MessageType.DelFavGoodsReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 收藏商品
     */
    public static void FavGoods() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            FavGoodsReq l=new FavGoodsReq();
            l.setGid("4");
            l.setSid("1");
            l.setUid("4");
            //请求类型
            req.setMessageType(MessageType.FavGoodsReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取收藏商品
     */
    public static void GetFavGoods() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetFavGoodsReq l=new GetFavGoodsReq();
            l.setPage("1");
            l.setPageNum("10");
            l.setUid("4");
            //请求类型
            req.setMessageType(MessageType.GetFavGoodsReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取推送消息
     */
    public static void GetMessage() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetMessageReq l=new GetMessageReq();
            l.setPage(1);
            l.setPageNum(10);
            l.setUid(4);
            //请求类型
            req.setMessageType(MessageType.GetMessageReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 提交反馈意见
     */
    public static void FeedBack() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            FeedBackReq l=new FeedBackReq();
            l.setContent("测试意见");
            l.setUid(4);
            //请求类型
            req.setMessageType(MessageType.FeedBackReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 搜索商品
     */
    public static void SearchGoods() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            SearchGoodsReq l=new SearchGoodsReq();
            l.setName("巧克力");
            l.setUid("1");
            l.setSid("33");
            l.setPage("1");
            l.setPageNum("5");
            //请求类型
            req.setMessageType(MessageType.SearchGoodsReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 发送验证码
     */
    public static void SendCheckCode() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            SendCheckCodeReq l=new SendCheckCodeReq();
            l.setUid("6");
            l.setMobile("15545587532");
            l.setCheckCode("123456");
            //请求类型
            req.setMessageType(MessageType.SendCheckCodeReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 找回密码
     */
    public static void RetrievePwd() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            RetrievePwdReq l=new RetrievePwdReq();
            l.setMobile("15545587532");
            l.setPasswd("aaaaaa");
            l.setCheckCode("123654");
            //请求类型
            req.setMessageType(MessageType.RetrievePwdReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(postcontent,"utf-8"));    
            httpPost.setEntity(reqEntity);
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();            
            String rsp = URLDecoder.decode(EntityUtils.toString(entity2),"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 设置社区店铺收货地址
     */
    public static void setShopAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            SetShopAddressReq l=new SetShopAddressReq();
            l.setSid(24);
            l.setUid(12);
            l.setName("宫庆峰");
            l.setTel("15545587532");
            l.setProvice("北京市");
            l.setCity("市辖区");
            l.setDistrict("海淀区");
            l.setAddress("北三环西路甲18号");
            //请求类型
            req.setMessageType(MessageType.SetShopAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取社区店铺收货地址
     */
    public static void getShopAddress() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetShopAddressReq l=new GetShopAddressReq();
            l.setSid(24);
            l.setUid(12);
            //请求类型
            req.setMessageType(MessageType.GetShopAddressReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取社区店铺广告信息
     */
    public static void getShopAd() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetShopAdReq l=new GetShopAdReq();
            l.setSid(0);
            //请求类型
            req.setMessageType(MessageType.GetShopAdReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取社区店铺信息
     */
    public static void getShopInfo() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetShopInfoReq l=new GetShopInfoReq();
            l.setSid(24);
            //请求类型
            req.setMessageType(MessageType.GetShopInfoReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 商家登录
     */
    public static void shopLogin() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            ShopLoginReq l=new ShopLoginReq();
            l.setUsername("ceshi1");
            l.setPasswd("654321");
            //请求类型
            req.setMessageType(MessageType.ShopLoginReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 商家修改密码
     */
    public static void shopUpdatePwd() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            ShopUpdatePwdReq l=new ShopUpdatePwdReq();
            l.setSid("34");
            l.setOPasswd("654321");
            l.setNPasswd("123456");
            //请求类型
            req.setMessageType(MessageType.ShopUpdatePwdReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取全部商品
     */
    public static void getAllGoodsByShop() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetAllGoodsByShopReq l=new GetAllGoodsByShopReq();
            l.setSid(75);
            l.setPage(1);
            l.setPageNum("10");
            //请求类型
            req.setMessageType(MessageType.GetAllGoodsByShopReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取我的商品
     */
    public static void getMyGoodsByShop() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetMyGoodsByShopReq l=new GetMyGoodsByShopReq();
            l.setSid(34);
            l.setPage(1);
            l.setPageNum("15");
            //请求类型
            req.setMessageType(MessageType.GetMyGoodsByShopReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    
    /**
     * 商品上架
     */
    public static void shopGoodsUp() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            ShopGoodsUpReq l=new ShopGoodsUpReq();
            l.setGid("838,839");
            l.setSid("34");
            //请求类型
            req.setMessageType(MessageType.ShopGoodsUpReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 商品下架
     */
    public static void shopGoodsDown() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            ShopGoodsDownReq l=new ShopGoodsDownReq();
            l.setSpid("146,147");
            l.setSid("34");
            //请求类型
            req.setMessageType(MessageType.ShopGoodsDownReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    

    /**
     * 价格和库存修改
     */
    public static void shopGoodsUpdate() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            ShopGoodsUpdateReq l=new ShopGoodsUpdateReq();
            l.setNum("66");
            l.setPrice("12.8");
            l.setSid("34");
            l.setSpid("149");
            //请求类型
            req.setMessageType(MessageType.ShopGoodsUpdateReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取待处理订单
     */
    public static void getWSendOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetWSendOrderReq l=new GetWSendOrderReq();
            l.setSid("33");
            l.setPage("1");
            l.setPageNum("15");
            //请求类型
            req.setMessageType(MessageType.GetWSendOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取派送中订单
     */
    public static void getSendingOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetSendingOrderReq l=new GetSendingOrderReq();
            l.setSid("33");
            l.setPage("1");
            l.setPageNum("15");
            //请求类型
            req.setMessageType(MessageType.GetSendingOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取已完成订单
     */
    public static void getSendedOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetSendedOrderReq l=new GetSendedOrderReq();
            l.setSid("33");
            l.setPage("1");
            l.setPageNum("15");
            //请求类型
            req.setMessageType(MessageType.GetSendedOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 更改订单状态
     */
    public static void shopUpdateOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            ShopUpdateOrderReq l=new ShopUpdateOrderReq();
            l.setOid("53");
            l.setReason("");
            l.setStatus(3);
            l.setSid("34");
            //请求类型
            req.setMessageType(MessageType.ShopUpdateOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取已完成订单
     */
    public static void getTotalOrder() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetTotalOrderReq l=new GetTotalOrderReq();
            l.setSid("33");
            l.setPage("1");
            l.setPageNum("15");
            //请求类型
            req.setMessageType(MessageType.GetTotalOrderReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    /**
     * 获取商品信息
     */
    public static void getShopGoods() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/

            GetShopGoodsInfoReq l=new GetShopGoodsInfoReq();
            l.setGid(1256);
            //请求类型
            req.setMessageType(MessageType.GetShopGoodsInfoReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取省列表
     */
    public static void GetProvince() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetProvinceReq l=new GetProvinceReq();
            //l.setUid("1");
            l.setUid(18);
            //请求类型
            req.setMessageType(MessageType.GetProvinceReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    /**
     * 获取市列表
     */
    public static void GetCity() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetCityReq l=new GetCityReq();
            //l.setUid("1");
            l.setUid(18);
            l.setProvinceid(110000);
            //请求类型
            req.setMessageType(MessageType.GetCityReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    /**
     * 获取区列表
     */
    public static void GetDistrict() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetDistrictReq l=new GetDistrictReq();
            //l.setUid("1");
            l.setUid(18);
            l.setCityid(110100);
            //请求类型
            req.setMessageType(MessageType.GetDistrictReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     * 获取指定辖区商店列表
     */
    public static void GetShop() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetShopReq l=new GetShopReq();
            //l.setUid("1");
            l.setUid(18);
            l.setCid(110100);
            l.setDid(110101);
            //请求类型
            req.setMessageType(MessageType.GetShopReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    

    /**
     * 设置个人资料
     */
    public static void SetPerInfo() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            SetPerInfoReq l=new SetPerInfoReq();
            //l.setUid("1");
            l.setUid(18);
            l.setNickName("五百万");
            l.setAge("");
            l.setHidename(0);
            l.setMarriage(3);
            l.setName("吴百万");
            l.setSex(1);
            //请求类型
            req.setMessageType(MessageType.SetPerInfoReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    /**
     * 获取个人资料
     */
    public static void GetPerInfo() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetPerInfoReq l=new GetPerInfoReq();
            //l.setUid("1");
            l.setUid(18);
            //请求类型
            req.setMessageType(MessageType.GetPerInfoReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
          //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    /**
     * 设置个人头像
     */
    public static void SetHeadImg() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            SetHeadImgReq l=new SetHeadImgReq();
            //l.setUid("1");
            File file = new File("D:\\abc.jpg");  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            byte[] buffer = bos.toByteArray(); 
            String img=ByteUtil.bytesToHexStr(buffer);
            l.setUid(18);
            l.setImg(img);
            //请求类型
            req.setMessageType(MessageType.SetHeadImgReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    /**
     * 清空购物车
     */
    public static void DelCarAll() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            DelCarAllReq l=new DelCarAllReq();
            //l.setUid("1");
            l.setUid(16);
            //请求类型
            req.setMessageType(MessageType.DelCarAllReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     *  获取首页招募令、0元购、限时抢购信息
     */
    public static void GetHpInfo() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetHpInfoReq l=new GetHpInfoReq();
            l.setUid(1);
            l.setSid(33);
            //请求类型
            req.setMessageType(MessageType.GetHpInfoReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     *  获取用户订单数
     */
    public static void GetUserOrderNum() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetUserOrderNumReq l=new GetUserOrderNumReq();
            l.setUid(13);
            //请求类型
            req.setMessageType(MessageType.GetUserOrderNumReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    
    /**
     *  获取用户限时抢购数
     */
    public static void GetShopGoodsBuyNum() {
        HttpPost httpPost = new HttpPost(posturl);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();       
            String reqStr = "";
            String postcontent = ""; 
            HttpContent req = new HttpContent();
            
            /*****************发送参数设置*************************/
            GetShopGoodsBuyNumReq l=new GetShopGoodsBuyNumReq();
            l.setUid(13);
            l.setGid(864);
            l.setSid(33);
            //请求类型
            req.setMessageType(MessageType.GetShopGoodsBuyNumReq);
            /*****************************************************/
            
            reqStr = coder.encode(l);
            //返回时间戳
            String msgTime = HttpDateUtil.getNowDateString();
            req.setMessageTimestamp(msgTime);                      
            req.setMessageContent(reqStr);
            postcontent = coder.encode(req);
            //返回数据               
            log.info("请求数据：" + postcontent);
            /*URLEncoder编码*/
            postcontent=URLEncoder.encode(postcontent,"utf-8");
            /*3DES加密*/
            ByteArrayEntity reqEntity = new ByteArrayEntity(Des3.encode(postcontent.getBytes("utf-8")));    
            httpPost.setEntity(reqEntity);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            HttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            
            /*3DES解密*/
            InputStream in=entity2.getContent();
            byte[] rspdata=IOUtils.toByteArray(in);
            String rsp = new String(Des3.decode(rspdata));
            /*URLDecoder解码*/
            rsp=URLDecoder.decode(rsp,"utf-8");
            //请求数据
            log.info("返回数据：" + rsp);
            //解析数据 包
            //HttpContent body = coder.decode(rsp, HttpContent.class);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
    }
    

    
}
