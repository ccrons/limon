package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.SubmitOrderDao;
import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.service.SubmitOrderService;

@Service("SubmitOrderService")
public class SubmitOrderServiceImpl implements SubmitOrderService{
	@Autowired
	private SubmitOrderDao submitOrderDao;

	public Integer saveOrder(Map<String, Object> map) {
		return submitOrderDao.saveOrder(map);
	}

	public Integer saveOrderProduct(Map<String, Object> map) {
		return submitOrderDao.saveOrderProduct(map);
	}

	@Override
	public Integer updateProductNum(Map<String, Object> map) {
		return submitOrderDao.updateProductNum(map);
	}

	@Override
	public Integer updateTLimitNum(Map<String, Object> map) {
		return submitOrderDao.updateTLimitNum(map);
	}

	@Override
	public Integer getProductNum(Map<String, Object> map) {
		return submitOrderDao.getProductNum(map);
	}

	@Override
	public Integer getTLimitNum(Map<String, Object> map) {
		return submitOrderDao.getTLimitNum(map);
	}

	@Override
	public Address getAddressById(Integer aid) {
		return submitOrderDao.getAddressById(aid);
	}

	@Override
	public Order getOrderbyId(Integer id) {
		return submitOrderDao.getOrderbyId(id);
	}

}
