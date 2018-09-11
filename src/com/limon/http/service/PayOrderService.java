package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;

public interface PayOrderService {
	public Integer updateOrderStatus(Map<String,Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Integer updateStoreSaleNum(Map<String,Object> map);
	public Order getOrderbyOid(Integer id);
}
