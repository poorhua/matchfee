/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.Checklist;
import org.wxjs.matchfee.modules.charge.dao.ChecklistDao;

/**
 * 结算清单Service
 * @author XYM
 * @version 2017-12-04
 */
@Service
@Transactional(readOnly = true)
public class ChecklistService extends CrudService<ChecklistDao, Checklist> {

	public Checklist get(String id) {
		return super.get(id);
	}
	
	public List<Checklist> findList(Checklist checklist) {
		return super.findList(checklist);
	}
	
	public Page<Checklist> findPage(Page<Checklist> page, Checklist checklist) {
		return super.findPage(page, checklist);
	}
	
	@Transactional(readOnly = false)
	public void save(Checklist checklist) {
		super.save(checklist);
	}
	
	@Transactional(readOnly = false)
	public void delete(Checklist checklist) {
		super.delete(checklist);
	}
	
}