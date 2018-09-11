package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="UpdatePwdRsp")
public class UpdatePwdRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;

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
