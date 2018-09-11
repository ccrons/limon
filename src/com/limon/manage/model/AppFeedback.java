package com.limon.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wn
 *
 * 2015-7-2
 */
public class AppFeedback implements Serializable {
	private static final long serialVersionUID = -9054806568563891311L;
	private Integer id;
	private Integer userid;
	private String username;
	private String content;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
