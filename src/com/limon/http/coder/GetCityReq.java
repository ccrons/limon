package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetCityReq")
public class GetCityReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Provinceid")
	private Integer Provinceid;
	public Integer getUid() {
		return Uid;
	}

	public void setUid(Integer uid) {
		Uid = uid;
	}

	public Integer getProvinceid() {
		return Provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		Provinceid = provinceid;
	}

}
