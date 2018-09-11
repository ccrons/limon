package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="SetShopAddressReq")
public class SetShopAddressReq {
	@XmlNode(name="Uid")
	private Integer Uid;
	@XmlNode(name="Sid")
	private Integer Sid;
	@XmlNode(name="Name")
	private String Name;
	@XmlNode(name="Tel")
	private String Tel;
	@XmlNode(name="Provice")
	private String Provice;
	@XmlNode(name="City")
	private String City;
	@XmlNode(name="District")
	private String District;
	@XmlNode(name="Address")
	private String Address;
	public Integer getUid() {
		return Uid;
	}
	public void setUid(Integer uid) {
		Uid = uid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getProvice() {
		return Provice;
	}
	public void setProvice(String provice) {
		Provice = provice;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}

}
