package com.limon.store.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.model.ProductInfo;
import com.limon.store.dao.ShelvesDao;
import com.limon.store.model.StoreProduct;
import com.limon.store.service.ShelvesService;

/**
 * @author wn
 *
 * 2015-7-8 上午10:51:56
 */
@Service("ShelvesService")
public class ShelvesServiceImpl implements ShelvesService {
	
	@Autowired
	private ShelvesDao shelvesDao;

	/**
	 * 查询待上架列表
	 * @param map
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(Map<String, Object> map) {
		return shelvesDao.getProductInfoList(map);
	}

	/**
	 * 查询待上架列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListCount(Map<String, Object> map) {
		return shelvesDao.getProductInfoListCount(map);
	}

	/**
	 * 查询待上架产品信息
	 * @param id
	 * @return
	 */
	public ProductInfo getProductInfo(Map<String, Object> map) {
		return shelvesDao.getProductInfo(map);
	}

	/**
	 * 查询是否已上过架
	 * @param map
	 * @return
	 */
	public Integer getStoreProductCount(Map<String, Object> map) {
		return shelvesDao.getStoreProductCount(map);
	}

	/**
	 * 商品上架
	 * @param map
	 */
	public void storeProductAdd(Map<String, Object> map) {
		shelvesDao.storeProductAdd(map);
	}

	/**
	 * 商品下架
	 * @param map
	 */
	public void storeProductDel(String id) {
		shelvesDao.storeProductDel(id);
	}

	/**
	 * 根据产品id商品下架
	 * @param String
	 */
	public void storeProductDelByProductid(String productid) {
		shelvesDao.storeProductDelByProductid(productid);
	}

	/**
	 * 查询所有上架信息
	 * @param String
	 */
	public List<StoreProduct> getStoreProductList() {
		return shelvesDao.getStoreProductList();
	}

	@Override
	public void storeProductUpdate(Map<String, Object> spmap) {
		shelvesDao.storeProductUpdate(spmap);
	}

}