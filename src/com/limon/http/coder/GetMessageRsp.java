package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetMessageRsp")
public class GetMessageRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="MessageList")
	private String MessageList;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="RecordNum")
	private String RecordNum;
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
	public String getMessageList() {
		return MessageList;
	}
	public void setMessageList(String messageList) {
		MessageList = messageList;
	}
	public String getRecordNum() {
		return RecordNum;
	}
	public void setRecordNum(String recordNum) {
		RecordNum = recordNum;
	}
}
