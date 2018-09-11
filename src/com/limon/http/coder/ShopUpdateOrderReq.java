package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopUpdateOrderReq")
public class ShopUpdateOrderReq {
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Oid")
	private String Oid;
	@XmlNode(name="Status")
	private Integer Status;
	@XmlNode(name="Reason")
	private String Reason;
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getOid() {
		return Oid;
	}
	public void setOid(String oid) {
		Oid = oid;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
}
