package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","spid","name","price","imgurl","specifications","num","isofficial","isrec","isfrist","isluck"})
public class ShopMyGoodsIndex {
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
	@JSONField(name="isrec")
	private Integer isrec;
	@JSONField(name="isfrist")
	private Integer isfrist;
	@JSONField(name="isluck")
	private Integer isluck;
	private String adid;//广告活动id
	private Double tlprice;//限时抢购价钱
	private Double adprice;
	private String infourl;
	private String tltime;
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
	public Integer getIsrec() {
		return isrec;
	}
	public void setIsrec(Integer isrec) {
		this.isrec = isrec;
	}
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	public Integer getIsfrist() {
		return isfrist;
	}
	public void setIsfrist(Integer isfrist) {
		this.isfrist = isfrist;
	}
	public String getInfourl() {
		return infourl;
	}
	public void setInfourl(String infourl) {
		this.infourl = infourl;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public Double getTlprice() {
		return tlprice;
	}
	public void setTlprice(Double tlprice) {
		this.tlprice = tlprice;
	}
	public Double getAdprice() {
		return adprice;
	}
	public void setAdprice(Double adprice) {
		this.adprice = adprice;
	}
	public String getTltime() {
		return tltime;
	}
	public void setTltime(String tltime) {
		this.tltime = tltime;
	}
}
