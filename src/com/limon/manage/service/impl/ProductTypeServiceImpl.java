package com.limon.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.ProductTypeDao;
import com.limon.manage.model.ProductType;
import com.limon.manage.service.ProductTypeService;

/**
 * @author wn
 *
 * 2015-7-2
 */
@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Autowired
	private ProductTypeDao productTypeDao;
	/**
	 * 查询产品类型列表
	 * @return
	 */
	public List<ProductType> getProductTypeList(Map<String, Object> map) {
		return productTypeDao.getProductTypeList(map);
	}
	/**
	 * 查询产品类型列表数量
	 * @return
	 */
	public Integer getProductTypeListCount(Map<String, Object> map) {
		return productTypeDao.getProductTypeListCount(map);
	}
	/**
	 * 产品类型列表
	 * @return
	 */
	public List<ProductType> getProductTypeListAll() {
		return productTypeDao.getProductTypeListAll();
	}
	/**
	 * 查询产品类型信息
	 * @return
	 */
	public ProductType getProductType(String id) {
		return productTypeDao.getProductType(id);
	}
	/**
	 * 产品类型新增
	 */
	public Integer productTypeAdd(Map<String, Object> map) {
		Integer inte = productTypeDao.getIsUseProductType(map);
		if(inte==0){
			productTypeDao.productTypeAdd(map);
		}
		return inte;
	}
	/**
	 * 产品类型更新
	 */
	public Integer productTypeUpdate(Map<String, Object> map) {
		Integer inte = productTypeDao.getIsUseProductType(map);
		if(inte==0){
			productTypeDao.productTypeUpdate(map);
		}
		return inte;
	}
	/**
	 * 产品类型删除
	 */
	public void productTypeDel(String id) {
		productTypeDao.productTypeDel(id);
	}
	/**
	 * 根据pid查询类型列表
	 * @param map
	 * @return
	 */
	public List<ProductType> getProductTypeListByPid(Map<String, Object> map) {
		return productTypeDao.getProductTypeListByPid(map);
	}
	/**
	 * 查询二级分类列表
	 * @param map
	 * @return
	 */
	public List<ProductType> getTwoLevelProductTypeList() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pid","0");
		List<ProductType> producttypelist = productTypeDao.getProductTypeListByPid(map);
		for (int i = 0; i < producttypelist.size(); i++) {
			map.put("pid",producttypelist.get(i).getId());
			producttypelist.get(i).setChildproductpypelist(productTypeDao.getProductTypeListByPid(map));
		}
		return producttypelist;
	}

	
	

}
