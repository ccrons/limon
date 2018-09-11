package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.ProductInfo;


/**
 * @author wn
 *
 * 2015-7-2
 */
public interface ProductInfoService {
	/**
	 * 查询产品信息列表
	 * @param map 
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(Map<String, Object> map);
	/**
	 * 查询产品信息列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListCount(Map<String, Object> map);
	/**
	 * 查询产品信息信息
	 * @return
	 */
	public ProductInfo getProductInfo(String id);
	/**
	 * 产品信息新增
	 * @param map
	 */
	public Integer productInfoAdd(Map<String, Object> map);
	/**
	 * 产品信息更新
	 * @param map
	 */
	public Integer productInfoUpdate(Map<String, Object> map);
	/**
	 * 产品信息删除
	 * @param id
	 */
	public void productInfoDel(String id);
	/**
	 * 导入产品信息
	 * @param map
	 */
	public void productInfoImport(Map<String, Object> map);
	/**
	 * 删除产品图片
	 * @param map
	 * @return
	 */
	public void productInfoImgDel(Map<String, String> map);
	/**
	 * 获取总销售额
	 * @param map
	 * @return
	 */
	public ProductInfo getProductInfoListStatisticsPrice(Map<String, Object> map);
	
	/**
	 * 获取总销售额配送费
	 * @param map
	 * @return
	 */
	public ProductInfo getProductInfoListStatisticsSendPrice(Map<String, Object> map);
	/**
	 * 获取销售额统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListStatisticsCount(Map<String, Object> map);
	/**
	 * 获取销售额统计列表
	 * @param map
	 * @return
	 */
	public List<ProductInfo> getProductInfoListStatistics(Map<String, Object> map);
	/**
	 * 更改产品0元购
	 * @param map
	 * @return
	 */
	public void updateProduntInfoIsFristById(Map<String, Object> map);
	/**
	 * 查询所有的官营产品
	 * @return
	 */
	public List<ProductInfo> getOfficialProductInfoList();
	/**
	 * 查询所有的官方商品并左关联上架信息
	 * @return
	 */
	public List<ProductInfo> getOfficialProductInfoLeftStoreProductList(Map<String, Object> map);
	/**
	 * 更改产品抽奖
	 * @param map
	 * @return
	 */
	public void updateProduntInfoIsLuckById(Map<String, Object> map);
	
	
}
