package com.limon.http.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"uid","username","mobile","headimg","createtime","nickname","sex","age","marriage","hidename","realname"})
public class AppUser {
	@JSONField(name="uid")
	private Integer id;
	@JSONField(name="username")
	private String username;
	@JSONField(serialize = false)
	private String password;
	@JSONField(name="mobile")
	private String mobile;
	@JSONField(name="headimg")
	private String headimg;
	@JSONField(name="createtime")
	private String createtime;
	
	@JSONField(name="nickname")
	private String nickname;
	@JSONField(name="sex")
	private Integer sex;
	@JSONField(name="age")
	private Integer age;
	@JSONField(name="marriage")
	private Integer marriage;
	@JSONField(name="hidename")
	private Integer hidename;
	@JSONField(name="realname")
	private String realname;
	
	@JSONField(name="forbbs")
	private String bbsInfo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getMarriage() {
		return marriage;
	}
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	public Integer getHidename() {
		return hidename;
	}
	public void setHidename(Integer hidename) {
		this.hidename = hidename;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getBbsInfo() {
		return bbsInfo;
	}
	public void setBbsInfo(String bbsInfo) {
		this.bbsInfo = bbsInfo;
	}
}
