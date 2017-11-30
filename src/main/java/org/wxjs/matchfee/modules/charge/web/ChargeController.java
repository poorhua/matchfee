/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.web;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.Project;
import org.wxjs.matchfee.modules.charge.service.ChargeService;
import org.wxjs.matchfee.modules.charge.service.ProjectService;
import org.wxjs.matchfee.modules.sys.entity.User;
import org.wxjs.matchfee.modules.sys.utils.UserUtils;

/**
 * 征收Controller
 * @author GLQ
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/charge/charge")
public class ChargeController extends BaseController {

	@Autowired
	private ChargeService chargeService;
	
	@Autowired
	private ProjectService projectService;
	
	@ModelAttribute
	public Charge get(@RequestParam(required=false) String id) {
		Charge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = chargeService.get(id);
		}
		if (entity == null){
			entity = new Charge();
		}
		return entity;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list", ""})
	public String list(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/chargeList";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"mylist"})
	public String mylist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		
		charge.setReportStaff(user);
		
		if(charge.getDateFrom() == null){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.MONTH, -6);
			charge.setDateFrom(cal.getTime());
		}
		
		if(charge.getDateTo() == null){
			Calendar cal=Calendar.getInstance();
			charge.setDateTo(cal.getTime());			
		}
		
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/myChargeList";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"list4Project"})
	public String list4Project(Charge charge, HttpServletRequest request, HttpServletResponse response,  HttpSession httpSession, Model model) {
		
		charge.setProject((Project)httpSession.getAttribute("project"));
		
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/chargeList";
	}

	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "form")
	public String form(Charge charge, Model model) {
		model.addAttribute("charge", charge);
		return "modules/charge/chargeForm";
	}

	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "save")
	public String save(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		chargeService.save(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "toProjectList")
	public String toProjectList(Project project, Model model, RedirectAttributes redirectAttributes) {
		
		User user = UserUtils.getUser();
		if(user.isQyUser()){
			
		}else{
			
		}
		
		return "redirect:"+Global.getAdminPath()+"/charge/project/list?repage";
	}	
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "create")
	public String create(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setReportStaff(user);
		
		Project project = projectService.getByPrjNum(charge.getProject().getPrjNum());
		
		charge.setProject(project);
		charge.setReportEntity(project.getBuildCorpName());
		charge.setReportDate(Calendar.getInstance().getTime());
		charge.setStatus(Global.CHARGE_STATUS_EDIT);
		
		chargeService.save(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}	
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "reportSave")
	public String reportSave(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_EDIT);
		return this.report(charge, model, redirectAttributes);
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "reportSubmit")
	public String reportSubmit(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_TO_CALCULATE);
		return this.report(charge, model, redirectAttributes);
	}
	
	private String report(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setReportStaff(user);
		
		Project project = projectService.getByPrjNum(charge.getProject().getPrjNum());
		
		charge.setProject(project);
		charge.setReportEntity(project.getBuildCorpCode());
		charge.setReportDate(Calendar.getInstance().getTime());
		
		chargeService.updateReport(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "calculateReject")
	public String calculateReject(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_REJECT);
		return this.calculate(charge, model, redirectAttributes);
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "calculatePass")
	public String calculatePass(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_TO_APPROVE);
		return this.calculate(charge, model, redirectAttributes);
	}
	
	private String calculate(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setCalStaff(user);
		
		charge.setCalDate(Calendar.getInstance().getTime());
		
		chargeService.updateCalculate(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "approve")
	public String approve(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setApproveStaff(user);

		charge.setApproveDate(Calendar.getInstance().getTime());
		
		charge.setStatus(Global.CHARGE_STATUS_TO_CONFIRM);
		
		chargeService.updateApprove(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "confirm")
	public String confirm(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setConfirmStaff(user);
		
		charge.setConfirmDate(Calendar.getInstance().getTime());
		
		charge.setStatus(Global.CHARGE_STATUS_CONFIRMED);
		
		chargeService.updateConfirm(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "close")
	public String close(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		charge.setStatus(Global.CHARGE_STATUS_CLOSE);
		
		chargeService.updateStatus(charge);
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "delete")
	public String delete(Charge charge, RedirectAttributes redirectAttributes) {
		chargeService.delete(charge);
		addMessage(redirectAttributes, "删除征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/?repage";
	}

}