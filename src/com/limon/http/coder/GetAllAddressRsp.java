package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetAllAddressRsp")
public class GetAllAddressRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="AddressList")
	private String AddressList;
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
	public String getAddressList() {
		return AddressList;
	}
	public void setAddressList(String addressList) {
		AddressList = addressList;
	}
}
