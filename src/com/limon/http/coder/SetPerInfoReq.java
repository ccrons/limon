package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SetPerInfoReq")
public class SetPerInfoReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="NickName")
	private String NickName;
	@XmlNode(name="Name")
	private String Name;
	@XmlNode(name="Sex")
	private Integer Sex;
	@XmlNode(name="Age")
	private String Age;
	@XmlNode(name="Marriage")
	private Integer Marriage;
	@XmlNode(name="Hidename")
	private Integer Hidename;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getSex() {
		return Sex;
	}
	public void setSex(Integer sex) {
		Sex = sex;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public Integer getMarriage() {
		return Marriage;
	}
	public void setMarriage(Integer marriage) {
		Marriage = marriage;
	}
	public Integer getHidename() {
		return Hidename;
	}
	public void setHidename(Integer hidename) {
		Hidename = hidename;
	}
	
}
