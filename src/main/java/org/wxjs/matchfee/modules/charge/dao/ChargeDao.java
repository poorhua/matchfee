/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.dao;

import org.wxjs.matchfee.common.persistence.CrudDao;
import org.wxjs.matchfee.common.persistence.annotation.MyBatisDao;
import org.wxjs.matchfee.modules.charge.entity.Charge;

/**
 * 征收DAO接口
 * @author GLQ
 * @version 2017-11-25
 */
@MyBatisDao
public interface ChargeDao extends CrudDao<Charge> {
	
	public int updateReport(Charge charge);
	
	public int updateCalculate(Charge charge);
	
	public int updateApprove(Charge charge);
	
	public int updateConfirm(Charge charge);
	
	public int updateStatus(Charge charge);
	
}