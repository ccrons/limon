package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="LoginRsp")
public class LoginRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="UserInfo")
	private String UserInfo;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	
	public String getUserInfo() {
		return UserInfo;
	}
	public void setUserInfo(String userInfo) {
		UserInfo = userInfo;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public Integer getResult() {
		return Result;
	}
	public void setResult(Integer result) {
		Result = result;
	}
	
}
