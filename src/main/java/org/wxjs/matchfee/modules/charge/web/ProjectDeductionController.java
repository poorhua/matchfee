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
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;
import org.wxjs.matchfee.modules.charge.service.ProjectDeductionService;

/**
 * 项目抵扣项Controller
 * @author GLQ
 * @version 2017-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/projectDeduction")
public class ProjectDeductionController extends BaseController {

	@Autowired
	private ProjectDeductionService projectDeductionService;
	
	@ModelAttribute
	public ProjectDeduction get(@RequestParam(required=false) String id) {
		ProjectDeduction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectDeductionService.get(id);
		}
		if (entity == null){
			entity = new ProjectDeduction();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectDeduction projectDeduction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectDeduction> page = projectDeductionService.findPage(new Page<ProjectDeduction>(request, response), projectDeduction); 
		model.addAttribute("page", page);
		return "modules/charge/projectDeductionList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(ProjectDeduction projectDeduction, Model model) {
		model.addAttribute("projectDeduction", projectDeduction);
		return "modules/charge/projectDeductionForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(ProjectDeduction projectDeduction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectDeduction)){
			return form(projectDeduction, model);
		}
		projectDeductionService.save(projectDeduction);
		addMessage(redirectAttributes, "保存项目抵扣项成功");
		return "redirect:"+Global.getAdminPath()+"/charge/projectDeduction/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectDeduction projectDeduction, RedirectAttributes redirectAttributes) {
		projectDeductionService.delete(projectDeduction);
		addMessage(redirectAttributes, "删除项目抵扣项成功");
		return "redirect:"+Global.getAdminPath()+"/charge/projectDeduction/?repage";
	}

}