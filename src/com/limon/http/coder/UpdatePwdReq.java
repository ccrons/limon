package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="UpdatePwdReq")
public class UpdatePwdReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="OPasswd")
	private String OPasswd;
	@XmlNode(name="NPasswd")
	private String NPasswd;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
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
