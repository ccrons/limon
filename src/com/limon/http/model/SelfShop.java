package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","name","tel","bussinesstime","sale","ad"})
public class SelfShop {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="name")
	private String storename;
	@JSONField(name="tel")
	private String mobile;
	@JSONField(name="bussinesstime")
	private String bussinesstime;
	@JSONField(name="sendprice")
	private String sendprice;
	@JSONField(name="startprice")
	private String startprice;
	@JSONField(name="sale")
	private List<SaleInfo> saleinfo;
	@JSONField(serialize = false)
	private String bussiness_stime="";
	@JSONField(serialize = false)
	private String bussiness_etime="";
	@JSONField(name="ad")
	private List<AdInfo> adinfo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBussinesstime() {
		if(bussiness_stime!=null||bussiness_etime!=null){
			this.bussinesstime = bussiness_stime+"-"+bussiness_etime;
		}else{
			this.bussinesstime = "";
		}
		return this.bussinesstime;
	}
	public void setBussinesstime(String bussinesstime) {
		this.bussinesstime = bussinesstime;
	}
	public String getBussiness_stime() {
		return bussiness_stime;
	}
	public void setBussiness_stime(String bussinessStime) {
		bussiness_stime = bussinessStime;
	}
	public String getBussiness_etime() {
		return bussiness_etime;
	}
	public void setBussiness_etime(String bussinessEtime) {
		bussiness_etime = bussinessEtime;
	}
	public List<SaleInfo> getSaleinfo() {
		return saleinfo;
	}
	public void setSaleinfo(List<SaleInfo> saleinfo) {
		this.saleinfo = saleinfo;
	}
	public List<AdInfo> getAdinfo() {
		return adinfo;
	}
	public void setAdinfo(List<AdInfo> adinfo) {
		this.adinfo = adinfo;
	}
	public String getSendprice() {
		return sendprice;
	}
	public void setSendprice(String sendprice) {
		this.sendprice = sendprice;
	}
	public String getStartprice() {
		return startprice;
	}
	public void setStartprice(String startprice) {
		this.startprice = startprice;
	}
	
}
