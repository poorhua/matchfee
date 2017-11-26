/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.dao.ChargeDao;

/**
 * 征收Service
 * @author GLQ
 * @version 2017-11-25
 */
@Service
@Transactional(readOnly = true)
public class ChargeService extends CrudService<ChargeDao, Charge> {

	public Charge get(String id) {
		return super.get(id);
	}
	
	public List<Charge> findList(Charge charge) {
		return super.findList(charge);
	}
	
	public Page<Charge> findPage(Page<Charge> page, Charge charge) {
		return super.findPage(page, charge);
	}
	
	@Transactional(readOnly = false)
	public void save(Charge charge) {
		super.save(charge);
	}
	
	@Transactional(readOnly = false)
	public void delete(Charge charge) {
		super.delete(charge);
	}
	
}