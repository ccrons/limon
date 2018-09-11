package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetHpInfoReq")
public class GetHpInfoReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Sid")
	private Integer Sid;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
}
