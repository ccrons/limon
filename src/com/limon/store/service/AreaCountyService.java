package com.limon.store.service;

import java.util.List;

import com.limon.store.model.AreaCounty;

/**
 * @author gqf
 *
 * 2015-2-10 上午10:32:56
 */
public interface AreaCountyService {
    public List<AreaCounty> getCountyByCityId(Integer cityid);
}