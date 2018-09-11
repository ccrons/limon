package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id"})
public class ShopLogin {
	@JSONField(name="id")
	private Integer id;
	@JSONField(serialize = false)
	private String username;
	@JSONField(serialize = false)
	private String storename;
	@JSONField(serialize = false)
	private String password;
	@JSONField(serialize = false)
	private Integer isself;
	@JSONField(serialize = false)
	private String createtime;
	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsself() {
		return isself;
	}
	public void setIsself(Integer isself) {
		this.isself = isself;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
