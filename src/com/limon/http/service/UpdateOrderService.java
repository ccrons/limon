package com.limon.http.service;

import java.util.Map;

import com.limon.http.model.Order;

public interface UpdateOrderService {
	public Integer updateOrderStatus(Map<String,Object> map);
	public Order getOrderById(Integer oid);
}
