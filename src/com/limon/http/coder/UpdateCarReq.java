package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="UpdateCarReq")
public class UpdateCarReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Sid")
	private Integer Sid;
	@XmlNode(name="Gid")
	private Integer Gid;
	@XmlNode(name="Num")
	private Integer Num;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public Integer getGid() {
		return Gid;
	}
	public void setGid(Integer gid) {
		Gid = gid;
	}
	public Integer getNum() {
		return Num;
	}
	public void setNum(Integer num) {
		Num = num;
	}
	
}
