package com.limon.store.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public class StoreInfo implements Serializable {
	private static final long serialVersionUID = 9040724408994442379L;
	private Integer id;
	private String storename;
	private String username;
	private String password;
	private String contact;
	private String mobile;
	private String storemobile;
	private Date createtime;
	private String bussiness_stime;
	private String bussiness_etime;
	private String send_stime;
	private String send_etime;
	private Double startprice;
	private Double sendprice;
	private Integer distance;
	private Integer provincecode;
	private String provname;
	private Integer citycode;
	private String cityname;
	private Integer countycode;
	private String countyname;
	private String address;
	private Integer isself;
	private Integer isopen;
	private Integer isbussiness;
	private String lat;//经度
	private String lng;//纬度
	private String rolename;
	private Integer roleid;
	
	private Double allorderprice;
	private Double allsendprice;
	private Double ptlirun;
	private Integer cashtype;
	private String cashaccount;
	
	public Integer getCashtype() {
		return cashtype;
	}
	public void setCashtype(Integer cashtype) {
		this.cashtype = cashtype;
	}
	public String getCashaccount() {
		return cashaccount;
	}
	public void setCashaccount(String cashaccount) {
		this.cashaccount = cashaccount;
	}
	public Double getPtlirun() {
		return ptlirun;
	}
	public void setPtlirun(Double ptlirun) {
		this.ptlirun = ptlirun;
	}
	private Double allprice;
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Double getStartprice() {
		return startprice;
	}
	public void setStartprice(Double startprice) {
		this.startprice = startprice;
	}
	public Double getSendprice() {
		return sendprice;
	}
	public void setSendprice(Double sendprice) {
		this.sendprice = sendprice;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(Integer provincecode) {
		this.provincecode = provincecode;
	}
	public Integer getCitycode() {
		return citycode;
	}
	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}
	public Integer getCountycode() {
		return countycode;
	}
	public void setCountycode(Integer countycode) {
		this.countycode = countycode;
	}
	public Integer getIsself() {
		return isself;
	}
	public void setIsself(Integer isself) {
		this.isself = isself;
	}
	public Integer getIsopen() {
		return isopen;
	}
	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}
	public Integer getIsbussiness() {
		return isbussiness;
	}
	public void setIsbussiness(Integer isbussiness) {
		this.isbussiness = isbussiness;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getBussiness_stime() {
		return bussiness_stime;
	}
	public void setBussiness_stime(String bussiness_stime) {
		this.bussiness_stime = bussiness_stime;
	}
	public String getBussiness_etime() {
		return bussiness_etime;
	}
	public void setBussiness_etime(String bussiness_etime) {
		this.bussiness_etime = bussiness_etime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCountyname() {
		return countyname;
	}
	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	public Double getAllprice() {
		return allprice;
	}
	public void setAllprice(Double allprice) {
		this.allprice = allprice;
	}
	public String getStoremobile() {
		return storemobile;
	}
	public void setStoremobile(String storemobile) {
		this.storemobile = storemobile;
	}
	public String getSend_stime() {
		return send_stime;
	}
	public void setSend_stime(String send_stime) {
		this.send_stime = send_stime;
	}
	public String getSend_etime() {
		return send_etime;
	}
	public void setSend_etime(String send_etime) {
		this.send_etime = send_etime;
	}
	public Double getAllorderprice() {
		return allorderprice;
	}
	public void setAllorderprice(Double allorderprice) {
		this.allorderprice = allorderprice;
	}
	public Double getAllsendprice() {
		return allsendprice;
	}
	public void setAllsendprice(Double allsendprice) {
		this.allsendprice = allsendprice;
	}
		
}
