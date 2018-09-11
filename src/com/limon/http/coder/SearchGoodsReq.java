package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SearchGoodsReq")
public class SearchGoodsReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Name")
	private String Name;
	@XmlNode(name="Page")
	private String Page;
	@XmlNode(name="PageNum")
	private String PageNum;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getPage() {
		return Page;
	}
	public void setPage(String page) {
		Page = page;
	}
	public String getPageNum() {
		return PageNum;
	}
	public void setPageNum(String pageNum) {
		PageNum = pageNum;
	}
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
