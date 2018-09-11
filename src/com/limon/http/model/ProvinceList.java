package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"provincelist"})
public class ProvinceList {
	@JSONField(name="provincelist")
	private List<Province> list;

	public List<Province> getList() {
		return list;
	}

	public void setList(List<Province> list) {
		this.list = list;
	}

}
