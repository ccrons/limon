package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetPerInfoRsp")
public class GetPerInfoRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="PerInfo")
	private String PerInfo;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public String getPerInfo() {
		return PerInfo;
	}
	public void setPerInfo(String perInfo) {
		PerInfo = perInfo;
	}
}
