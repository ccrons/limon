package com.limon.http.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gqf
 *
 * 2015-7-2
 */
public class Version implements Serializable {
	private static final long serialVersionUID = 4659940206933478868L;
	private Integer id;
	private String version;
	private String description;
	private String app_path;
	private String os;
	private Integer type;
	private Integer isopen;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApp_path() {
		return app_path;
	}
	public void setApp_path(String app_path) {
		this.app_path = app_path;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsopen() {
		return isopen;
	}
	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
