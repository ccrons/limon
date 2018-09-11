package com.limon.http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.UpdateCarDao;
import com.limon.http.model.ShoppingCart;
import com.limon.http.service.UpdateCarService;

@Service("UpdateCarService")
public class UpdateCarServiceImpl implements UpdateCarService{
	@Autowired
	private UpdateCarDao updateCarDao;

	public Integer updateCar(ShoppingCart cart) {
		return updateCarDao.updateCar(cart);
	}

}
