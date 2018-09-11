package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"name","count","gid","imgurl","opprice"})
public class OrderProductGet {
	@JSONField(name="gid")
	private Integer gid;
	@JSONField(name="count")
	private Integer count;
	@JSONField(name="name")
	private String name;
	@JSONField(name="imgurl")
	private String imgurl;
	@JSONField(name="opprice")
	private String opprice;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getOpprice() {
		return opprice;
	}
	public void setOpprice(String opprice) {
		this.opprice = opprice;
	}

}
