package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="RegisterReq")
public class RegisterReq {
	@XmlNode(name="Tel")
	private String Tel;
	@XmlNode(name="PassWd")
	private String PassWd;
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getPassWd() {
		return PassWd;
	}
	public void setPassWd(String passWd) {
		PassWd = passWd;
	}
	
}
