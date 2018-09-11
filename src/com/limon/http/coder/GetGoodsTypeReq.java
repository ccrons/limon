package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetGoodsTypeReq")
public class GetGoodsTypeReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Fid")
	private String Fid;
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
	public String getFid() {
		return Fid;
	}
	public void setFid(String fid) {
		Fid = fid;
	}
}
