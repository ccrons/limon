package com.limon.http.dao;

import java.util.Map;

import com.limon.http.model.Order;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface UpdateOrderDao {
	public Integer updateOrderStatus(Map<String,Object> map);
	public Order getOrderById(Integer oid);
}