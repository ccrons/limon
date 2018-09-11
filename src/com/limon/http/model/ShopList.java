package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"shoplist"})
public class ShopList {
	@JSONField(name="shoplist")
	private List<Shop> list;

	public List<Shop> getList() {
		return list;
	}

	public void setList(List<Shop> list) {
		this.list = list;
	}
}
