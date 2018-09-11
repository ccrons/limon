package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"goodslist"})
public class ShopAllList {
	@JSONField(name="goodslist")
	private List<ShopAllGoods> list;

	public List<ShopAllGoods> getList() {
		return list;
	}

	public void setList(List<ShopAllGoods> list) {
		this.list = list;
	}
}
