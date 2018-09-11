package com.limon.store.model;

import java.io.Serializable;

public class AreaCity  implements Serializable {
	private static final long serialVersionUID = -8661885908538941928L;
	private Integer id;
	private Integer citycode;
	private Integer provcode;
	private String cityname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCitycode() {
		return citycode;
	}
	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}
	public Integer getProvcode() {
		return provcode;
	}
	public void setProvcode(Integer provcode) {
		this.provcode = provcode;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
}
