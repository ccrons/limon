package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopAdRsp")
public class GetShopAdRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="AdList")
	private String AdList;
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
	public String getAdList() {
		return AdList;
	}
	public void setAdList(String adList) {
		AdList = adList;
	}
	
}
