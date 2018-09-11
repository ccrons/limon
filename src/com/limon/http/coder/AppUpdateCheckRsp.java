package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="AppUpdateCheckRsp")
public class AppUpdateCheckRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="Ver")
	private String Ver;
	@XmlNode(name="Url")
	private String Url;
	@XmlNode(name="Notes")
	private String Notes;
	@XmlNode(name="Type")
	private String Type;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	public Integer getResult() {
		return Result;
	}
	public void setResult(Integer result) {
		Result = result;
	}
	public String getVer() {
		return Ver;
	}
	public void setVer(String ver) {
		Ver = ver;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	
}
