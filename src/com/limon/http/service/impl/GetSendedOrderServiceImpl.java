package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetSendedOrderDao;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.http.service.GetSendedOrderService;
import com.limon.store.model.StoreInfo;

@Service("GetSendedOrderService")
public class GetSendedOrderServiceImpl implements GetSendedOrderService{
	@Autowired
	private GetSendedOrderDao getSendedOrderDao;

	public List<ShopOrder> getSendedOrder(Map<String, Object> map) {
		return getSendedOrderDao.getSendedOrder(map);
	}

	public Integer getSendedOrderCount(Map<String, Object> map) {
		return getSendedOrderDao.getSendedOrderCount(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getSendedOrderDao.getProductListByOid(id);
	}

	public StoreInfo getStoreInfoById(Integer sid) {
		return getSendedOrderDao.getStoreInfoById(sid);
	}

	public List<ShopOrder> getSendedOrderSelf(Map<String, Object> map) {
		return getSendedOrderDao.getSendedOrderSelf(map);
	}

	public Integer getSendedOrderSelfCount(Map<String, Object> map) {
		return getSendedOrderDao.getSendedOrderSelfCount(map);
	}

}
