/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.service.TreeService;
import org.wxjs.matchfee.modules.sys.dao.AreaDao;
import org.wxjs.matchfee.modules.sys.entity.Area;
import org.wxjs.matchfee.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
