package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopUpdatePwdReq")
public class ShopUpdatePwdReq {
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="OPasswd")
	private String OPasswd;
	@XmlNode(name="NPasswd")
	private String NPasswd;
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getOPasswd() {
		return OPasswd;
	}
	public void setOPasswd(String passwd) {
		OPasswd = passwd;
	}
	public String getNPasswd() {
		return NPasswd;
	}
	public void setNPasswd(String passwd) {
		NPasswd = passwd;
	}
	
}
