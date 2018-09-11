package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SetShopAddressRsp")
public class SetShopAddressRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="AddressInfo")
	private String AddressInfo;
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
	public String getAddressInfo() {
		return AddressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		AddressInfo = addressInfo;
	}
	
}
