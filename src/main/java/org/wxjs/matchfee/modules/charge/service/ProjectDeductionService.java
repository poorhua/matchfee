/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.service.CrudService;
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;
import org.wxjs.matchfee.modules.charge.dao.ProjectDeductionDao;

/**
 * 项目抵扣项Service
 * @author GLQ
 * @version 2017-12-03
 */
@Service
@Transactional(readOnly = true)
public class ProjectDeductionService extends CrudService<ProjectDeductionDao, ProjectDeduction> {

	public ProjectDeduction get(String id) {
		return super.get(id);
	}
	
	public List<ProjectDeduction> findList(ProjectDeduction projectDeduction) {
		return super.findList(projectDeduction);
	}
	
	public Page<ProjectDeduction> findPage(Page<ProjectDeduction> page, ProjectDeduction projectDeduction) {
		return super.findPage(page, projectDeduction);
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectDeduction projectDeduction) {
		super.save(projectDeduction);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectDeduction projectDeduction) {
		super.delete(projectDeduction);
	}
	
}