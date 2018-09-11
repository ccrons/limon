package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetDistrictRsp")
public class GetDistrictRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="RecordNum")
	private Integer RecordNum;
	@XmlNode(name="DistrictList")
	private String DistrictList;
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
	public String getDistrictList() {
		return DistrictList;
	}
	public void setDistrictList(String districtList) {
		DistrictList = districtList;
	}
}
