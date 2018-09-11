package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.ProductBrand;


/**
 * @author wn
 *
 * 2015-7-2
 */
public interface ProductBrandService {
	/**
	 * 查询产品品牌列表
	 * @param map 
	 * @return
	 */
	public List<ProductBrand> getProductBrandList(Map<String, Object> map);
	/**
	 * 查询产品品牌列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductBrandListCount(Map<String, Object> map);
	/**
	 * 产品品牌列表
	 * @param map 
	 * @return
	 */
	public List<ProductBrand> getProductBrandListAll();
	/**
	 * 查询产品品牌信息
	 * @return
	 */
	public ProductBrand getProductBrand(String id);
	/**
	 * 产品品牌新增
	 * @param map
	 */
	public Integer productBrandAdd(Map<String, Object> map);
	/**
	 * 产品品牌更新
	 * @param map
	 */
	public Integer productBrandUpdate(Map<String, Object> map);
	/**
	 * 产品品牌删除
	 * @param id
	 */
	public void productBrandDel(String id);
	
	
}
