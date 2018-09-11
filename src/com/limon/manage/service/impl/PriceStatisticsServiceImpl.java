package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.PriceStatisticsDao;
import com.limon.manage.model.StoreCash;
import com.limon.manage.service.PriceStatisticsService;
import com.limon.store.model.OrderInfo;
import com.limon.store.model.StoreInfo;

/**
 * 数据统计->销售额统计
 * 项目名称：limon   
 * 类名称：PriceStatisticsServiceImpl
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:46   
 * @version v1.0
 */
@Service("PriceStatisticsService")
public class PriceStatisticsServiceImpl implements PriceStatisticsService {
	@Autowired
	private PriceStatisticsDao priceStatisticsDao;
	/**
	 * 销售额统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getStoreInfoListCount(Map<String, Object> map) {
		return priceStatisticsDao.getStoreInfoListCount(map);
	}
	/**
	 * 销售额统计列表
	 * @param map
	 * @return
	 */
	public List<StoreInfo> getStoreInfoList(Map<String, Object> map) {
		return priceStatisticsDao.getStoreInfoList(map);
	}
	/**
	 * 销售额统计列表总金额
	 * @param map
	 * @return
	 */
	public StoreInfo getStoreInfoListCountPrice(Map<String, Object> map) {
		return priceStatisticsDao.getStoreInfoListCountPrice(map);
	}
	@Override
	public List<OrderInfo> getStoreOrderList(Map<String, Object> map) {
		return priceStatisticsDao.getStoreOrderList(map);
	}
	@Override
	public List<StoreCash> getStoreStoreCashList(Map<String, Object> map) {
		return priceStatisticsDao.getStoreStoreCashList(map);
	}
	@Override
	public Integer getStoreStoreCashListCount(Map<String, Object> map) {
		return priceStatisticsDao.getStoreStoreCashListCount(map);
	}
	@Override
	public void saveStoreCash(Map<String, Object> map) {
		priceStatisticsDao.saveStoreCash(map);
	}
	@Override
	public void updateStoreCashAccount(Map<String, Object> map) {
		priceStatisticsDao.updateStoreCashAccount(map);
	}
	@Override
	public void updateStoreCashStatus(Map<String, Object> map) {
		priceStatisticsDao.updateStoreCashStatus(map);
	}
	@Override
	public Integer getCashByStoreidAndTime(Map<String, Object> map) {
		return priceStatisticsDao.getCashByStoreidAndTime(map);
	}
	
}