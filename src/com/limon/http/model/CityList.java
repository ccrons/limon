package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"citylist"})
public class CityList {
	@JSONField(name="citylist")
	private List<City> list;

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}
}
