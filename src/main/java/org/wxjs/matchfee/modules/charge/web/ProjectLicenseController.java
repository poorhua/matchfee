/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.common.utils.StringUtils;
import org.wxjs.matchfee.modules.base.service.OperationLogService;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
import org.wxjs.matchfee.modules.charge.service.ChargeService;
import org.wxjs.matchfee.modules.charge.service.ProjectLicenseService;

/**
 * 工程许可证Controller
 * @author GLQ
 * @version 2017-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/projectLicense")
public class ProjectLicenseController extends BaseController {

	@Autowired
	private ProjectLicenseService projectLicenseService;
	
	@Autowired
	private ChargeService chargeService;
	
	@ModelAttribute
	public ProjectLicense get(@RequestParam(required=false) String id) {
		ProjectLicense entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectLicenseService.get(id);
		}
		if (entity == null){
			entity = new ProjectLicense();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectLicense projectLicense, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectLicense> page = projectLicenseService.findPage(new Page<ProjectLicense>(request, response), projectLicense); 
		model.addAttribute("page", page);
		return "modules/charge/projectLicenseList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(ProjectLicense projectLicense, HttpSession httpSession,Model model) {
		model.addAttribute("projectLicense", projectLicense);
		
		String chargeId = (String)httpSession.getAttribute("chargeId");
		
		Charge charge = chargeService.get(chargeId);
		
		model.addAttribute("charge", charge);
		return "modules/charge/projectLicenseForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(ProjectLicense projectLicense, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectLicense)){
			return form(projectLicense,httpSession, model);
		}
		try{
			projectLicenseService.save(projectLicense);
			
			//operationLogService.log(projectLicense.getCharge().getId(), "保存规划许可证", "成功");
			
			addMessage(redirectAttributes, "保存工程许可证成功");
		}catch(DuplicateKeyException e1){
			addMessage(redirectAttributes, "保存工程许可证失败。重复！");
			logger.error("save error", e1);
		}catch(Exception e2){
			addMessage(redirectAttributes, "保存工程许可证失败。");
			logger.error("save error", e2);
		}
		return "redirect:"+Global.getAdminPath()+"/charge/charge/projectLicenseTab/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectLicense projectLicense, RedirectAttributes redirectAttributes) {
		projectLicenseService.delete(projectLicense);
		
		//operationLogService.log(projectLicense.getCharge().getId(), "删除规划许可证", "成功");
		
		addMessage(redirectAttributes, "删除工程许可证成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/projectLicenseTab/?repage";
	}

}