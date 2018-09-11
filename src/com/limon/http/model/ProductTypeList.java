package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"typelist"})
public class ProductTypeList {
	@JSONField(name="typelist")
	private List<ProductType> list;

	public List<ProductType> getList() {
		return list;
	}

	public void setList(List<ProductType> list) {
		this.list = list;
	}

}
