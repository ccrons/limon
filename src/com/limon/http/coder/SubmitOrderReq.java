package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SubmitOrderReq")
public class SubmitOrderReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Sid")
	private Integer Sid;
	@XmlNode(name="Aid")
	private Integer Aid;
	@XmlNode(name="OrderPrice")
	private String OrderPrice;
	@XmlNode(name="SendPrice")
	private String SendPrice;
	@XmlNode(name="ReMark")
	private String ReMark;
	@XmlNode(name="SendTime")
	private String SendTime;
	@XmlNode(name="GoodsList")
	private String GoodsList;
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
	public Integer getAid() {
		return Aid;
	}
	public void setAid(Integer aid) {
		Aid = aid;
	}
	public String getGoodsList() {
		return GoodsList;
	}
	public void setGoodsList(String goodsList) {
		GoodsList = goodsList;
	}
	public String getOrderPrice() {
		return OrderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		OrderPrice = orderPrice;
	}
	public String getSendPrice() {
		return SendPrice;
	}
	public void setSendPrice(String sendPrice) {
		SendPrice = sendPrice;
	}
	public String getReMark() {
		return ReMark;
	}
	public void setReMark(String reMark) {
		ReMark = reMark;
	}
	public String getSendTime() {
		return SendTime;
	}
	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}
}
