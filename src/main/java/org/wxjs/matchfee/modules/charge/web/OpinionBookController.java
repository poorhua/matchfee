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
import org.wxjs.matchfee.modules.charge.entity.OpinionBook;
import org.wxjs.matchfee.modules.charge.service.OpinionBookService;

/**
 * 条件意见书Controller
 * @author GLQ
 * @version 2017-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/opinionBook")
public class OpinionBookController extends BaseController {

	@Autowired
	private OpinionBookService opinionBookService;
	
	@ModelAttribute
	public OpinionBook get(@RequestParam(required=false) String id) {
		OpinionBook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = opinionBookService.get(id);
		}
		if (entity == null){
			entity = new OpinionBook();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(OpinionBook opinionBook, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OpinionBook> list = opinionBookService.findList(opinionBook); 
		model.addAttribute("list", list);
		return "modules/charge/opinionBookList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(OpinionBook opinionBook, Model model) {
		model.addAttribute("opinionBook", opinionBook);
		return "modules/charge/opinionBookForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(OpinionBook opinionBook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, opinionBook)){
			return form(opinionBook, model);
		}
		opinionBookService.save(opinionBook);
		addMessage(redirectAttributes, "保存OpinonBook成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/opinionBookTab?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(OpinionBook opinionBook, RedirectAttributes redirectAttributes) {
		opinionBookService.delete(opinionBook);
		addMessage(redirectAttributes, "删除OpinonBook成功");
		return "redirect:"+Global.getAdminPath()+"/charge/opinionBook/?repage";
	}

}