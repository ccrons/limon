package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopLoginRsp")
public class ShopLoginRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="ShopInfo")
	private String ShopInfo;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	
	public String getShopInfo() {
		return ShopInfo;
	}
	public void setShopInfo(String shopinfo) {
		ShopInfo = shopinfo;
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
