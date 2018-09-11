package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="NmSelfShopRsp")
public class NmSelfShopRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="ShopInfo")
	private String ShopInfo;
	public Integer getResult() {
		return Result;
	}
	public void setResult(Integer result) {
		Result = result;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public String getShopInfo() {
		return ShopInfo;
	}
	public void setShopInfo(String shopInfo) {
		ShopInfo = shopInfo;
	}
}
