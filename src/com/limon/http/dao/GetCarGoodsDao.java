package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.CarGoods;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetCarGoodsDao {
	public List<CarGoods> getCarGoods(Map<String, Object> map);
}