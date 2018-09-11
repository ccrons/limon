package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","name","price","sprice","imgurl","isofficial","specifications","infourl","isrec","type","islimit","isad"})
public class Goods {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="name")
	private String name;
	@JSONField(name="price")
	private String price;
	@JSONField(name="sprice")
	private String sprice;
	@JSONField(name="imgurl")
	private String imgurl="";
	@JSONField(name="isofficial")
	private String isofficial;
	@JSONField(name="specifications")
	private String specifications;
	@JSONField(name="infourl")
	private String infourl;
	@JSONField(name="isrec")
	private String isrec;
	@JSONField(name="islimit")
	private String islimit;
	@JSONField(name="isad")
	private String isad;
	@JSONField(name="type")
	private String type;
	@JSONField(serialize = false)
	private transient String isfirst;
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
	public String getSprice() {
		return sprice;
	}
	public void setSprice(String sprice) {
		this.sprice = sprice;
	}
	public String getIsofficial() {
		return isofficial;
	}
	public void setIsofficial(String isofficial) {
		this.isofficial = isofficial;
	}
	public String getInfourl() {
		return infourl;
	}
	public void setInfourl(String infourl) {
		this.infourl = infourl;
	}
	public String getIsrec() {
		return isrec;
	}
	public void setIsrec(String isrec) {
		this.isrec = isrec;
	}
	public String getIslimit() {
		return islimit;
	}
	public void setIslimit(String islimit) {
		this.islimit = islimit;
	}
	public String getIsad() {
		return isad;
	}
	public void setIsad(String isad) {
		this.isad = isad;
	}
	public String getIsfirst() {
		return isfirst;
	}
	public void setIsfirst(String isfirst) {
		this.isfirst = isfirst;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
