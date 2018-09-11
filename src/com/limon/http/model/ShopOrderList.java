package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"orderlist"})
public class ShopOrderList {
	@JSONField(name="orderlist")
	private List<ShopOrder> list;

	public List<ShopOrder> getList() {
		return list;
	}

	public void setList(List<ShopOrder> list) {
		this.list = list;
	}
}
