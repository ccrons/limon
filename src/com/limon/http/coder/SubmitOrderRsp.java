package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SubmitOrderRsp")
public class SubmitOrderRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="OrderInfo")
	private String OrderInfo;
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
	public String getOrderInfo() {
		return OrderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		OrderInfo = orderInfo;
	}
}
