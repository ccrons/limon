package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopAddressRsp")
public class GetShopAddressRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="Address")
	private String Address;
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
}
