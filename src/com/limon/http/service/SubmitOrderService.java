package com.limon.http.service;

import java.util.Map;

import com.limon.http.model.Address;
import com.limon.http.model.Order;

public interface SubmitOrderService {
	public Integer saveOrder(Map<String,Object> map);
	public Integer saveOrderProduct(Map<String,Object> map);
	public Integer updateProductNum(Map<String,Object> map);
	public Integer updateTLimitNum(Map<String,Object> map);
	public Integer getProductNum(Map<String,Object> map);
	public Integer getTLimitNum(Map<String,Object> map);
	public Address getAddressById(Integer aid);
	public Order getOrderbyId(Integer id);
}
