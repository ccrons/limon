package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"oid","orderno","sprice","address","acceptuser","accepttel","sid","sname","time","status","list","sendtime","picknum","paytype","remark","isluck"})
public class ShopOrder {
	@JSONField(name="oid")
	private Integer id;
	@JSONField(name="orderno")
	private String orderno;
	@JSONField(name="sprice")
	private String sprice;
	@JSONField(name="province")
	private String province;
	@JSONField(name="city")
	private String city;
	@JSONField(name="county")
	private String county;
	@JSONField(name="address")
	private String address;
	@JSONField(name="acceptuser")
	private String acceptuser;
	@JSONField(name="accepttel")
	private String accepttel;
	@JSONField(name="sid")
	private String sid;
	@JSONField(name="sname")
	private String sname;
	@JSONField(name="time")
	private String time;
	@JSONField(name="status")
	private String status;
	@JSONField(name="list")
	private List<OrderProductGet> list;
	@JSONField(name="sendtime")
	private String sendtime;
	@JSONField(name="picknum")
	private String picknum;
	@JSONField(name="paytype")
	private String paytype;
	@JSONField(name="remark")
	private String remark;
	
	@JSONField(name="isluck")
	private Integer isluck;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSprice() {
		return sprice;
	}
	public void setSprice(String sprice) {
		this.sprice = sprice;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAcceptuser() {
		return acceptuser;
	}
	public void setAcceptuser(String acceptuser) {
		this.acceptuser = acceptuser;
	}
	public String getAccepttel() {
		return accepttel;
	}
	public void setAccepttel(String accepttel) {
		this.accepttel = accepttel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<OrderProductGet> getList() {
		return list;
	}
	public void setList(List<OrderProductGet> list) {
		this.list = list;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getPicknum() {
		return picknum;
	}
	public void setPicknum(String picknum) {
		this.picknum = picknum;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	
}
