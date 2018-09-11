package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"goodslist"})
public class OrderProductList {
	@JSONField(name="goodslist")
	private List<OrderProductInfo> list;

	public List<OrderProductInfo> getList() {
		return list;
	}

	public void setList(List<OrderProductInfo> list) {
		this.list = list;
	}
}
