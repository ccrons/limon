package com.limon.manage.dao;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.ProductType;

/**
 * @author wn
 *
 * 2015-7-2
 */
public interface ProductTypeDao {
	/**
	 * 查询产品类型列表
	 * @param map 
	 * @return
	 */
	public List<ProductType> getProductTypeList(Map<String, Object> map);
	/**
	 * 查询产品类型列表数量
	 * @param map 
	 * @return Integer
	 */
	public Integer getProductTypeListCount(Map<String, Object> map);
	/**
	 * 产品类型列表
	 * @return
	 */
	public List<ProductType> getProductTypeListAll();
	/**
	 * 查询产品类型信息
	 * @return
	 */
    public ProductType getProductType(String id);
    /**
     * 查询产品类型名称是否重复
     * @param map
     * @return
     */
	public Integer getIsUseProductType(Map<String, Object> map);
    /**
     * 产品类型新增
     * @param map
     * @return
     */
	public Integer productTypeAdd(Map<String, Object> map);
	/**
	 * 产品类型更新
	 * @param producttype
	 */
	public Integer productTypeUpdate(Map<String, Object> map);
	/**
	 * 产品类型删除
	 * @param id
	 */
	public void productTypeDel(String id);
	/**
	 * 根据pid查询类型列表
	 * @param map
	 * @return
	 */
	public List<ProductType> getProductTypeListByPid(Map<String, Object> map);
	
}
