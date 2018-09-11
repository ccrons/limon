package com.limon.wx.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public class WxConfig implements Serializable {
	private static final long serialVersionUID = 9040724408994442379L;
	private Integer id;
	private String cname;
	private String cvalue;
	private long expires_in;
	private Date createtime;
	private String appid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCvalue() {
		return cvalue;
	}
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
}
