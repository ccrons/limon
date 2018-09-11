package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetUserOrderNumReq")
public class GetUserOrderNumReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
}
