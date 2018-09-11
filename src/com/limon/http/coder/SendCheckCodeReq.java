package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SendCheckCodeReq")
public class SendCheckCodeReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Mobile")
	private String Mobile;
	@XmlNode(name="CheckCode")
	private String CheckCode;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getCheckCode() {
		return CheckCode;
	}
	public void setCheckCode(String checkCode) {
		CheckCode = checkCode;
	}
	
}
