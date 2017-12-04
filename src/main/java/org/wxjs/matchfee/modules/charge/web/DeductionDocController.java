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
import org.wxjs.matchfee.modules.charge.entity.DeductionDoc;
import org.wxjs.matchfee.modules.charge.service.DeductionDocService;

/**
 * 抵扣项文件Controller
 * @author GLQ
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/deductionDoc")
public class DeductionDocController extends BaseController {

	@Autowired
	private DeductionDocService deductionDocService;
	
	@ModelAttribute
	public DeductionDoc get(@RequestParam(required=false) String id) {
		DeductionDoc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deductionDocService.get(id);
		}
		if (entity == null){
			entity = new DeductionDoc();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeductionDoc deductionDoc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeductionDoc> page = deductionDocService.findPage(new Page<DeductionDoc>(request, response), deductionDoc); 
		model.addAttribute("page", page);
		return "modules/charge/deductionDocList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(DeductionDoc deductionDoc, Model model) {
		model.addAttribute("deductionDoc", deductionDoc);
		return "modules/charge/deductionDocForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(DeductionDoc deductionDoc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deductionDoc)){
			return form(deductionDoc, model);
		}
		deductionDocService.save(deductionDoc);
		addMessage(redirectAttributes, "保存抵扣项文件成功");
		return "redirect:"+Global.getAdminPath()+"/charge/deductionDoc/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(DeductionDoc deductionDoc, RedirectAttributes redirectAttributes) {
		deductionDocService.delete(deductionDoc);
		addMessage(redirectAttributes, "删除抵扣项文件成功");
		return "redirect:"+Global.getAdminPath()+"/charge/deductionDoc/?repage";
	}

}