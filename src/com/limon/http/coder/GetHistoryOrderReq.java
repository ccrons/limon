package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetHistoryOrderReq")
public class GetHistoryOrderReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Page")
	private String Page;
	@XmlNode(name="PageNum")
	private String PageNum;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getPage() {
		return Page;
	}
	public void setPage(String page) {
		Page = page;
	}
	public String getPageNum() {
		return PageNum;
	}
	public void setPageNum(String pageNum) {
		PageNum = pageNum;
	}
}
