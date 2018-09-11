package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetGoodsByTypeRsp")
public class GetGoodsByTypeRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="GoodsList")
	private String GoodsList;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="RecordNum")
	private String RecordNum;
	
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
	public String getGoodsList() {
		return GoodsList;
	}
	public void setGoodsList(String goodsList) {
		GoodsList = goodsList;
	}
	public String getRecordNum() {
		return RecordNum;
	}
	public void setRecordNum(String recordNum) {
		RecordNum = recordNum;
	}
}
