package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.ShopUpdateOrderDao;
import com.limon.http.model.Order;
import com.limon.http.service.ShopUpdateOrderService;

@Service("ShopUpdateOrderService")
public class ShopUpdateOrderServiceImpl implements ShopUpdateOrderService{
	@Autowired
	private ShopUpdateOrderDao shopUpdateOrderDao;

	public Integer shopupdateOrderStatus(Map<String, Object> map) {
		return shopUpdateOrderDao.shopupdateOrderStatus(map);
	}

	@Override
	public Order getOrderbyId(Integer id) {
		return shopUpdateOrderDao.getOrderbyId(id);
	}
}
