package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","name","price","imgurl","sid","oprice","type","isrec"})
public class FavGoods {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="name")
	private String name;
	@JSONField(name="price")
	private String price;
	@JSONField(name="imgurl")
	private String imgurl="";
	@JSONField(name="sid")
	private String sid;
	@JSONField(name="oprice")
	private String oprice;
	@JSONField(name="type")
	private String type;
	@JSONField(name="isrec")
	private String isrec;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getOprice() {
		return oprice;
	}
	public void setOprice(String oprice) {
		this.oprice = oprice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsrec() {
		return isrec;
	}
	public void setIsrec(String isrec) {
		this.isrec = isrec;
	}
}
