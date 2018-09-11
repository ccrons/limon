package com.limon.http.dao;

import com.limon.http.model.ShoppingCart;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface AddCarDao {
	public Integer addCar(ShoppingCart cart);
	public ShoppingCart getCar(ShoppingCart cart);
	public Integer updateCarNum(ShoppingCart cart);
}