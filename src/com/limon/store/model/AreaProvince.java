package com.limon.store.model;

import java.io.Serializable;

public class AreaProvince  implements Serializable {
	private static final long serialVersionUID = -8661885908538941928L;
	private Integer id;
	private Integer provcode;
	private String provname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProvcode() {
		return provcode;
	}
	public void setProvcode(Integer provcode) {
		this.provcode = provcode;
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
}
