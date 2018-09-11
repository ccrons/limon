package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="ShopGoodsUpdateReq")
public class ShopGoodsUpdateReq {
	@XmlNode(name="Sid")
	private String Sid;
	@XmlNode(name="Spid")
	private String Spid;
	@XmlNode(name="Price")
	private String Price;
	@XmlNode(name="Num")
	private String Num;
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getSpid() {
		return Spid;
	}
	public void setSpid(String spid) {
		Spid = spid;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getNum() {
		return Num;
	}
	public void setNum(String num) {
		Num = num;
	}
	
}
