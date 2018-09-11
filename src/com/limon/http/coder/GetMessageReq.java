package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetMessageReq")
public class GetMessageReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Page")
	private Integer Page;
	@XmlNode(name="PageNum")
	private Integer PageNum;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getPage() {
		return Page;
	}
	public void setPage(Integer page) {
		Page = page;
	}
	public Integer getPageNum() {
		return PageNum;
	}
	public void setPageNum(Integer pageNum) {
		PageNum = pageNum;
	}
}
