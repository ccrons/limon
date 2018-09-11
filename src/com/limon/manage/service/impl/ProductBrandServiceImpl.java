package com.limon.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.ProductBrandDao;
import com.limon.manage.model.ProductBrand;
import com.limon.manage.service.ProductBrandService;

/**
 * @author wn
 *
 * 2015-7-2
 */
@Service("productBrandService")
public class ProductBrandServiceImpl implements ProductBrandService {
	
	@Autowired
	private ProductBrandDao productBrandDao;
	/**
	 * 查询产品品牌列表
	 * @return
	 */
	public List<ProductBrand> getProductBrandList(Map<String, Object> map) {
		return productBrandDao.getProductBrandList(map);
	}
	/**
	 * 查询产品品牌列表数量
	 * @return
	 */
	public Integer getProductBrandListCount(Map<String, Object> map) {
		return productBrandDao.getProductBrandListCount(map);
	}
	/**
	 * 产品品牌列表
	 * @return
	 */
	public List<ProductBrand> getProductBrandListAll() {
		return productBrandDao.getProductBrandListAll();
	}
	/**
	 * 查询产品品牌信息
	 * @return
	 */
	public ProductBrand getProductBrand(String id) {
		return productBrandDao.getProductBrand(id);
	}
	/**
	 * 产品品牌新增
	 */
	public Integer productBrandAdd(Map<String, Object> map) {
		Integer inte = productBrandDao.getIsUseProductBrand(map);
		if(inte==0){
			productBrandDao.productBrandAdd(map);
		}
		return inte;
	}
	/**
	 * 产品品牌更新
	 */
	public Integer productBrandUpdate(Map<String, Object> map) {
		Integer inte = productBrandDao.getIsUseProductBrand(map);
		if(inte==0){
			productBrandDao.productBrandUpdate(map);
		}
		return inte;
	}
	/**
	 * 产品品牌删除
	 */
	public void productBrandDel(String id) {
		productBrandDao.productBrandDel(id);
	}

	
	

}
