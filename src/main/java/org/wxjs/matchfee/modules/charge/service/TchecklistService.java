/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.Tchecklist;
import org.wxjs.matchfee.modules.charge.dao.TchecklistDao;

/**
 * 结算清单Service
 * @author XYM
 * @version 2017-12-04
 */
@Service
@Transactional(readOnly = true)
public class TchecklistService extends CrudService<TchecklistDao, Tchecklist> {

	public Tchecklist get(String id) {
		return super.get(id);
	}
	
	public List<Tchecklist> findList(Tchecklist tchecklist) {
		return super.findList(tchecklist);
	}
	
	public Page<Tchecklist> findPage(Page<Tchecklist> page, Tchecklist tchecklist) {
		return super.findPage(page, tchecklist);
	}
	
	@Transactional(readOnly = false)
	public void save(Tchecklist tchecklist) {
		super.save(tchecklist);
	}
	
	@Transactional(readOnly = false)
	public void delete(Tchecklist tchecklist) {
		super.delete(tchecklist);
	}
	
}