package com.limon.http.service;

import java.util.List;
import java.util.Map;

import com.limon.http.model.ProductType;

public interface GetGoodsFTypeService {
	public List<ProductType> getStoreProductType(Map<String, String> map);
}
