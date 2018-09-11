package com.limon.manage.service;

import java.util.List;
import java.util.Map;

import com.limon.manage.model.Recruit;



/**
 * @author wn
 *
 * 2015-9-1 下午9:21:56
 */
public interface RecruitService {

	public Integer getRecruitListCount(Map<String, Object> map);

	public List<Recruit> getRecruitList(Map<String, Object> map);

	public Recruit getRecruit(String id);

	public void recruitAdd(Map<String, Object> map);

	public void recruitUpdate(Map<String, Object> map);

	public void recruitDel(String id);

	public Integer getRecruitUpCount(Map<String, Object> map);

	public void recruitUp(Map<String, Object> map);
	
    
}
