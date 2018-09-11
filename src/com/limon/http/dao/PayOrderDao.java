package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface PayOrderDao {
	public Integer updateOrderStatus(Map<String,Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Integer updateStoreSaleNum(Map<String,Object> map);
	public Order getOrderbyOid(Integer id);
}