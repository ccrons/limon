package com.limon.manage.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.ProductBrand;

/**
 * @author wn
 *
 * 2015-7-2
 */
public interface ProductBrandDao {
	/**
	 * 查询产品品牌列表
	 * @param map 
	 * @return
	 */
	public List<ProductBrand> getProductBrandList(Map<String, Object> map);
	/**
	 * 查询产品品牌列表数量
	 * @param map 
	 * @return Integer
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
     * 查询产品品牌名称是否重复
     * @param map
     * @return
     */
	public Integer getIsUseProductBrand(Map<String, Object> map);
    /**
     * 产品品牌新增
     * @param map
     * @return
     */
	public Integer productBrandAdd(Map<String, Object> map);
	/**
	 * 产品品牌更新
	 * @param producttype
	 */
	public Integer productBrandUpdate(Map<String, Object> map);
	/**
	 * 产品品牌删除
	 * @param id
	 */
	public void productBrandDel(String id);
}
