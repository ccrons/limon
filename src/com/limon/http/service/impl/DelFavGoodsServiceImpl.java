package com.limon.http.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.http.dao.DelFavGoodsDao;
import com.limon.http.service.DelFavGoodsService;

@Service("DelFavGoodsService")
public class DelFavGoodsServiceImpl implements DelFavGoodsService{
	@Autowired
	private DelFavGoodsDao delFavGoodsDao;

	public Integer delFavGoods(Map<String, Object> map) {
		return delFavGoodsDao.delFavGoods(map);
	}

}
