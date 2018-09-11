package com.limon.http.service;

import com.limon.http.model.ShoppingCart;

public interface AddCarService {
	public Integer addCar(ShoppingCart cart);
	public ShoppingCart getCar(ShoppingCart cart);
	public Integer updateCarNum(ShoppingCart cart);
}
