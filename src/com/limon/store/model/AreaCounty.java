package com.limon.store.model;

import java.io.Serializable;

public class AreaCounty  implements Serializable {
	private static final long serialVersionUID = -8661885908538941928L;
	private Integer id;
	private Integer countycode;
	private Integer citycode;
	private String countyname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCountycode() {
		return countycode;
	}
	public void setCountycode(Integer countycode) {
		this.countycode = countycode;
	}
	public Integer getCitycode() {
		return citycode;
	}
	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}
	public String getCountyname() {
		return countyname;
	}
	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
}
