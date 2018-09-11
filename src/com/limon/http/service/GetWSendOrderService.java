package com.limon.http.service;

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
public interface GetWSendOrderService {
	public List<ShopOrder> getWSendOrder(Map<String, Object> map);
	public Integer getWSendOrderCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public StoreInfo getStoreInfoById(Integer sid);
	public List<ShopOrder> getWSendOrderSelf(Map<String, Object> map);
	public Integer getWSendOrderSelfCount(Map<String, Object> map);
}