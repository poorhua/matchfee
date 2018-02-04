/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.LandPayTicket;
import org.wxjs.matchfee.modules.charge.dao.ChargeDao;
import org.wxjs.matchfee.modules.charge.dao.LandPayTicketDao;

/**
 * LandPayTicketService
 * @author GLQ
 * @version 2017-12-11
 */
@Service
@Transactional(readOnly = true)
public class LandPayTicketService extends CrudService<LandPayTicketDao, LandPayTicket> {
	
	@Autowired
	private ChargeDao chargeDao;	

	public LandPayTicket get(String id) {
		return super.get(id);
	}
	
	public List<LandPayTicket> findList(LandPayTicket landPayTicket) {
		return super.findList(landPayTicket);
	}
	
	public List<LandPayTicket> findList4DuplicateCheck(LandPayTicket landPayTicket) {
		return dao.findList4DuplicateCheck(landPayTicket);
	}
	
	public Page<LandPayTicket> findPage(Page<LandPayTicket> page, LandPayTicket landPayTicket) {
		return super.findPage(page, landPayTicket);
	}
	
	@Transactional(readOnly = false)
	public void save(LandPayTicket landPayTicket, Charge charge) {
		super.save(landPayTicket);
		
		//refresh land pay money
		Charge charge1 = chargeDao.get(charge);
		chargeDao.refreshLandPayMoney(charge1);
		
		//refresh cal money
		chargeDao.refreshCalMoney(charge1);
	}
	
	@Transactional(readOnly = false)
	public void delete(LandPayTicket landPayTicket, Charge charge) {
		super.delete(landPayTicket);
		
		//refresh land pay money
		Charge charge1 = chargeDao.get(charge);
		chargeDao.refreshLandPayMoney(charge1);
		
		//refresh cal money
		chargeDao.refreshCalMoney(charge1);
	}
	
}