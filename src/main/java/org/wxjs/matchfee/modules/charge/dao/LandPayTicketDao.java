/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import java.util.List;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.LandPayTicket;

/**
 * LandPayTicketDAO接口
 * @author GLQ
 * @version 2017-12-11
 */
@MyBatisDao
public interface LandPayTicketDao extends CrudDao<LandPayTicket> {
	
	public List<LandPayTicket> findList4DuplicateCheck(LandPayTicket entity);
	
}