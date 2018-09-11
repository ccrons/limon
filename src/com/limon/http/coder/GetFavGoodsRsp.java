package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetFavGoodsRsp")
public class GetFavGoodsRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="RecordNum")
	private Integer RecordNum;
	@XmlNode(name="GoodsList")
	private String GoodsList;
	public Integer getRecordNum() {
		return RecordNum;
	}
	public void setRecordNum(Integer recordNum) {
		RecordNum = recordNum;
	}
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
	public String getGoodsList() {
		return GoodsList;
	}
	public void setGoodsList(String goodsList) {
		GoodsList = goodsList;
	}
	
}
