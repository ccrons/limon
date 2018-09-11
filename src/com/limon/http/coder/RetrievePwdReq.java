package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="RetrievePwdReq")
public class RetrievePwdReq {
	@XmlNode(name="Mobile")
	private String Mobile;
	@XmlNode(name="Passwd")
	private String Passwd;
	@XmlNode(name="CheckCode")
	private String CheckCode;
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getPasswd() {
		return Passwd;
	}
	public void setPasswd(String passwd) {
		Passwd = passwd;
	}
	public String getCheckCode() {
		return CheckCode;
	}
	public void setCheckCode(String checkCode) {
		CheckCode = checkCode;
	}
}
