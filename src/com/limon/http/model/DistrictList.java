package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"districtlist"})
public class DistrictList {
	@JSONField(name="districtlist")
	private List<District> list;

	public List<District> getList() {
		return list;
	}

	public void setList(List<District> list) {
		this.list = list;
	}

}
