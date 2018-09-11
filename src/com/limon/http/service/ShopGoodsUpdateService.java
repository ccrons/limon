package com.limon.http.service;

import java.util.Map;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface ShopGoodsUpdateService {
	public void storeProductUpdate(Map<String, Object> map);
	public void storeProductUpdatePrice(Map<String, Object> map);
	public void storeProductUpdateNum(Map<String, Object> map);
}