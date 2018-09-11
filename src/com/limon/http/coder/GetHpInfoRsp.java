package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetHpInfoRsp")
public class GetHpInfoRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="RecruitInfo")
	private String RecruitInfo;
	@XmlNode(name="ZeroInfo")
	private String ZeroInfo;
	@XmlNode(name="TimeLimitInfo")
	private String TimeLimitInfo;
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
	public String getRecruitInfo() {
		return RecruitInfo;
	}
	public void setRecruitInfo(String recruitInfo) {
		RecruitInfo = recruitInfo;
	}
	public String getZeroInfo() {
		return ZeroInfo;
	}
	public void setZeroInfo(String zeroInfo) {
		ZeroInfo = zeroInfo;
	}
	public String getTimeLimitInfo() {
		return TimeLimitInfo;
	}
	public void setTimeLimitInfo(String timeLimitInfo) {
		TimeLimitInfo = timeLimitInfo;
	}
}
