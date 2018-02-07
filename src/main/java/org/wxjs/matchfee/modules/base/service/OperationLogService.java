/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.base.entity.OperationLog;
import org.wxjs.matchfee.modules.base.dao.OperationLogDao;
import org.wxjs.matchfee.modules.sys.entity.User;
import org.wxjs.matchfee.modules.sys.utils.UserUtils;

/**
 * 操作日志Service
 * @author GLQ
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class OperationLogService extends CrudService<OperationLogDao, OperationLog> {

	public OperationLog get(String id) {
		return super.get(id);
	}
	
	public List<OperationLog> findList(OperationLog operationLog) {
		return super.findList(operationLog);
	}
	
	public Page<OperationLog> findPage(Page<OperationLog> page, OperationLog operationLog) {
		return super.findPage(page, operationLog);
	}
	
	@Transactional(readOnly = false)
	public void save(OperationLog operationLog) {
		super.save(operationLog);
	}
	
	@Transactional(readOnly = false)
	public void logApprove(String chargeId, String operation, String result) {
		User user = UserUtils.getUser();
		String operator = user.getName()+"("+user.getLoginName()+")";
		if(user.getIsQyUser()){
			operator = user.getProject().getBuildCorpName();
		}
		OperationLog log = new OperationLog(chargeId, operator, operation, result);
		log.setRemarks("1");
		super.save(log);
	}
	
	@Transactional(readOnly = false)
	public void log(String chargeId, String operation, String result) {
		User user = UserUtils.getUser();
		String operator = user.getLoginName();
		if(user.getIsQyUser()){
			operator = user.getProject().getBuildCorpName();
		}
		OperationLog log = new OperationLog(chargeId, operator, operation, result);
		
		super.save(log);
	}
	
	@Transactional(readOnly = false)
	public void delete(OperationLog operationLog) {
		super.delete(operationLog);
	}
	
}