package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetSendedOrderReq")
public class GetSendedOrderReq {
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Page")
	private String Page;
	@XmlNode(name="PageNum")
	private String PageNum;
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
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
