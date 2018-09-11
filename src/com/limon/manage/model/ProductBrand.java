package com.limon.manage.model;

import java.io.Serializable;

/**
 * @author wn
 *
 * 2015-7-2
 */
public class ProductBrand implements Serializable {
	private static final long serialVersionUID = 4659940206933478868L;
	private Integer id;
	private String brandname;
	private String initial;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	
}
