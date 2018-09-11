package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetNearShopReq")
public class GetNearShopReq {
	@XmlNode(name="Uid")
	private String Uid;
	@XmlNode(name="Lat")
	private String Lat;
	@XmlNode(name="Lng")
	private String Lng;
	@XmlNode(name="Distance")
	private String Distance;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public String getLng() {
		return Lng;
	}
	public void setLng(String lng) {
		Lng = lng;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
}
