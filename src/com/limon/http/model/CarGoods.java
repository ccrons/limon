package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","sid","sname","name","price","count","imgurl","specifications","islimit","buynum","limitnum"})
public class CarGoods {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="sid")
	private String sid;
	@JSONField(name="sname")
	private String sname;
	@JSONField(name="name")
	private String name;
	@JSONField(name="price")
	private String price;
	@JSONField(name="count")
	private String count;
	@JSONField(name="imgurl")
	private String imgurl;
	@JSONField(name="specifications")
	private String specifications;
	@JSONField(name="islimit")
	private String islimit;
	@JSONField(name="buynum")
	private String buynum;
	@JSONField(name="limitnum")
	private String limitnum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getIslimit() {
		return islimit;
	}
	public void setIslimit(String islimit) {
		this.islimit = islimit;
	}
	public String getBuynum() {
		return buynum;
	}
	public void setBuynum(String buynum) {
		this.buynum = buynum;
	}
	public String getLimitnum() {
		return limitnum;
	}
	public void setLimitnum(String limitnum) {
		this.limitnum = limitnum;
	}
}
