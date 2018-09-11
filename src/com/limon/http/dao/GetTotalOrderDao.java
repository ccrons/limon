package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.OrderProductGet;
import com.limon.http.model.ShopOrder;
import com.limon.store.model.StoreInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetTotalOrderDao {
	public List<ShopOrder> getTodayOrder(Map<String, Object> map);
	public Integer getTodayOrderCount(Map<String, Object> map);
	public List<ShopOrder> getTodayOrderSelf(Map<String, Object> map);
	public Integer getTodayOrderSelfCount(Map<String, Object> map);
	
	
	public Double getTodayOrderSum(Map<String, Object> map);
	public Integer getTotalOrderCount(Map<String,Object> map);
	public Double getTotalOrderSum(Map<String, Object> map);
	
	
	public List<OrderProductGet> getProductListByOid(Integer id);
	public StoreInfo getStoreInfoById(Integer sid);
}