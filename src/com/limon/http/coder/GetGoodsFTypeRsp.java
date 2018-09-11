package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetGoodsFTypeRsp")
public class GetGoodsFTypeRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="TypeList")
	private String TypeList;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	public Integer getResult() {
		return Result;
	}
	public void setResult(Integer result) {
		Result = result;
	}
	public String getTypeList() {
		return TypeList;
	}
	public void setTypeList(String typeList) {
		TypeList = typeList;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
}
