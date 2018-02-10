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
import org.wxjs.matchfee.modules.base.utils.WebServiceUtils;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.Project;
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
import org.wxjs.matchfee.modules.charge.dao.ChargeDao;
import org.wxjs.matchfee.modules.charge.dao.ProjectDao;

import com.google.common.collect.Lists;

/**
 * 项目信息Service
 * @author GLQ
 * @version 2017-11-27
 */
@Service
@Transactional(readOnly = true)
public class ProjectService extends CrudService<ProjectDao, Project> {
	
	@Autowired
	private ChargeDao chargeDao;
	
	@Autowired
	private ProjectLicenseService projectLicenseService;

	public Project get(String id) {
		return super.get(id);
	}
	
	public Project getByPrjNum(String prjNum) {
		return dao.getByPrjNum(prjNum);
	}
	
	public List<Project> findList(Project project) {
		return super.findList(project);
	}
	
	public Page<Project> findPage(Page<Project> page, Project project) {
		
		return super.findPage(page, project);
	}
	
	public Project getByPrjNum_Remote(String prjNum) {
		return WebServiceUtils.getProjectInfo(prjNum);
	}
	
	public List<Project> findList_LocalAndRemote(Project project) {
		
		List<Project> list = dao.findList(project);
		
		logger.debug("local: "+list.size());
		
		List<String> prjNumList = Lists.newArrayList();
		StringBuffer prjNums = new StringBuffer();
		
		for(Project prj : list){
			prjNumList.add(prj.getPrjNum());
			prjNums.append("," + prj.getPrjNum());
		}
		
		//fill charge history
		Charge chargeParam = new Charge();
		Project projectParam = new Project();
		projectParam.setPrjNum(prjNums.length()>0?prjNums.substring(1): prjNums.toString());
		chargeParam.setProject(projectParam);
		
		List<Charge> chargeList = chargeDao.findList(chargeParam);
		
		for(Project projectItem : list){
			List<Charge> projectChargeList = Lists.newArrayList();
			for(Charge chargeItem : chargeList){
				if(projectItem.getPrjNum().equals(chargeItem.getProject().getPrjNum())){
					ProjectLicense projectLicenseParam = new ProjectLicense();
					projectLicenseParam.setCharge(new Charge(chargeItem.getId()));
					//get ProjectLicense
					chargeItem.setProjectLicenseList(this.projectLicenseService.findList(projectLicenseParam));
					projectChargeList.add(chargeItem);
				}
			}
			projectItem.setCharges(projectChargeList);
		}
		
		//get from remote server
		List<Project> remoteList = WebServiceUtils.getProjectList(project);
		
		logger.debug("remote: "+remoteList.size());
		
		for(Project prj : remoteList){
			if(!prjNumList.contains(prj.getPrjNum())){
				list.add(prj);
				prjNumList.add(prj.getPrjNum());
			}
		}
		
		logger.debug("total: "+list.size());
		
		return list;
	}
	
	@Transactional(readOnly = false)
	public void save(Project project) {
		super.save(project);
	}
	
	@Transactional(readOnly = false)
	public void delete(Project project) {
		super.delete(project);
	}
	
}