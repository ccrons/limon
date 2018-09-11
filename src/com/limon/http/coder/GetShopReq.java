package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopReq")
public class GetShopReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Cid")
	private Integer Cid;
	@XmlNode(name="Did")
	private Integer Did;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getCid() {
		return Cid;
	}
	public void setCid(Integer cid) {
		Cid = cid;
	}
	public Integer getDid() {
		return Did;
	}
	public void setDid(Integer did) {
		Did = did;
	}
}
