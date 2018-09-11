package com.limon.store.service;

import java.util.List;

import com.limon.store.model.AreaCity;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface AreaCityService {
    public List<AreaCity> getCityByProvId(Integer provid);
}