package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopGoodsBuyNumReq")
public class GetShopGoodsBuyNumReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Gid")
	private Integer Gid;
	@XmlNode(name="Sid")
	private Integer Sid;
	public Integer getGid() {
		return Gid;
	}
	public void setGid(Integer gid) {
		Gid = gid;
	}
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
