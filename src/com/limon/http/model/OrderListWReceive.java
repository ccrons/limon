package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"orderlist"})
public class OrderListWReceive {
	@JSONField(name="orderlist")
	private List<OrderWReceive> list;

	public List<OrderWReceive> getList() {
		return list;
	}

	public void setList(List<OrderWReceive> list) {
		this.list = list;
	}
}
