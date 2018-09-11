package com.limon.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gqf
 *
 * 2015-7-2
 */
public class SysPush implements Serializable {
	private static final long serialVersionUID = 4659940206933478868L;
	private Integer id;
	private String content;
	private Integer ptype;
	private String alias;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getPtype() {
		return ptype;
	}
	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
