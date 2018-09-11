package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopInfoRsp")
public class GetShopInfoRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="ShopInfo")
	private String ShopInfo;
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
	public String getShopInfo() {
		return ShopInfo;
	}
	public void setShopInfo(String shopInfo) {
		ShopInfo = shopInfo;
	}
	
}
