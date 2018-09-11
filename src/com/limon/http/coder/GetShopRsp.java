package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopRsp")
public class GetShopRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="RecordNum")
	private Integer RecordNum;
	@XmlNode(name="ShopList")
	private String ShopList;
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
	public Integer getRecordNum() {
		return RecordNum;
	}
	public void setRecordNum(Integer recordNum) {
		RecordNum = recordNum;
	}
	public String getShopList() {
		return ShopList;
	}
	public void setShopList(String shopList) {
		ShopList = shopList;
	}

}
