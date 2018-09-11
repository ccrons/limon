package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetAllGoodsByShopReq")
public class GetAllGoodsByShopReq {
	@XmlNode(name="Sid")
	private Integer Sid;
	@XmlNode(name="Page")
	private Integer Page;
	@XmlNode(name="PageNum")
	private String PageNum;
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public Integer getPage() {
		return Page;
	}
	public void setPage(Integer page) {
		Page = page;
	}
	public String getPageNum() {
		return PageNum;
	}
	public void setPageNum(String pageNum) {
		PageNum = pageNum;
	}
}
