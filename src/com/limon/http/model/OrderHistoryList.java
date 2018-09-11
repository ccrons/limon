package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"orderlist"})
public class OrderHistoryList {
	@JSONField(name="orderlist")
	private List<OrderHistory> list;

	public List<OrderHistory> getList() {
		return list;
	}

	public void setList(List<OrderHistory> list) {
		this.list = list;
	}
}
