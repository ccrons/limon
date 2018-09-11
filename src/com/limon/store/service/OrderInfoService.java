package com.limon.store.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.manage.model.ProductInfo;
import com.limon.store.model.OrderInfo;


/**
 * 
 * 项目名称：limon   
 * 类名称：OrderInfoService
 * 创建人：WN	
 * 创建时间：2015年7月10日 上午11:22:02   
 * @version v1.0
 */
public interface OrderInfoService {
	/**
	 * 订单列表数量
	 * @param map
	 * @return
	 */
	public Integer getOrderInfoListCount(Map<String, Object> map);
	/**
	 * 订单列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> getOrderInfoList(Map<String, Object> map);
	/**
	 * 订单确认
	 * @param id
	 */
	public void orderInfoCF(String id);
	/**
	 * 订单完成
	 * @param id
	 */
	public void orderInfoOver(String id,String goodscode);
	/**
	 * 订单取消
	 * @param id
	 */
	public void orderInfoCancel(Map<String, Object> map);
	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	public OrderInfo getOrderInfo(String id);
	/**
	 * 根据订单id查找产品列表
	 * @param id
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(String id);
	/**
	 * 获取历史订单列表数量
	 * @param map
	 * @return
	 */
	public Integer getHistoryOrderInfoListCount(Map<String, Object> map);
	/**
	 * 获取历史订单列表
	 * @param map
	 * @return
	 */
	public List<OrderInfo> getHistoryOrderInfoList(Map<String, Object> map);
	/**
	 * 获取新订单订单列表数量
	 * @param map
	 * @return
	 */
	public Integer getNewOrderListCount(Map<String, Object> map);
	
	public List<OrderProductGet> getProductListByOid(Integer id);
	public Integer updateStoreSaleNum(Map<String,Object> map);
	public Order getOrderbyOid(Integer id);
	public Order getOrderbyId(Integer id);
}