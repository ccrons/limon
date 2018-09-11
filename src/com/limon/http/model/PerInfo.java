package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"nickname","name","sex","age","marriage","icourl","hidename"})
public class PerInfo {
	@JSONField(name="nickname")
	private String nickname;
	@JSONField(name="name")
	private String realname;
	@JSONField(name="sex")
	private Integer sex;
	@JSONField(name="age")
	private String age;
	@JSONField(name="marriage")
	private Integer marriage;
	@JSONField(name="icourl")
	private String headimg;
	@JSONField(name="hidename")
	private Integer hidename;
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Integer getMarriage() {
		return marriage;
	}
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public Integer getHidename() {
		return hidename;
	}
	public void setHidename(Integer hidename) {
		this.hidename = hidename;
	}
}
