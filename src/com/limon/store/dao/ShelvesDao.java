package com.limon.store.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.ProductInfo;
import com.limon.store.model.StoreProduct;


/**
 * @author wn
 *
 * 2015-7-8 上午11:37:56
 */
public interface ShelvesDao {
	
	/**
	 * 查询待上架列表
	 * @param map
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(Map<String, Object> map);
	/**
	 * 查询待上架列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListCount(Map<String, Object> map);
	/**
	 * 查询待上架产品信息
	 * @param id
	 * @return
	 */
	public ProductInfo getProductInfo(Map<String, Object> map);
	/**
	 * 查询是否已上过架
	 * @param map
	 * @return
	 */
	public Integer getStoreProductCount(Map<String, Object> map);
	/**
	 * 商品上架
	 * @param map
	 */
	public void storeProductAdd(Map<String, Object> map);
	/**
	 * 商品下架
	 * @param map
	 */
	public void storeProductDel(String id);
	/**
	 * 根据产品id商品下架
	 * @param String
	 */
	public void storeProductDelByProductid(String productid);
	/**
	 * 查询所有上架信息
	 * @return
	 */
	public List<StoreProduct> getStoreProductList();
	public void storeProductUpdate(Map<String, Object> spmap);
    
}