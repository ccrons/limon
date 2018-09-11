package com.limon.http.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","content","sendtime"})
public class Message {
	@JSONField(name="id")
	private Integer id;
	@JSONField(name="content")
	private String content;
	@JSONField(name="sendtime",format = "yyyy-MM-dd HH:mm:ss")
	public Date sendtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
}
