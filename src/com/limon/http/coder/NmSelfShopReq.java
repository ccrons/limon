package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="NmSelfShopReq")
public class NmSelfShopReq {
	@XmlNode(name="Uid")
	private String Uid;

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}
}
