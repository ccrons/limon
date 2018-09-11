package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetGoodsFTypeDao;
import com.limon.http.model.ProductType;
import com.limon.http.service.GetGoodsFTypeService;

@Service("GetGoodsFTypeService")
public class GetGoodsFTypeServiceImpl implements GetGoodsFTypeService{
	@Autowired
	private GetGoodsFTypeDao getGoodsFTypeDao;

	public List<ProductType> getStoreProductType(Map<String, String> map) {
		return getGoodsFTypeDao.getStoreProductType(map);
	}

}
