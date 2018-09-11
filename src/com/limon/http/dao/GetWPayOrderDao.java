package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.StoreAdProductInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetWPayOrderDao {
	public List<Order> getWPayOrders(Map<String, Object> map);
	public Integer getWPayOrdersCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Address getAddressById(Integer aid);
	public List<StoreAdProductInfo> getTLimit(Integer sid, String tltime);
	List<String> getTimeLimitProduct(Integer pid);
}