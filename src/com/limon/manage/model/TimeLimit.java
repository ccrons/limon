package com.limon.manage.model;

import java.io.Serializable;
/**
 * 限时抢购表
 * 项目名称：project
 * 类名称：TimeLimit
 * 创建人：WN
 * 创建时间：2015年11月14日下午10:45:10
 * @version v1.0
 */
public class TimeLimit implements Serializable {

	private static final long serialVersionUID = 578137202184267963L;
	private Integer id;
	private Integer productid;
	private Integer tlnum;
	private Integer buynum;
	private Double tlprice;
	private String tltime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getTlnum() {
		return tlnum;
	}
	public void setTlnum(Integer tlnum) {
		this.tlnum = tlnum;
	}
	public Integer getBuynum() {
		return buynum;
	}
	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}
	public Double getTlprice() {
		return tlprice;
	}
	public void setTlprice(Double tlprice) {
		this.tlprice = tlprice;
	}
	public String getTltime() {
		return tltime;
	}
	public void setTltime(String tltime) {
		this.tltime = tltime;
	}
	
}
