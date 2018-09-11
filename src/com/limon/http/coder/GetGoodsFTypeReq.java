package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetGoodsFTypeReq")
public class GetGoodsFTypeReq {
	@XmlNode(name="Sid")
	private Integer Sid;
	@XmlNode(name="Uid")
	private Integer Uid;
	public Integer getSid() {
		return Sid;
	}

	public void setSid(Integer sid) {
		Sid = sid;
	}

	public Integer getUid() {
		return Uid;
	}

	public void setUid(Integer uid) {
		Uid = uid;
	}
}
