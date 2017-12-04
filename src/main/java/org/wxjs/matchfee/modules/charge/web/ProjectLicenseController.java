/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
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
	public String form(ProjectLicense projectLicense, Model model) {
		model.addAttribute("projectLicense", projectLicense);
		return "modules/charge/projectLicenseForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(ProjectLicense projectLicense, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectLicense)){
			return form(projectLicense, model);
		}
		projectLicenseService.save(projectLicense);
		addMessage(redirectAttributes, "保存工程许可证成功");
		return "redirect:"+Global.getAdminPath()+"/charge/projectLicense/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectLicense projectLicense, RedirectAttributes redirectAttributes) {
		projectLicenseService.delete(projectLicense);
		addMessage(redirectAttributes, "删除工程许可证成功");
		return "redirect:"+Global.getAdminPath()+"/charge/projectLicense/?repage";
	}

}