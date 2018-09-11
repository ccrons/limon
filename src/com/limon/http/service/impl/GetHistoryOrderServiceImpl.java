package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetHistoryOrderDao;
import com.limon.http.model.Address;
import com.limon.http.model.OrderHistory;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.GetHistoryOrderService;

@Service("GetHistoryOrderService")
public class GetHistoryOrderServiceImpl implements GetHistoryOrderService{
	@Autowired
	private GetHistoryOrderDao getHistoryOrderDao;

	public List<OrderHistory> getHistoryOrders(Map<String, Object> map) {
		return getHistoryOrderDao.getHistoryOrders(map);
	}

	public Integer getHistoryOrdersCount(Map<String, Object> map) {
		return getHistoryOrderDao.getHistoryOrdersCount(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getHistoryOrderDao.getProductListByOid(id);
	}

	public Address getAddressById(Integer aid) {
		return getHistoryOrderDao.getAddressById(aid);
	}
}
