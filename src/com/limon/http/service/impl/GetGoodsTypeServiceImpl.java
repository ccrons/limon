package com.limon.http.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.GetGoodsTypeDao;
import com.limon.http.model.ProductType;
import com.limon.http.service.GetGoodsTypeService;

@Service("GetGoodsTypeService")
public class GetGoodsTypeServiceImpl implements GetGoodsTypeService{
	@Autowired
	private GetGoodsTypeDao getGoodsTypeDao;

	public List<ProductType> getStoreProductType(Map<String, String> map) {
		return getGoodsTypeDao.getStoreProductType(map);
	}

}
