package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","spid","name","price","imgurl","specifications","num","isofficial"})
public class ShopMyGoods {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="spid")
	private Integer spid;
	@JSONField(name="name")
	private String name;
	@JSONField(name="price")
	private String price;
	@JSONField(name="imgurl")
	private String imgurl="";
	@JSONField(name="specifications")
	private String specifications;
	@JSONField(name="num")
	private Integer num;
	@JSONField(name="isofficial")
	private Integer isofficial;
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
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getSpid() {
		return spid;
	}
	public void setSpid(Integer spid) {
		this.spid = spid;
	}
	public Integer getIsofficial() {
		return isofficial;
	}
	public void setIsofficial(Integer isofficial) {
		this.isofficial = isofficial;
	}
}
