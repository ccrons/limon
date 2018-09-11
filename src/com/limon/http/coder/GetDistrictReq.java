package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetDistrictReq")
public class GetDistrictReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Cid")
	private Integer Cityid;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getCityid() {
		return Cityid;
	}
	public void setCityid(Integer cityid) {
		Cityid = cityid;
	}
}
