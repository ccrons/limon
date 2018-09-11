package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SearchShopReq")
public class SearchShopReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Name")
	private String Name;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
