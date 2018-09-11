package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopAdReq")
public class GetShopAdReq {
	@XmlNode(name="Sid")
	private Integer Sid;

	public Integer getSid() {
		return Sid;
	}

	public void setSid(Integer sid) {
		Sid = sid;
	}
}
