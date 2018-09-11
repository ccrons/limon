package com.limon.store.model;

import java.io.Serializable;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public class StoreDiscount implements Serializable {
	private static final long serialVersionUID = 9040724408994442379L;
	private Integer id;
	private Integer storeid;
	private Integer productid;
	private String productname;
	private String discount;
	private Double saleprice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}
}
