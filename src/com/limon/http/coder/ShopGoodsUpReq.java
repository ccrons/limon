package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopGoodsUpReq")
public class ShopGoodsUpReq {
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Gid")
	private String Gid;
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
