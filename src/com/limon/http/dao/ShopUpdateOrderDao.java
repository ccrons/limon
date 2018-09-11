package com.limon.http.dao;

import java.util.Map;

import com.limon.http.model.Order;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface ShopUpdateOrderDao {
	public Integer shopupdateOrderStatus(Map<String,Object> map);

	public Order getOrderbyId(Integer id);
}