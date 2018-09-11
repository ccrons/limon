package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Address;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.OrderWReceive;

public interface GetWReceiveOrderService {
	public List<OrderWReceive> getWReceiveOrders(Map<String, Object> map);
	public Integer getWReceiveOrdersCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Address getAddressById(Integer aid);
}
