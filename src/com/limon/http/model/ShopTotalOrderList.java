package com.limon.http.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"totaltoday","todaynum","totalall","totalnum","orderlist"})
public class ShopTotalOrderList {
	@JSONField(name="totaltoday")
	private String totaltoday;
	@JSONField(name="totalall")
	private String totalall;
	@JSONField(name="todaynum")
	private Integer todaynum;
	@JSONField(name="totalnum")
	private Integer totalnum;
	@JSONField(name="orderlist")
	private List<ShopOrder> list;
	
	public String getTotaltoday() {
		return totaltoday;
	}

	public void setTotaltoday(String totaltoday) {
		this.totaltoday = totaltoday;
	}

	public String getTotalall() {
		return totalall;
	}

	public void setTotalall(String totalall) {
		this.totalall = totalall;
	}

	public Integer getTodaynum() {
		return todaynum;
	}

	public void setTodaynum(Integer todaynum) {
		this.todaynum = todaynum;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public List<ShopOrder> getList() {
		return list;
	}

	public void setList(List<ShopOrder> list) {
		this.list = list;
	}
}
