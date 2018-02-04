/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.Page;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.common.utils.StringUtils;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.LandPayTicket;
import org.wxjs.matchfee.modules.charge.entity.Project;
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
	public String save(LandPayTicket landPayTicket, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, landPayTicket)){
			return form(landPayTicket, model);
		}
		
		String chargeId = Util.getString(httpSession.getAttribute("chargeId"));
		Charge charge = new Charge(chargeId);
		Project project = new Project();
		project.setPrjNum(landPayTicket.getPrjNum());
		charge.setProject(project);
		
		try{
			landPayTicketService.save(landPayTicket, charge); 
			addMessage(redirectAttributes, "保存国土缴费凭证成功");
		}catch(DuplicateKeyException e1){
			addMessage(redirectAttributes, "保存失败。重复！");
			logger.error("save error", e1);
		}catch(Exception e2){
			addMessage(redirectAttributes, "保存失败。");
			logger.error("save error", e2);
		}
		
		
		return "redirect:"+Global.getAdminPath()+"/charge/charge/landPayTicketTab?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(LandPayTicket landPayTicket, HttpSession httpSession, RedirectAttributes redirectAttributes) {
		
		String chargeId = Util.getString(httpSession.getAttribute("chargeId"));
		Charge charge = new Charge(chargeId);
		Project project = new Project();
		project.setPrjNum(landPayTicket.getPrjNum());
		charge.setProject(project);
		
		landPayTicketService.delete(landPayTicket, charge);
		addMessage(redirectAttributes, "删除国土已缴费成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/landPayTicketTab/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "ticketNoExists")
	public boolean ticketNoExists(String id, String ticketNo) {
		
		LandPayTicket landPayTicket = new LandPayTicket();
		
		landPayTicket.setId(id);
		
		landPayTicket.setTicketNo(ticketNo);
		
		List<LandPayTicket> list = landPayTicketService.findList4DuplicateCheck(landPayTicket);
		
		return (list != null && list.size() > 0);
	}

}