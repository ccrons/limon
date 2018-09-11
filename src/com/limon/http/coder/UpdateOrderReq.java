package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="UpdateOrderReq")
public class UpdateOrderReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Oid")
	private String Oid;
	@XmlNode(name="Status")
	private Integer Status;
	@XmlNode(name="Reason")
	private String Reason;
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
