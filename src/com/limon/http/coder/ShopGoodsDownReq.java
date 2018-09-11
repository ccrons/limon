package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopGoodsDownReq")
public class ShopGoodsDownReq {
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Spid")
	private String Spid;
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getSpid() {
		return Spid;
	}
	public void setSpid(String spid) {
		Spid = spid;
	}
}
