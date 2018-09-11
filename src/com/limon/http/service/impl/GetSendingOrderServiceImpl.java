package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetSendingOrderDao;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.http.service.GetSendingOrderService;
import com.limon.store.model.StoreInfo;

@Service("GetSendingOrderService")
public class GetSendingOrderServiceImpl implements GetSendingOrderService{
	@Autowired
	private GetSendingOrderDao getSendingOrderDao;

	public List<ShopOrder> getSendingOrder(Map<String, Object> map) {
		return getSendingOrderDao.getSendingOrder(map);
	}

	public Integer getSendingOrderCount(Map<String, Object> map) {
		return getSendingOrderDao.getSendingOrderCount(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getSendingOrderDao.getProductListByOid(id);
	}

	public StoreInfo getStoreInfoById(Integer sid) {
		return getSendingOrderDao.getStoreInfoById(sid);
	}

	public List<ShopOrder> getSendingOrderSelf(Map<String, Object> map) {
		return getSendingOrderDao.getSendingOrderSelf(map);
	}

	public Integer getSendingOrderSelfCount(Map<String, Object> map) {
		return getSendingOrderDao.getSendingOrderSelfCount(map);
	}

}
