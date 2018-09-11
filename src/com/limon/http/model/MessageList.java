package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"messagelist"})
public class MessageList {
	@JSONField(name="messagelist")
	private List<Message> list;

	public List<Message> getList() {
		return list;
	}

	public void setList(List<Message> list) {
		this.list = list;
	}
}
