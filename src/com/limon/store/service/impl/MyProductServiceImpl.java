package com.limon.store.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.model.ProductInfo;
import com.limon.store.dao.MyProductDao;
import com.limon.store.service.MyProductService;

/**
 * @author wn
 *
 * 2015-7-8 上午10:51:56
 */
@Service("MyProductService")
public class MyProductServiceImpl implements MyProductService {
	
	@Autowired
	private MyProductDao myproductDao;

	/**
	 * 查询我的产品列表
	 * @param map
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(Map<String, Object> map) {
		return myproductDao.getProductInfoList(map);
	}

	/**
	 * 查询我的产品列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListCount(Map<String, Object> map) {
		return myproductDao.getProductInfoListCount(map);
	}

	/**
	 * 获取产品信息
	 * @param id
	 * @return
	 */
	public ProductInfo getProductInfo(Map<String, Object> map) {
		return myproductDao.getProductInfo(map);
	}

	/**
	 * 商品改售价
	 * @param map
	 */
	public void storeProductUpdate(Map<String, Object> map) {
		myproductDao.storeProductUpdate(map);
	}

	/**
	 * 商品下架
	 * @param map
	 */
	public void storeProductDel(String id) {
		myproductDao.storeProductDel(id);
	}

	public void storeProductUpdateNum(Map<String, Object> map) {
		myproductDao.storeProductUpdateNum(map);
	}

	public void storeProductUpdatePrice(Map<String, Object> map) {
		myproductDao.storeProductUpdatePrice(map);
	}

}