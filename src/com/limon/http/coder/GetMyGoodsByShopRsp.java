package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetMyGoodsByShopRsp")
public class GetMyGoodsByShopRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="RecordNum")
	private Integer RecordNum;
	@XmlNode(name="GoodsList")
	private String GoodsList;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	public Integer getResult() {
		return Result;
	}
	public void setResult(Integer result) {
		Result = result;
	}
	public Integer getRecordNum() {
		return RecordNum;
	}
	public void setRecordNum(Integer recordNum) {
		RecordNum = recordNum;
	}
	public String getGoodsList() {
		return GoodsList;
	}
	public void setGoodsList(String goodsList) {
		GoodsList = goodsList;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	
}
