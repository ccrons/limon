package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="DelFavGoodsReq")
public class DelFavGoodsReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Gid")
	private String Gid;
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
	public String getGid() {
		return Gid;
	}
	public void setGid(String gid) {
		Gid = gid;
	}
}
