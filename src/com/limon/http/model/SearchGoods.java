package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","name","price","imgurl","specifications","typeid","typename","oprice","type","isrec","islimit","isad","infourl"})
public class SearchGoods {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="name")
	private String name;
	@JSONField(name="price")
	private String price;
	@JSONField(name="imgurl")
	private String imgurl="";
	@JSONField(name="specifications")
	private String specifications;
	@JSONField(name="typeid")
	private String typeid;
	@JSONField(name="typename")
	private String typename;
	@JSONField(name="oprice")
	private String oprice;
	@JSONField(name="type")
	private String type;
	@JSONField(name="isrec")
	private String isrec;
	@JSONField(name="islimit")
	private String islimit;
	@JSONField(name="isad")
	private String isad;
	@JSONField(serialize = false)
	private transient String isfirst;
	@JSONField(name="infourl")
	private String infourl="";
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
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getInfourl() {
		return infourl;
	}
	public void setInfourl(String infourl) {
		this.infourl = infourl;
	}
	public String getIsfirst() {
		return isfirst;
	}
	public void setIsfirst(String isfirst) {
		this.isfirst = isfirst;
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
}
