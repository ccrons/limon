package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetWReceiveOrderDao;
import com.limon.http.model.Address;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.OrderWReceive;
import com.limon.http.service.GetWReceiveOrderService;

@Service("GetWReceiveOrderService")
public class GetWReceiveOrderServiceImpl implements GetWReceiveOrderService{
	@Autowired
	private GetWReceiveOrderDao getWReceiveOrderDao;
	public List<OrderWReceive> getWReceiveOrders(Map<String, Object> map) {
		return getWReceiveOrderDao.getWReceiveOrders(map);
	}
	public Integer getWReceiveOrdersCount(Map<String, Object> map) {
		return getWReceiveOrderDao.getWReceiveOrdersCount(map);
	}
	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getWReceiveOrderDao.getProductListByOid(id);
	}
	public Address getAddressById(Integer aid) {
		return getWReceiveOrderDao.getAddressById(aid);
	}

}
