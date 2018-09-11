package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetTotalOrderDao;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.http.service.GetTotalOrderService;
import com.limon.store.model.StoreInfo;

@Service("GetTotalOrderService")
public class GetTotalOrderServiceImpl implements GetTotalOrderService{
	@Autowired
	private GetTotalOrderDao getTotalOrderDao;

	public List<ShopOrder> getTodayOrder(Map<String, Object> map) {
		return getTotalOrderDao.getTodayOrder(map);
	}

	public Integer getTodayOrderCount(Map<String, Object> map) {
		return getTotalOrderDao.getTodayOrderCount(map);
	}

	public Double getTodayOrderSum(Map<String, Object> map) {
		return getTotalOrderDao.getTodayOrderSum(map);
	}

	public Integer getTotalOrderCount(Map<String, Object> map) {
		return getTotalOrderDao.getTotalOrderCount(map);
	}

	public Double getTotalOrderSum(Map<String, Object> map) {
		return getTotalOrderDao.getTotalOrderSum(map);
	}

	public List<OrderProductGet> getProductListByOid(Integer id) {
		return getTotalOrderDao.getProductListByOid(id);
	}

	public StoreInfo getStoreInfoById(Integer sid) {
		return getTotalOrderDao.getStoreInfoById(sid);
	}

	public List<ShopOrder> getTodayOrderSelf(Map<String, Object> map) {
		return getTotalOrderDao.getTodayOrderSelf(map);
	}

	public Integer getTodayOrderSelfCount(Map<String, Object> map) {
		return getTotalOrderDao.getTodayOrderSelfCount(map);
	}

}
