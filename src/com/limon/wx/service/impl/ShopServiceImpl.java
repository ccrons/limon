package com.limon.wx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.store.model.StoreInfo;
import com.limon.wx.dao.ShopDao;
import com.limon.wx.service.ShopService;
@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
	@Override
	public ProductInfo getGoosById(Integer pid){
		return shopDao.getGoosById(pid);
	}

	@Override
	public List<Order> getWPayOrder(Map<String, Object> map) {
		return shopDao.getWPayOrder(map);
	}

	@Override
	public List<Order> getWReceiveOrder(Map<String, Object> map) {
		return shopDao.getWReceiveOrder(map);
	}

	@Override
	public List<Order> getWSendOrder(Map<String, Object> map) {
		return shopDao.getWSendOrder(map);
	}

	@Override
	public List<Order> getOverOrder(Map<String, Object> map) {
		return shopDao.getOverOrder(map);
	}

	@Override
	public List<Order> getCancelOrder(Map<String, Object> map) {
		return shopDao.getCancelOrder(map);
	}

	@Override
	public List<Order> getAllOrder(Map<String, Object> map) {
		return shopDao.getAllOrder(map);
	}

	@Override
	public List<OrderProductGet> getProductListByOid(Integer id) {
		return shopDao.getProductListByOid(id);
	}

	@Override
	public Address getAddressById(Integer aid) {
		return shopDao.getAddressById(aid);
	}

	@Override
	public List<StoreAdProductInfo> getTLimit(Integer sid, String tltime) {
		return shopDao.getTLimit(sid, tltime);
	}

	@Override
	public List<String> getTimeLimitProduct(Integer pid) {
		return shopDao.getTimeLimitProduct(pid);
	}

	@Override
	public StoreInfo getStoreInfoById(Integer sid) {
		return shopDao.getStoreInfoById(sid);
	}

	@Override
	public Integer getWPayOrderCount(Map<String, Object> map) {
		return shopDao.getWPayOrderCount(map);
	}

	@Override
	public Integer getWReceiveOrderCount(Map<String, Object> map) {
		return shopDao.getWReceiveOrderCount(map);
	}

	@Override
	public Integer getWSendOrderCount(Map<String, Object> map) {
		return shopDao.getWSendOrderCount(map);
	}

	@Override
	public Integer getOverOrderCount(Map<String, Object> map) {
		return shopDao.getOverOrderCount(map);
	}

	@Override
	public Integer getCancelOrderCount(Map<String, Object> map) {
		return shopDao.getCancelOrderCount(map);
	}

	@Override
	public Integer getAllOrderCount(Map<String, Object> map) {
		return shopDao.getAllOrderCount(map);
	}

	@Override
	public Order getOrderById(Integer oid) {
		return shopDao.getOrderById(oid);
	}

	@Override
	public Order getOrderByOrderno(String orderno) {
		return shopDao.getOrderByOrderno(orderno);
	}

	@Override
	public void cleanDefault(Integer userid) {
		shopDao.cleanDefault(userid);
	}

	@Override
	public void setDefault(Integer aid) {
		shopDao.setDefault(aid);
	}

	@Override
	public Integer getDefaultNum(Integer userid) {
		return shopDao.getDefaultNum(userid);
	}

	@Override
	public void updateOrderPrepay_id(String orderno) {
		shopDao.updateOrderPrepay_id(orderno);
	}

	@Override
	public String getOrderPrepay_id(String orderno) {
		return shopDao.getOrderPrepay_id(orderno);
	}
}
