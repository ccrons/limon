package com.limon.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 招聘管理
 * @author WN
 *
 */
public class Recruit implements Serializable {
	private static final long serialVersionUID = -672979084855691217L;
	private Integer id;
	private String retitle;
	private String recontent;
	private Date createtime;
	private Integer isup;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRetitle() {
		return retitle;
	}
	public void setRetitle(String retitle) {
		this.retitle = retitle;
	}
	public String getRecontent() {
		return recontent;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getIsup() {
		return isup;
	}
	public void setIsup(Integer isup) {
		this.isup = isup;
	}
}
