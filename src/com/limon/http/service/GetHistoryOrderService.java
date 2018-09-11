package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Address;
import com.limon.http.model.OrderHistory;
import com.limon.http.model.OrderProductGet;

public interface GetHistoryOrderService {
	public List<OrderHistory> getHistoryOrders(Map<String, Object> map);
	public Integer getHistoryOrdersCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Address getAddressById(Integer aid);
}
