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
import org.wxjs.matchfee.modules.charge.entity.Tchecklist;
import org.wxjs.matchfee.modules.charge.service.TchecklistService;

/**
 * 结算清单Controller
 * @author XYM
 * @version 2017-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/tchecklist")
public class TchecklistController extends BaseController {

	@Autowired
	private TchecklistService tchecklistService;
	
	@ModelAttribute
	public Tchecklist get(@RequestParam(required=false) String id) {
		Tchecklist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tchecklistService.get(id);
		}
		if (entity == null){
			entity = new Tchecklist();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:tchecklist:view")
	@RequestMapping(value = {"list", ""})
	public String list(Tchecklist tchecklist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Tchecklist> page = tchecklistService.findPage(new Page<Tchecklist>(request, response), tchecklist); 
		model.addAttribute("page", page);
		return "modules/charge/tchecklistList";
	}

	@RequiresPermissions("charge:tchecklist:view")
	@RequestMapping(value = "form")
	public String form(Tchecklist tchecklist, Model model) {
		model.addAttribute("tchecklist", tchecklist);
		return "modules/charge/tchecklistForm";
	}

	@RequiresPermissions("charge:tchecklist:edit")
	@RequestMapping(value = "save")
	public String save(Tchecklist tchecklist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tchecklist)){
			return form(tchecklist, model);
		}
		tchecklistService.save(tchecklist);
		addMessage(redirectAttributes, "保存结算清单信息成功");
		return "redirect:"+Global.getAdminPath()+"/charge/tchecklist/?repage";
	}
	
	@RequiresPermissions("charge:tchecklist:edit")
	@RequestMapping(value = "delete")
	public String delete(Tchecklist tchecklist, RedirectAttributes redirectAttributes) {
		tchecklistService.delete(tchecklist);
		addMessage(redirectAttributes, "删除结算清单信息成功");
		return "redirect:"+Global.getAdminPath()+"/charge/tchecklist/?repage";
	}

}