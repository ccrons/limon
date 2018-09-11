package com.limon.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.ProductInfoDao;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.service.ProductInfoService;

/**
 * @author wn
 *
 * 2015-7-2
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
	
	@Autowired
	private ProductInfoDao productInfoDao;
	/**
	 * 查询产品信息列表
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(Map<String, Object> map) {
		return productInfoDao.getProductInfoList(map);
	}
	/**
	 * 查询产品信息列表数量
	 * @return
	 */
	public Integer getProductInfoListCount(Map<String, Object> map) {
		return productInfoDao.getProductInfoListCount(map);
	}
	/**
	 * 查询产品信息信息
	 * @return
	 */
	public ProductInfo getProductInfo(String id) {
		return productInfoDao.getProductInfo(id);
	}
	/**
	 * 产品信息新增
	 */
	public Integer productInfoAdd(Map<String, Object> map) {
		Integer inte = productInfoDao.getIsUseProductInfo(map);
		if(inte==0){
			productInfoDao.productInfoAdd(map);
		}
		return inte;
	}
	/**
	 * 产品信息更新
	 */
	public Integer productInfoUpdate(Map<String, Object> map) {
		Integer inte = productInfoDao.getIsUseProductInfo(map);
		if(inte==0){
			productInfoDao.productInfoUpdate(map);
		}
		return inte;
	}
	/**
	 * 产品信息删除
	 */
	public void productInfoDel(String id) {
		productInfoDao.productInfoDel(id);
	}
	/**
	 * 导入产品信息
	 */
	public void productInfoImport(Map<String, Object> map) {
		productInfoDao.productInfoImport(map);
	}
	/**
	 * 删除产品图片
	 */
	public void productInfoImgDel(Map<String, String> map) {
		productInfoDao.productInfoImgDel(map);
	}
	/**
	 * 获取总销售额
	 * @param map
	 * @return
	 */
	public ProductInfo getProductInfoListStatisticsPrice(Map<String, Object> map) {
		return productInfoDao.getProductInfoListStatisticsPrice(map);
	}
	/**
	 * 获取销售额统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getProductInfoListStatisticsCount(Map<String, Object> map) {
		return productInfoDao.getProductInfoListStatisticsCount(map);
	}
	/**
	 * 获取销售额统计列表
	 * @param map
	 * @return
	 */
	public List<ProductInfo> getProductInfoListStatistics(
			Map<String, Object> map) {
		return productInfoDao.getProductInfoListStatistics(map);
	}
	/**
	 * 更改产品0元购
	 * @param map
	 * @return
	 */
	public void updateProduntInfoIsFristById(Map<String, Object> map) {
		productInfoDao.updateProduntInfoIsFristById(map);
	}
	/**
	 * 查询所有的官营产品
	 */
	public List<ProductInfo> getOfficialProductInfoList() {
		return productInfoDao.getOfficialProductInfoList();
	}
	/**
	 * 查询所有的官方商品并左关联上架信息
	 * @return
	 */
	public List<ProductInfo> getOfficialProductInfoLeftStoreProductList(Map<String, Object> map) {
		return productInfoDao.getOfficialProductInfoLeftStoreProductList(map);
	}
	@Override
	public void updateProduntInfoIsLuckById(Map<String, Object> map) {
		productInfoDao.updateProduntInfoIsLuckById(map);
	}
	@Override
	public ProductInfo getProductInfoListStatisticsSendPrice(
			Map<String, Object> map) {
		return productInfoDao.getProductInfoListStatisticsSendPrice(map);
	}

	
	

}
