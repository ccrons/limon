package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.AddCarDao;
import com.limon.http.model.ShoppingCart;
import com.limon.http.service.AddCarService;

@Service("AddCarService")
public class AddCarServiceImpl implements AddCarService{
	@Autowired
	private AddCarDao addCarDao;

	public Integer addCar(ShoppingCart cart) {
		return addCarDao.addCar(cart);
	}

	public ShoppingCart getCar(ShoppingCart cart) {
		return addCarDao.getCar(cart);
	}

	public Integer updateCarNum(ShoppingCart cart) {
		return addCarDao.updateCarNum(cart);
	}
	
}
