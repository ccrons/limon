package com.limon.store.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.ProductInfo;


/**
 * @author wn
 *
 * 2015-7-8 上午11:37:56
 */
public interface MyProductDao {
	
	/**
	 * 查询我的产品列表
	 * @param map
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(Map<String, Object> map);
	/**
	 * 查询我的产品列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListCount(Map<String, Object> map);
	/**
	 * 获取产品信息
	 * @param id
	 * @return
	 */
	public ProductInfo getProductInfo(Map<String, Object> map);
	/**
	 * 商品改售价
	 * @param map
	 */
	public void storeProductUpdate(Map<String, Object> map);
	/**
	 * 商品下架
	 * @param map
	 */
	public void storeProductDel(String id);
	/**
	 * ajax商品改售价
	 * @param map
	 */
	public void storeProductUpdatePrice(Map<String, Object> map);
	
	/**
	 * ajax商品改库存
	 * @param map
	 */
	public void storeProductUpdateNum(Map<String, Object> map);
}