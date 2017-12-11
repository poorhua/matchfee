/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.LandPayTicket;
import org.wxjs.matchfee.modules.charge.dao.LandPayTicketDao;

/**
 * LandPayTicketService
 * @author GLQ
 * @version 2017-12-11
 */
@Service
@Transactional(readOnly = true)
public class LandPayTicketService extends CrudService<LandPayTicketDao, LandPayTicket> {

	public LandPayTicket get(String id) {
		return super.get(id);
	}
	
	public List<LandPayTicket> findList(LandPayTicket landPayTicket) {
		return super.findList(landPayTicket);
	}
	
	public Page<LandPayTicket> findPage(Page<LandPayTicket> page, LandPayTicket landPayTicket) {
		return super.findPage(page, landPayTicket);
	}
	
	@Transactional(readOnly = false)
	public void save(LandPayTicket landPayTicket) {
		super.save(landPayTicket);
	}
	
	@Transactional(readOnly = false)
	public void delete(LandPayTicket landPayTicket) {
		super.delete(landPayTicket);
	}
	
}