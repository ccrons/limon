package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Address;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.OrderWReceive;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetWReceiveOrderDao {
	public List<OrderWReceive> getWReceiveOrders(Map<String, Object> map);
	public Integer getWReceiveOrdersCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Address getAddressById(Integer aid);
}