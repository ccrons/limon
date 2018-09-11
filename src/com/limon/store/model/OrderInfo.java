package com.limon.store.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = -6032698412521376263L;
	
	private Integer id;
	private String orderno;
	private Integer paytype;
	private String payno;
	private Integer status;
	private Integer userid;
	private String username;
	private String nickname;
	private String return_id;
	private Double orderprice;
	private Double sendprice;
	private String remark;
	private Integer addrid;
	private String province;
	private String city;
	private String county;
	private String address;
	private String contact;
	private String mobile;
	private Date createtime;
	private Integer isdel;
	private String reason;
	private String storename;
	private String goodscode;
	private String sendtime;
	private String acceptname;
	private String accepttel;
	private Integer isluck;
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	public String getAcceptname() {
		return acceptname;
	}
	public void setAcceptname(String acceptname) {
		this.acceptname = acceptname;
	}
	public String getAccepttel() {
		return accepttel;
	}
	public void setAccepttel(String accepttel) {
		this.accepttel = accepttel;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Integer getPaytype() {
		return paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	public String getPayno() {
		return payno;
	}
	public void setPayno(String payno) {
		this.payno = payno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Double getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(Double orderprice) {
		this.orderprice = orderprice;
	}
	public Double getSendprice() {
		return sendprice;
	}
	public void setSendprice(Double sendprice) {
		this.sendprice = sendprice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAddrid() {
		return addrid;
	}
	public void setAddrid(Integer addrid) {
		this.addrid = addrid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getIsdel() {
		return isdel;
	}
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getReturn_id() {
		return return_id;
	}
	public void setReturn_id(String return_id) {
		this.return_id = return_id;
	}
	
}
