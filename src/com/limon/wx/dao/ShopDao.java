package com.limon.wx.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Address;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.store.model.StoreInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface ShopDao {
	public ProductInfo getGoosById(Integer pid);
	public List<Order> getWPayOrder(Map<String,Object> map);
	public Integer getWPayOrderCount(Map<String,Object> map);
	public List<Order> getWReceiveOrder(Map<String,Object> map);
	public Integer getWReceiveOrderCount(Map<String,Object> map);
	public List<Order> getWSendOrder(Map<String,Object> map);
	public Integer getWSendOrderCount(Map<String,Object> map);
	public List<Order> getOverOrder(Map<String,Object> map);
	public Integer getOverOrderCount(Map<String,Object> map);
	public List<Order> getCancelOrder(Map<String,Object> map);
	public Integer getCancelOrderCount(Map<String,Object> map);
	public List<Order> getAllOrder(Map<String,Object> map);
	public Integer getAllOrderCount(Map<String,Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Address getAddressById(Integer aid);
	public List<StoreAdProductInfo> getTLimit(Integer sid, String tltime);
	public List<String> getTimeLimitProduct(Integer pid);
	public StoreInfo getStoreInfoById(Integer sid);
	public Order getOrderById(Integer oid);
	public Order getOrderByOrderno(String orderno);
	public void cleanDefault(Integer userid);
	public void setDefault(Integer aid);
	public Integer getDefaultNum(Integer userid);
	public void updateOrderPrepay_id(String orderno);
	public String getOrderPrepay_id(String orderno);
}
