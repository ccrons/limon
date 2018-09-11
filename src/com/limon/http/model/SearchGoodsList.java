package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"goodslist"})
public class SearchGoodsList {
	@JSONField(name="goodslist")
	private List<SearchGoods> list;

	public List<SearchGoods> getList() {
		return list;
	}

	public void setList(List<SearchGoods> list) {
		this.list = list;
	}
}
