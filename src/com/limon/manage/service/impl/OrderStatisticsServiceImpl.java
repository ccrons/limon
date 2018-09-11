package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.OrderStatisticsDao;
import com.limon.manage.service.OrderStatisticsService;
import com.limon.store.model.OrderInfo;

/**
 * 数据统计->订单统计
 * 项目名称：limon   
 * 类名称：OrderStatisticsServiceImpl
 * 创建人：WN	
 * 创建时间：2015年7月16日 下午4:26:46   
 * @version v1.0
 */
@Service("OrderStatisticsService")
public class OrderStatisticsServiceImpl implements OrderStatisticsService {
	@Autowired
	private OrderStatisticsDao orderStatisticsDao;
	/**
	 * 订单统计列表数量
	 * @param map
	 * @return
	 */
	public Integer getOrderInfoListCount(Map<String, Object> map) {
		return orderStatisticsDao.getOrderInfoListCount(map);
	}
	/**
	 * 订单统计列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> getOrderInfoList(Map<String, Object> map) {
		return orderStatisticsDao.getOrderInfoList(map);
	}
	/**
	 * 订单统计列表总金额
	 * @param map
	 * @return
	 */
	public Double getOrderInfoListCountPrice(Map<String, Object> map) {
		return orderStatisticsDao.getOrderInfoListCountPrice(map);
	}
	
	@Override
	public Double getOrderInfoListCountSendPrice(Map<String, Object> map) {
		return orderStatisticsDao.getOrderInfoListCountSendPrice(map);
	}
	
}