package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"goodslist"})
public class FavGoodsList {
	@JSONField(name="goodslist")
	private List<FavGoods> list;

	public List<FavGoods> getList() {
		return list;
	}

	public void setList(List<FavGoods> list) {
		this.list = list;
	}
}
