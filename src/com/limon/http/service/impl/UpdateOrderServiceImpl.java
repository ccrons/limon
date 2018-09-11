package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.UpdateOrderDao;
import com.limon.http.model.Order;
import com.limon.http.service.UpdateOrderService;

@Service("UpdateOrderService")
public class UpdateOrderServiceImpl implements UpdateOrderService{
	@Autowired
	private UpdateOrderDao updateOrderDao;

	public Integer updateOrderStatus(Map<String, Object> map) {
		return updateOrderDao.updateOrderStatus(map);
	}

	@Override
	public Order getOrderById(Integer oid) {
		return updateOrderDao.getOrderById(oid);
	}
	
}
