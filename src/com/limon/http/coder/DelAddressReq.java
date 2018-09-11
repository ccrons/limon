package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="DelAddressReq")
public class DelAddressReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Aid")
	private Integer Aid;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getAid() {
		return Aid;
	}
	public void setAid(Integer aid) {
		Aid = aid;
	}

}
