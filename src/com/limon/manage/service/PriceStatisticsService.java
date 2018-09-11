package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.StoreCash;
import com.limon.store.model.OrderInfo;
import com.limon.store.model.StoreInfo;


/**
 * 数据统计->销售额统计
 * 项目名称：limon   
 * 类名称：PriceStatisticsService
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:20   
 * @version v1.0
 */
public interface PriceStatisticsService {
	
	/**
	 * 销售额统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getStoreInfoListCount(Map<String, Object> map);
	/**
	 * 销售额统计列表
	 * @param map
	 * @return
	 */
	public List<StoreInfo> getStoreInfoList(Map<String, Object> map);
	/**
	 * 销售额统计列表总金额
	 * @param map
	 * @return
	 */
	public StoreInfo getStoreInfoListCountPrice(Map<String, Object> map);
	/**
	 * 销售额统计订单列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> getStoreOrderList(Map<String, Object> map);
	
	/**
	 * 销售额统计提现列表
	 * @param map
	 * @return
	 */
	public List<StoreCash> getStoreStoreCashList(Map<String, Object> map);
	public Integer getStoreStoreCashListCount(Map<String, Object> map);
	public void saveStoreCash(Map<String, Object> map);
	public void updateStoreCashAccount(Map<String, Object> map);
	public void updateStoreCashStatus(Map<String, Object> map);
	public Integer getCashByStoreidAndTime(Map<String, Object> map);
	
}
