package com.limon.http.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetWPayOrderDao;
import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.GetWPayOrderService;
import com.limon.manage.model.StoreAdProductInfo;

@Service("GetWPayOrderService")
public class GetWPayOrderServiceImpl implements GetWPayOrderService{
	@Autowired
	private GetWPayOrderDao getWPayOrderDao;

	public List<Order> getWPayOrders(Map<String, Object> map) {
		return getWPayOrderDao.getWPayOrders(map);
	}

	public Integer getWPayOrdersCount(Map<String, Object> map) {
		return getWPayOrderDao.getWPayOrdersCount(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getWPayOrderDao.getProductListByOid(id);
	}

	public Address getAddressById(Integer aid) {
		return getWPayOrderDao.getAddressById(aid);
	}

	@Override
	public List<StoreAdProductInfo> getTLimit(Integer sid, String tltime) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sid", sid);
		map.put("tltime", tltime);
		return getWPayOrderDao.getTLimit(sid, tltime);
	}

	@Override
	public List<String> getTimeLimitProduct(Integer pid) {
		return getWPayOrderDao.getTimeLimitProduct(pid);
	}
	
}
