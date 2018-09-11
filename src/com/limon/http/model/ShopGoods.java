package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","name","brand","specifications","imgurls","num","price","oprice","type","isrec","isofficial","infourl"})
public class ShopGoods {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="name")
	private String name;
	@JSONField(name="brand")
	private String brand;
	@JSONField(name="specifications")
	private String unit;
	@JSONField(name="imgurls")
	private String imgurls;
	@JSONField(name="num")
	private String num;
	@JSONField(name="price")
	private String price;
	@JSONField(name="oprice")
	private String oprice;
	@JSONField(name="type")
	private String type;
	@JSONField(name="isrec")
	private String isrec;
	@JSONField(name="isofficial")
	private String isofficial;
	@JSONField(serialize = false)
	private String pic1;
	@JSONField(serialize = false)
	private String pic2;
	@JSONField(serialize = false)
	private String pic3;
	@JSONField(serialize = false)
	private String pic4;
	@JSONField(serialize = false)
	private String pic5;
	@JSONField(name="infourl")
	private String infourl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getImgurls() {
		String imgs="";
		if(this.pic1!=null&&!this.pic1.equals("")){
			imgs+=pic1+",";
		}
		if(this.pic2!=null&&!this.pic2.equals("")){
			imgs+=pic2+",";
		}
		if(this.pic3!=null&&!this.pic3.equals("")){
			imgs+=pic3+",";
		}
		if(this.pic4!=null&&!this.pic4.equals("")){
			imgs+=pic4+",";
		}
		if(this.pic5!=null&&!this.pic5.equals("")){
			imgs+=pic5+",";
		}
		if(imgs.length()>0){
			imgs=imgs.substring(0,imgs.length()-1);
		}
		this.imgurls=imgs;
		return imgurls;
	}
	public void setImgurls(String imgurls) {
		this.imgurls = imgurls;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getPic4() {
		return pic4;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	public String getPic5() {
		return pic5;
	}
	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
