package com.limon.store.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.store.dao.OrderInfoDao;
import com.limon.store.model.OrderInfo;
import com.limon.store.service.OrderInfoService;

/**
 * 
 * 项目名称：limon   
 * 类名称：OrderInfoServiceImpl
 * 创建人：WN	
 * 创建时间：2015年7月10日 上午11:03:40   
 * @version v1.0
 */
@Service("OrderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	/**
	 * 订单列表数量
	 */
	public Integer getOrderInfoListCount(Map<String, Object> map) {
		return orderInfoDao.getOrderInfoListCount(map);
	}
	/**
	 * 订单列表
	 */
	public List<OrderInfo> getOrderInfoList(Map<String, Object> map) {
		return orderInfoDao.getOrderInfoList(map);
	}
	/**
	 * 确认订单
	 */
	public void orderInfoCF(String id) {
		orderInfoDao.orderInfoCF(id);
	}
	/**
	 * 订单取消
	 */
	public void orderInfoCancel(Map<String, Object> map) {
		orderInfoDao.orderInfoCancel(map);
	}
	/**
	 * 订单详情
	 */
	public OrderInfo getOrderInfo(String id) {
		return orderInfoDao.getOrderInfo(id);
	}
	/**
	 * 根据订单id查找产品列表
	 */
	public List<ProductInfo> getProductInfoList(String id) {
		return orderInfoDao.getProductInfoList(id);
	}
	/**
	 * 获取历史订单列表数量
	 * @param map
	 * @return
	 */
	public Integer getHistoryOrderInfoListCount(Map<String, Object> map) {
		return orderInfoDao.getHistoryOrderInfoListCount(map);
	}
	/**
	 * 获取历史订单列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> getHistoryOrderInfoList(Map<String, Object> map) {
		return orderInfoDao.getHistoryOrderInfoList(map);
	}
	/**
	 * 获取新订单订单列表数量
	 * @param map
	 * @return
	 */
	public Integer getNewOrderListCount(Map<String, Object> map) {
		return orderInfoDao.getNewOrderListCount(map);
	}
	public Order getOrderbyOid(Integer id) {
		return orderInfoDao.getOrderbyOid(id);
	}
	public List<OrderProductGet> getProductListByOid(Integer id) {
		return orderInfoDao.getProductListByOid(id);
	}
	public Integer updateStoreSaleNum(Map<String, Object> map) {
		return orderInfoDao.updateStoreSaleNum(map);
	}

	public void orderInfoOver(String id,String goodscode) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id",id);
		map.put("return_id", goodscode);
		orderInfoDao.orderInfoOver(map);
	}
	@Override
	public Order getOrderbyId(Integer id) {
		return orderInfoDao.getOrderbyId(id);
	}
	
}