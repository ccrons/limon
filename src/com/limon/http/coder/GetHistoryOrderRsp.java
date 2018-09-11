package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetHistoryOrderRsp")
public class GetHistoryOrderRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="RecordNum")
	private Integer RecordNum;
	@XmlNode(name="OrderList")
	private String OrderList;
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
	public String getOrderList() {
		return OrderList;
	}
	public void setOrderList(String orderList) {
		OrderList = orderList;
	}
	
}
