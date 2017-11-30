/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import java.util.List;

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
import org.wxjs.matchfee.modules.charge.entity.OpinionBookItem;
import org.wxjs.matchfee.modules.charge.service.OpinionBookItemService;

/**
 * 条件意见书项目Controller
 * @author GLQ
 * @version 2017-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/opinionBookItem")
public class OpinionBookItemController extends BaseController {

	@Autowired
	private OpinionBookItemService opinionBookItemService;
	
	@ModelAttribute
	public OpinionBookItem get(@RequestParam(required=false) String id) {
		OpinionBookItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = opinionBookItemService.get(id);
		}
		if (entity == null){
			entity = new OpinionBookItem();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:opinionBookItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(OpinionBookItem opinionBookItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OpinionBookItem> list = opinionBookItemService.findList(opinionBookItem); 
		model.addAttribute("list", list);
		return "modules/charge/opinionBookItemList";
	}

	@RequiresPermissions("charge:opinionBookItem:view")
	@RequestMapping(value = "form")
	public String form(OpinionBookItem opinionBookItem, Model model) {
		model.addAttribute("opinionBookItem", opinionBookItem);
		return "modules/charge/opinionBookItemForm";
	}

	@RequiresPermissions("charge:opinionBookItem:edit")
	@RequestMapping(value = "save")
	public String save(OpinionBookItem opinionBookItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, opinionBookItem)){
			return form(opinionBookItem, model);
		}
		opinionBookItemService.save(opinionBookItem);
		addMessage(redirectAttributes, "保存条件意见书项目成功");
		return "redirect:"+Global.getAdminPath()+"/charge/opinionBookItem/?repage";
	}
	
	@RequiresPermissions("charge:opinionBookItem:edit")
	@RequestMapping(value = "delete")
	public String delete(OpinionBookItem opinionBookItem, RedirectAttributes redirectAttributes) {
		opinionBookItemService.delete(opinionBookItem);
		addMessage(redirectAttributes, "删除条件意见书项目成功");
		return "redirect:"+Global.getAdminPath()+"/charge/opinionBookItem/?repage";
	}

}