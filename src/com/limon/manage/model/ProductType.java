package com.limon.manage.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author wn
 *
 * 2015-7-2
 */
public class ProductType implements Serializable {
	private static final long serialVersionUID = -1751424213147775565L;
	
	private Integer id;
	private String typename;
	private Integer pid;
	private Integer sort;
	private List<ProductType> childproductpypelist;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<ProductType> getChildproductpypelist() {
		return childproductpypelist;
	}
	public void setChildproductpypelist(List<ProductType> childproductpypelist) {
		this.childproductpypelist = childproductpypelist;
	}
	
}
