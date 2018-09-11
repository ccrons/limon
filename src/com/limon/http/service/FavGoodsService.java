package com.limon.http.service;

import java.util.Map;

public interface FavGoodsService {
	public Integer favGoods(Map<String, Object> map);
	public Integer getFavGoods(Map<String, Object> map);
	public Integer getCartNum(Integer userid);
}
