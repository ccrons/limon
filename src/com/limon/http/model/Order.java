package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"oid","sprice","aid","sid","sname","time","list","address","sendtime","picknum","remark","paytype","isright","isluck"})
public class Order {
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
	@JSONField(name="time")
	private String time;
	@JSONField(name="list")
	private List<OrderProductGet> list;
	
	@JSONField(name="address")
	private Address address;
	@JSONField(name="sendtime")
	private String sendtime;
	@JSONField(name="picknum")
	private String picknum;
	@JSONField(name="remark")
	private String remark;
	
	@JSONField(name="paytype")
	private String paytype;
	
	@JSONField(name="isright")
	private Integer isright;
	
	@JSONField(name="isluck")
	private Integer isluck;
	
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
	@JSONField(deserialize=false)
	private transient String goodscode;
	@JSONField(deserialize=false)
	private transient Integer userid;
	@JSONField(deserialize=false)
	private transient String orderno;
	@JSONField(deserialize=false)
	private transient Integer hasad;
	@JSONField(deserialize=false)
	private transient String status;

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
	public String getPicknum() {
		return picknum;
	}
	public void setPicknum(String picknum) {
		this.picknum = picknum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public Integer getIsright() {
		return isright;
	}
	public void setIsright(Integer isright) {
		this.isright = isright;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Integer getHasad() {
		return hasad;
	}
	public void setHasad(Integer hasad) {
		this.hasad = hasad;
	}
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
