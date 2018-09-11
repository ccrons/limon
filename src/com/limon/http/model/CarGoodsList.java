package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"goodslist"})
public class CarGoodsList {
	@JSONField(name="goodslist")
	private List<CarGoods> list;

	public List<CarGoods> getList() {
		return list;
	}

	public void setList(List<CarGoods> list) {
		this.list = list;
	}
}
