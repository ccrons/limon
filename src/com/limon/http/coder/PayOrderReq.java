package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="PayOrderReq")
public class PayOrderReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Oid")
	private String Oid;
	@XmlNode(name="PayNo")
	private String PayNo;
	@XmlNode(name="PayType")
	private String PayType;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getOid() {
		return Oid;
	}
	public void setOid(String oid) {
		Oid = oid;
	}
	public String getPayNo() {
		return PayNo;
	}
	public void setPayNo(String payNo) {
		PayNo = payNo;
	}
	public String getPayType() {
		return PayType;
	}
	public void setPayType(String payType) {
		PayType = payType;
	}
}
