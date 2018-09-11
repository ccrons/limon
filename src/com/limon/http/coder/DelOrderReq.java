package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="DelOrderReq")
public class DelOrderReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Oid")
	private String Oid;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getOid() {
		return Oid;
	}
	public void setOid(String oid) {
		Oid = oid;
	}
}
