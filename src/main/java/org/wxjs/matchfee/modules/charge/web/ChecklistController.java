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
import org.wxjs.matchfee.modules.charge.entity.Checklist;
import org.wxjs.matchfee.modules.charge.service.ChecklistService;

/**
 * 结算清单Controller
 * @author XYM
 * @version 2017-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/checklist")
public class ChecklistController extends BaseController {

	@Autowired
	private ChecklistService checklistService;
	
	@ModelAttribute
	public Checklist get(@RequestParam(required=false) String id) {
		Checklist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = checklistService.get(id);
		}
		if (entity == null){
			entity = new Checklist();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:checklist:view")
	@RequestMapping(value = {"list", ""})
	public String list(Checklist checklist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Checklist> page = checklistService.findPage(new Page<Checklist>(request, response), checklist); 
		model.addAttribute("page", page);
		return "modules/charge/checklistList";
	}

	@RequiresPermissions("charge:checklist:view")
	@RequestMapping(value = "form")
	public String form(Checklist checklist, Model model) {
		model.addAttribute("checklist", checklist);
		return "modules/charge/checklistForm";
	}

	@RequiresPermissions("charge:checklist:edit")
	@RequestMapping(value = "save")
	public String save(Checklist checklist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, checklist)){
			return form(checklist, model);
		}
		checklistService.save(checklist);
		addMessage(redirectAttributes, "保存结算清单信息成功");
		return "redirect:"+Global.getAdminPath()+"/charge/checklist/?repage";
	}
	
	@RequiresPermissions("charge:checklist:edit")
	@RequestMapping(value = "delete")
	public String delete(Checklist checklist, RedirectAttributes redirectAttributes) {
		checklistService.delete(checklist);
		addMessage(redirectAttributes, "删除结算清单信息成功");
		return "redirect:"+Global.getAdminPath()+"/charge/checklist/?repage";
	}

}