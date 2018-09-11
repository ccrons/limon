package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SetHeadImgReq")
public class SetHeadImgReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Img")
	private String Img;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public String getImg() {
		return Img;
	}
	public void setImg(String img) {
		Img = img;
	}
}
