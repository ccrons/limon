package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.PayOrderDao;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.PayOrderService;

@Service("PayOrderService")
public class PayOrderServiceImpl implements PayOrderService{
	@Autowired
	private PayOrderDao payOrderDao;

	public Integer updateOrderStatus(Map<String, Object> map) {
		return payOrderDao.updateOrderStatus(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return payOrderDao.getProductListByOid(id);
	}

	public Integer updateStoreSaleNum(Map<String, Object> map) {
		return payOrderDao.updateStoreSaleNum(map);
	}

	public Order getOrderbyOid(Integer id) {
		return payOrderDao.getOrderbyOid(id);
	}

}
