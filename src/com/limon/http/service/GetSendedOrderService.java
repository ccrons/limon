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
public interface GetSendedOrderService {
	public List<ShopOrder> getSendedOrder(Map<String, Object> map);
	public Integer getSendedOrderCount(Map<String, Object> map);
	public List<ShopOrder> getSendedOrderSelf(Map<String, Object> map);
	public Integer getSendedOrderSelfCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public StoreInfo getStoreInfoById(Integer sid);
}