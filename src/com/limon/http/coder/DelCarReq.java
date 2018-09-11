package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="DelCarReq")
public class DelCarReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Sid")
	private Integer Sid;
	@XmlNode(name="Gid")
	private String Gid;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public String getGid() {
		return Gid;
	}
	public void setGid(String gid) {
		Gid = gid;
	}
}
