package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetProvinceReq")
public class GetProvinceReq {
	@XmlNode(name="Uid")
	private Integer Uid;

	public Integer getUid() {
		return Uid;
	}

	public void setUid(Integer uid) {
		Uid = uid;
	}

}
