package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="AppUpdateCheckReq")
public class AppUpdateCheckReq {
	@XmlNode(name="OS")
	private String OS;
	@XmlNode(name="Ver")
	private String Ver;
	public String getOS() {
		return OS;
	}
	public void setOS(String os) {
		OS = os;
	}
	public String getVer() {
		return Ver;
	}
	public void setVer(String ver) {
		Ver = ver;
	}
	
}
