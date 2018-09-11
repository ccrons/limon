package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"oid","sprice","aid","sid","sname","type","time","list","address","sendtime","picknum","remark","isluck"})
public class OrderHistory {
	@JSONField(name="oid")
	private Integer id;
	@JSONField(name="sprice")
	private String sprice;
	@JSONField(name="aid")
	private String aid;
	@JSONField(name="sid")
	private String sid;
	@JSONField(name="sname")
	private String sname;
	@JSONField(name="type")
	private Integer type;
	@JSONField(serialize = false)
	private transient Integer status;
	@JSONField(name="time")
	private String time;
	@JSONField(name="list")
	private List<OrderProductGet> list;
	@JSONField(name="remark")
	private String remark;
	@JSONField(name="address")
	private Address address;
	@JSONField(name="sendtime")
	private String sendtime;

	@JSONField(name="picknum")
	private String picknum;
	@JSONField(deserialize=false)
	private transient String province;
	@JSONField(deserialize=false)
	private transient String city;
	@JSONField(deserialize=false)
	private transient String county;
	@JSONField(deserialize=false)
	private transient String addr;
	@JSONField(deserialize=false)
	private transient String acceptname;
	@JSONField(deserialize=false)
	private transient String accepttel;
	
	@JSONField(name="paytype")
	private String paytype;
	
	@JSONField(name="isluck")
	private Integer isluck;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAcceptname() {
		return acceptname;
	}
	public void setAcceptname(String acceptname) {
		this.acceptname = acceptname;
	}
	public String getAccepttel() {
		return accepttel;
	}
	public void setAccepttel(String accepttel) {
		this.accepttel = accepttel;
	}
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
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	
}
