/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.PayTicket;
import org.wxjs.matchfee.modules.charge.dao.PayTicketDao;

/**
 * 缴费凭证Service
 * @author GLQ
 * @version 2017-12-07
 */
@Service
@Transactional(readOnly = true)
public class PayTicketService extends CrudService<PayTicketDao, PayTicket> {

	public PayTicket get(String id) {
		return super.get(id);
	}
	
	public List<PayTicket> findList(PayTicket payTicket) {
		return super.findList(payTicket);
	}
	
	public Page<PayTicket> findPage(Page<PayTicket> page, PayTicket payTicket) {
		return super.findPage(page, payTicket);
	}
	
	@Transactional(readOnly = false)
	public void save(PayTicket payTicket) {
		super.save(payTicket);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayTicket payTicket) {
		super.delete(payTicket);
	}
	
}