package com.limon.manage.model;

import java.util.Date;

public class StoreCash {
	private Integer id;
	private Integer storeid;
	private Date createtime;
	private Double cashnum;
	private Integer cashtype;
	private String cashaccount;
	private String cashtime;
	private String storename;
	private Integer cashstatus;
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public Integer getCashstatus() {
		return cashstatus;
	}
	public void setCashstatus(Integer cashstatus) {
		this.cashstatus = cashstatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Double getCashnum() {
		return cashnum;
	}
	public void setCashnum(Double cashnum) {
		this.cashnum = cashnum;
	}
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
	public String getCashtime() {
		return cashtime;
	}
	public void setCashtime(String cashtime) {
		this.cashtime = cashtime;
	}
}
