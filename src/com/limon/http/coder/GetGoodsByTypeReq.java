package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetGoodsByTypeReq")
public class GetGoodsByTypeReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Type")
	private String Type;
	@XmlNode(name="Fid")
	private String Fid;
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
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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
	public String getFid() {
		return Fid;
	}
	public void setFid(String fid) {
		Fid = fid;
	}
}
