package com.limon.manage.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limon.manage.dao.RecruitDao;
import com.limon.manage.model.Recruit;
import com.limon.manage.service.RecruitService;

/**
 * @author wn
 *
 * 2015-9-1 下午9:21:56
 */
@Service("RecruitService")
public class RecruitServiceImpl implements RecruitService {
	
	@Autowired
	private RecruitDao recruitDao;

	public Integer getRecruitListCount(Map<String, Object> map) {
		return recruitDao.getRecruitListCount(map);
	}

	public List<Recruit> getRecruitList(Map<String, Object> map) {
		return recruitDao.getRecruitList(map);
	}

	public Recruit getRecruit(String id) {
		return recruitDao.getRecruit(id);
	}

	public void recruitAdd(Map<String, Object> map) {
		recruitDao.recruitAdd(map);
	}

	public void recruitUpdate(Map<String, Object> map) {
		recruitDao.recruitUpdate(map);
	}

	public void recruitDel(String id) {
		recruitDao.recruitDel(id);
	}

	public Integer getRecruitUpCount(Map<String, Object> map) {
		return recruitDao.getRecruitUpCount(map);
	}

	public void recruitUp(Map<String, Object> map) {
		recruitDao.recruitUp(map);
	}

}