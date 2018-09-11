package com.limon.http.dao;

import java.util.Map;

import com.limon.manage.model.ProductInfo;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface ShopGoodsUpDao {
	public Integer getStoreProductCount(Map<String, Object> map);
	public void storeProductAdd(Map<String, Object> map);
	public ProductInfo getProductInfo(Map<String, Object> map);
}