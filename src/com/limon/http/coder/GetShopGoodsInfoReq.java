package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopGoodsInfoReq")
public class GetShopGoodsInfoReq {
	@XmlNode(name="Gid")
	private Integer Gid;
	public Integer getGid() {
		return Gid;
	}
	public void setGid(Integer gid) {
		Gid = gid;
	}
}
