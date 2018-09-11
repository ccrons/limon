package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"goodslist"})
public class ShopMyList {
	@JSONField(name="goodslist")
	private List<ShopMyGoods> list;

	public List<ShopMyGoods> getList() {
		return list;
	}

	public void setList(List<ShopMyGoods> list) {
		this.list = list;
	}
}
