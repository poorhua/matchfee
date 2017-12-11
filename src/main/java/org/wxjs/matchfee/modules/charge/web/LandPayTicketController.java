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
import org.wxjs.matchfee.modules.charge.entity.LandPayTicket;
import org.wxjs.matchfee.modules.charge.service.LandPayTicketService;

/**
 * LandPayTicketController
 * @author GLQ
 * @version 2017-12-11
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/landPayTicket")
public class LandPayTicketController extends BaseController {

	@Autowired
	private LandPayTicketService landPayTicketService;
	
	@ModelAttribute
	public LandPayTicket get(@RequestParam(required=false) String id) {
		LandPayTicket entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = landPayTicketService.get(id);
		}
		if (entity == null){
			entity = new LandPayTicket();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(LandPayTicket landPayTicket, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LandPayTicket> page = landPayTicketService.findPage(new Page<LandPayTicket>(request, response), landPayTicket); 
		model.addAttribute("page", page);
		return "modules/charge/landPayTicketList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(LandPayTicket landPayTicket, Model model) {
		model.addAttribute("landPayTicket", landPayTicket);
		return "modules/charge/landPayTicketForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(LandPayTicket landPayTicket, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, landPayTicket)){
			return form(landPayTicket, model);
		}
		landPayTicketService.save(landPayTicket);
		addMessage(redirectAttributes, "保存国土缴费凭证成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/landPayTicketTab?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(LandPayTicket landPayTicket, RedirectAttributes redirectAttributes) {
		landPayTicketService.delete(landPayTicket);
		addMessage(redirectAttributes, "删除国土已缴费成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/landPayTicketTab/?repage";
	}

}