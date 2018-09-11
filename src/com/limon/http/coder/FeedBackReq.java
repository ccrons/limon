package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="FeedBackReq")
public class FeedBackReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Content")
	private String Content;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
