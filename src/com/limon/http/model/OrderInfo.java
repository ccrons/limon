package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","orderprice"})
public class OrderInfo {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="orderprice")
	private String orderprice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrice() {
		return orderprice;
	}
	public void setPrice(String orderprice) {
		this.orderprice = orderprice;
	}
}
