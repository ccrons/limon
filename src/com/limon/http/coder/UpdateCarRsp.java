package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="UpdateCarRsp")
public class UpdateCarRsp {
	@XmlNode(name="Result")
	private String Result;
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
}
