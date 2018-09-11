package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetWSendOrderDao;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.http.service.GetWSendOrderService;
import com.limon.store.model.StoreInfo;

@Service("GetWSendOrderService")
public class GetWSendOrderServiceImpl implements GetWSendOrderService{
	@Autowired
	private GetWSendOrderDao getWSendOrderDao;

	public List<ShopOrder> getWSendOrder(Map<String, Object> map) {
		return getWSendOrderDao.getWSendOrder(map);
	}

	public Integer getWSendOrderCount(Map<String, Object> map) {
		return getWSendOrderDao.getWSendOrderCount(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getWSendOrderDao.getProductListByOid(id);
	}

	public StoreInfo getStoreInfoById(Integer sid) {
		return getWSendOrderDao.getStoreInfoById(sid);
	}

	public List<ShopOrder> getWSendOrderSelf(Map<String, Object> map) {
		return getWSendOrderDao.getWSendOrderSelf(map);
	}

	public Integer getWSendOrderSelfCount(Map<String, Object> map) {
		return getWSendOrderDao.getWSendOrderSelfCount(map);
	}

}
