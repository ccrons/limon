package com.limon.http.dao;

import java.util.List;
import java.util.Map;

import com.limon.http.model.ShopMyGoods;
import com.limon.http.model.ShopMyGoodsIndex;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface GetMyGoodsByShopDao {
	public List<ShopMyGoods> getMyGoodsByShop(Map<String, Object> map);
	public Integer getMyGoodsByShopCount(Map<String, Object> map);
	public List<ShopMyGoodsIndex> getMyGoodsSearchByShop(Map<String, Object> map);
	public List<ShopMyGoodsIndex> getMyGoodsRecByShop(Integer sid);
	public List<ShopMyGoodsIndex> getMyGoodsFirstByShop(Integer sid);
	public List<ShopMyGoodsIndex> getMyGoodsLuckByShop(Integer sid);
}