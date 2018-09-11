package com.limon.store.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wn
 *
 * 2015-7-2
 */
public class StoreProduct implements Serializable {
	private static final long serialVersionUID = -9005600950125662633L;
	private Integer id;
	private Integer storeid;
	private Integer productid;
	private Integer salenum;
	private Double saleprice;
	private Date createtime;
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
	public Integer getSalenum() {
		return salenum;
	}
	public void setSalenum(Integer salenum) {
		this.salenum = salenum;
	}
	public Double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
