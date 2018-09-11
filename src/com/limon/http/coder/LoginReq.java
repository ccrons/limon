package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="LoginReq")
public class LoginReq {
	@XmlNode(name="Username")
	private String Username;
	@XmlNode(name="CheckCode")
	private String CheckCode;
	@XmlNode(name="IMEI")
	private String IMEI;
	@XmlNode(name="IMSI")
	private String IMSI;
	@XmlNode(name="OS")
	private String OS;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getIMSI() {
		return IMSI;
	}
	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getCheckCode() {
		return CheckCode;
	}
	public void setCheckCode(String checkCode) {
		CheckCode = checkCode;
	}
	
}
