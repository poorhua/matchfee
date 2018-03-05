/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import java.util.List;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.PayTicket;

/**
 * 缴费凭证DAO接口
 * @author GLQ
 * @version 2017-12-07
 */
@MyBatisDao
public interface PayTicketDao extends CrudDao<PayTicket> {
	
	public List<PayTicket> findList4DuplicateCheck(PayTicket payTicket);
	
}