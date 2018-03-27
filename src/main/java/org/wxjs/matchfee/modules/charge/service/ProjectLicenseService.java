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
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
import org.wxjs.matchfee.modules.charge.dao.ChargeDao;
import org.wxjs.matchfee.modules.charge.dao.ProjectLicenseDao;

/**
 * 工程许可证Service
 * @author GLQ
 * @version 2017-12-03
 */
@Service
@Transactional(readOnly = true)
public class ProjectLicenseService extends CrudService<ProjectLicenseDao, ProjectLicense> {
	
	@Autowired
	private ChargeDao chargeDao;	

	public ProjectLicense get(String id) {
		return super.get(id);
	}
	
	public List<ProjectLicense> findList(ProjectLicense projectLicense) {
		return super.findList(projectLicense);
	}
	
	public List<ProjectLicense> findList4DuplicateCheck(ProjectLicense projectLicense) {
		return dao.findList4DuplicateCheck(projectLicense);
	}
	
	public Page<ProjectLicense> findPage(Page<ProjectLicense> page, ProjectLicense projectLicense) {
		return super.findPage(page, projectLicense);
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectLicense projectLicense) {
		super.save(projectLicense);
		
		//refresh land pay money
		Charge charge = chargeDao.get(projectLicense.getCharge());
		chargeDao.refreshLandPayMoney(charge);
		
		//refresh calMoney in charge
		chargeDao.refreshCalMoney(charge);

	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectLicense projectLicense) {
		super.delete(projectLicense);
		
		//refresh land pay money
		Charge charge = chargeDao.get(projectLicense.getCharge());
		chargeDao.refreshLandPayMoney(charge);
		
		//refresh calMoney in charge
		chargeDao.refreshCalMoney(charge);

	}
	
	@Transactional(readOnly = false)
	public void deleteOnly(ProjectLicense projectLicense) {
		super.delete(projectLicense);

	}
	
}