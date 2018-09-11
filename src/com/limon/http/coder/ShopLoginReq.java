package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopLoginReq")
public class ShopLoginReq {
	@XmlNode(name="Username")
	private String Username;
	@XmlNode(name="Passwd")
	private String Passwd;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPasswd() {
		return Passwd;
	}
	public void setPasswd(String passwd) {
		Passwd = passwd;
	}
}
