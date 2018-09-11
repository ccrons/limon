package com.limon.manage.model;

import java.io.Serializable;
/**
 * 广告产品关联表
 * 项目名称：project
 * 类名称：StoreAdProduct
 * 创建人：WN
 * 创建时间：2015年11月14日下午10:45:10
 * @version v1.0
 */
public class StoreAdProduct implements Serializable {

	private static final long serialVersionUID = -556917883429926727L;
	private Integer id;
	private Integer adid;
	private Integer productid;
	private Double adprice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdid() {
		return adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Double getAdprice() {
		return adprice;
	}

	public void setAdprice(Double adprice) {
		this.adprice = adprice;
	}

}
