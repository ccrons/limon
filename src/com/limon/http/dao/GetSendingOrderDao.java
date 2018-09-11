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
public interface GetSendingOrderDao {
	public List<ShopOrder> getSendingOrder(Map<String, Object> map);
	public Integer getSendingOrderCount(Map<String, Object> map);
	public List<ShopOrder> getSendingOrderSelf(Map<String, Object> map);
	public Integer getSendingOrderSelfCount(Map<String, Object> map);
	public List<OrderProductGet> getProductListByOid(Integer id);
	public StoreInfo getStoreInfoById(Integer sid);
}