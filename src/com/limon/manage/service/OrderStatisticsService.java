package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.store.model.OrderInfo;


/**
 * 数据统计->订单统计
 * 项目名称：limon   
 * 类名称：OrderStatisticsService
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:20   
 * @version v1.0
 */
public interface OrderStatisticsService {
	
	/**
	 * 订单统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getOrderInfoListCount(Map<String, Object> map);
	/**
	 * 订单统计列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> getOrderInfoList(Map<String, Object> map);
	/**
	 * 订单统计列表总金额
	 * @param map
	 * @return
	 */
	public Double getOrderInfoListCountPrice(Map<String, Object> map);
	/**
	 * 订单统计列表运费总金额
	 * @param map
	 * @return
	 */
	public Double getOrderInfoListCountSendPrice(Map<String, Object> map);
	
}
