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
import org.wxjs.matchfee.common.utils.DateUtils;
import org.wxjs.matchfee.common.utils.ExportExcelFile;
import org.wxjs.matchfee.common.utils.StringUtils;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.common.utils.excel.ExportExcel;
import org.wxjs.matchfee.common.web.BaseController;
import org.wxjs.matchfee.modules.base.utils.ExportSettlementList;
import org.wxjs.matchfee.modules.charge.entity.Charge;
import org.wxjs.matchfee.modules.charge.entity.DeductionDoc;
import org.wxjs.matchfee.modules.charge.entity.DeductionDocItem;
import org.wxjs.matchfee.modules.charge.entity.LandPayTicket;
import org.wxjs.matchfee.modules.charge.entity.OpinionBook;
import org.wxjs.matchfee.modules.charge.entity.OpinionBookItem;
import org.wxjs.matchfee.modules.charge.entity.PayTicket;
import org.wxjs.matchfee.modules.charge.entity.Project;
import org.wxjs.matchfee.modules.charge.entity.ProjectDeduction;
import org.wxjs.matchfee.modules.charge.entity.ProjectLicense;
import org.wxjs.matchfee.modules.charge.entity.SettlementList;
import org.wxjs.matchfee.modules.charge.service.ChargeService;
import org.wxjs.matchfee.modules.charge.service.DeductionDocItemService;
import org.wxjs.matchfee.modules.charge.service.DeductionDocService;
import org.wxjs.matchfee.modules.charge.service.LandPayTicketService;
import org.wxjs.matchfee.modules.charge.service.OpinionBookItemService;
import org.wxjs.matchfee.modules.charge.service.OpinionBookService;
import org.wxjs.matchfee.modules.charge.service.PayTicketService;
import org.wxjs.matchfee.modules.charge.service.ProjectDeductionService;
import org.wxjs.matchfee.modules.charge.service.ProjectLicenseService;
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
	private DeductionDocService deductionDocService;
	
	@Autowired
	private DeductionDocItemService  deductionDocItemService;
	
	@Autowired
	private OpinionBookService opinionBookService;
	
	@Autowired
	private OpinionBookItemService opinionBookItemService;
	
	@Autowired
	private ProjectDeductionService projectDeductionService;
	
	@Autowired
	private ProjectLicenseService projectLicenseService;
	
	@Autowired
	private PayTicketService payTicketService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private LandPayTicketService landPayTicketService;
	
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
	@RequestMapping(value = {"enterpriselist"})
	public String enterpriselist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		
		if(user.getProject()==null){
			logger.debug("user.getProject()==null");
		}else{
			logger.debug(user.getProject().toString());
		}
		
		Charge chargeParam = new Charge();
		Project projectParam = new Project();
		projectParam.setPrjNum(user.getProject().getPrjNum());
		chargeParam.setProject(projectParam);
		chargeParam.setStatus(Global.CHARGE_STATUS_EDIT
				 + "," + Global.CHARGE_STATUS_REJECT);
		
		
		List<Charge> list = chargeService.findList(chargeParam); 
		model.addAttribute("list", list);
		return "modules/charge/myChargeList_enterprise";
	}	
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"mylist"})
	public String mylist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		
		charge.setReportStaff(user);
		
		charge.setStatus(Global.CHARGE_STATUS_EDIT
				 + "," + Global.CHARGE_STATUS_REJECT);
		
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
	@RequestMapping(value = {"calculatelist"})
	public String calculatelist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//User user = UserUtils.getUser();
		
		//charge.setReportStaff(user);
		
		charge.setStatus(Global.CHARGE_STATUS_TO_CALCULATE);
		
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/myChargeList_postSubmit";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"approvelist"})
	public String approvelist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//User user = UserUtils.getUser();
		
		//charge.setReportStaff(user);
		
		charge.setStatus(Global.CHARGE_STATUS_TO_APPROVE);
		
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/myChargeList_postSubmit";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"confirmlist"})
	public String confirmlist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		charge.setStatus(Global.CHARGE_STATUS_TO_CONFIRM);
		
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/myChargeList_postSubmit";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = {"settlementlist"})
	public String settlementlist(Charge charge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//User user = UserUtils.getUser();
		
		//charge.setReportStaff(user);
		
		charge.setStatus(Global.CHARGE_STATUS_CONFIRMED);
		
		List<Charge> list = chargeService.findList(charge); 
		model.addAttribute("list", list);
		return "modules/charge/myChargeList_postSubmit";
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
			model.addAttribute("project", user.getProject());
			return "modules/charge/projectForm";
		}else{
			return "modules/charge/projectList";
		}
	}	
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "create")
	public String create(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setReportStaff(user);
		
		Project project = (Project)httpSession.getAttribute("project");
		
		charge.setProject(project);
		charge.setReportEntity(project.getBuildCorpName());
		charge.setReportDate(Calendar.getInstance().getTime());
		charge.setStatus(Global.CHARGE_STATUS_EDIT);
		
		chargeService.save(charge);
		addMessage(redirectAttributes, "保存征收成功");
		
		if(user.isQyUser()){
			return "redirect:"+Global.getAdminPath()+"/charge/charge/enterpriselist?repage";
		}else{
			return "redirect:"+Global.getAdminPath()+"/charge/charge/mylist?repage";
		}
		
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "defaultTab")
	public String defaultTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		return this.projectLicenseTab(charge, httpSession, model, redirectAttributes);
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "opinionBookTab")
	public String opinionBookTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		
		String chargeId = this.getChargeId(charge, httpSession);
		
		logger.debug("charge==null: "+(charge==null)+", chargeId: "+chargeId);
		if(charge!=null){
			logger.debug(charge.toString());
		}
		
		if(this.isChargeEmpty(charge) && !StringUtils.isBlank(chargeId)){
			charge = chargeService.get(new Charge(chargeId));
		}
		
		OpinionBook opinionBookParam = new OpinionBook();
		opinionBookParam.setPrjNum(charge.getProject().getPrjNum());
		
		charge.setOpinionBookList(this.opinionBookService.findList(opinionBookParam));
		
		for(OpinionBook item : charge.getOpinionBookList()){
			OpinionBookItem opinionBookItemParam = new OpinionBookItem();
			opinionBookItemParam.setDoc(item);
			item.setOpinionBookItemList(opinionBookItemService.findList(opinionBookItemParam));
		}
		
		model.addAttribute("charge", charge);
		
		httpSession.setAttribute("chargeId", charge.getId());
		
		httpSession.setAttribute("project", charge.getProject());
		
		return "modules/charge/opinionBookTab";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "landPayTicketTab")
	public String landPayTicketTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		
		String chargeId = this.getChargeId(charge, httpSession);
		
		logger.debug("charge==null: "+(charge==null)+", chargeId: "+chargeId);
		if(charge!=null){
			logger.debug(charge.toString());
		}
		
		if(this.isChargeEmpty(charge) && !StringUtils.isBlank(chargeId)){
			charge = chargeService.get(new Charge(chargeId));
		}
		
		LandPayTicket landPayTicketParam = new LandPayTicket();
		landPayTicketParam.setPrjNum(charge.getProject().getPrjNum());
		
		charge.setLandPayTicketList(this.landPayTicketService.findList(landPayTicketParam));
		
		model.addAttribute("charge", charge);
		
		httpSession.setAttribute("chargeId", charge.getId());
		
		model.addAttribute("project", charge.getProject());
		
		return "modules/charge/landPayTicketTab";
	}	
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "projectLicenseTab")
	public String projectLicenseTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		
		String chargeId = this.getChargeId(charge, httpSession);
		
		logger.debug("charge==null: "+(charge==null)+", chargeId: "+chargeId);
		if(charge!=null){
			logger.debug(charge.toString());
		}
		
		if(this.isChargeEmpty(charge) && !StringUtils.isBlank(chargeId)){
			charge = chargeService.get(new Charge(chargeId));
		}
		
		ProjectLicense projectLicenseParam = new ProjectLicense();
		projectLicenseParam.setCharge(new Charge(chargeId));
		
		charge.setProjectLicenseList(this.projectLicenseService.findList(projectLicenseParam));
		
		model.addAttribute("charge", charge);
		
		httpSession.setAttribute("chargeId", charge.getId());
		
		httpSession.setAttribute("project", charge.getProject());
		
		return "modules/charge/projectLicenseTab";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "projectDeductionTab")
	public String projectDeductionTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		
		String chargeId = this.getChargeId(charge, httpSession);
		
		if(this.isChargeEmpty(charge) && !StringUtils.isBlank(chargeId)){
			charge = chargeService.get(new Charge(chargeId));
		}
		
		ProjectDeduction projectDeductionParam = new ProjectDeduction();
		projectDeductionParam.setCharge(new Charge(chargeId));
		
		charge.setProjectDeductionList(this.projectDeductionService.findList(projectDeductionParam));
		
		model.addAttribute("charge", charge);
		
		httpSession.setAttribute("chargeId", charge.getId());
		
		httpSession.setAttribute("project", charge.getProject());
		
		return "modules/charge/projectDeductionTab";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "deductionDocTab")
	public String deductionDocTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		
		String chargeId = this.getChargeId(charge, httpSession);
		
		logger.debug("charge==null: "+(charge==null)+", chargeId: "+chargeId);
		if(this.isChargeEmpty(charge) && !StringUtils.isBlank(chargeId)){
			charge = chargeService.get(new Charge(chargeId));
		}
		
		DeductionDoc deductionDocParam = new DeductionDoc();
		deductionDocParam.setCharge(new Charge(chargeId));
		
		charge.setDeductionDocList(this.deductionDocService.findList(deductionDocParam));
		
		for(DeductionDoc item : charge.getDeductionDocList()){
			DeductionDocItem deductionDocItemParam = new DeductionDocItem();
			deductionDocItemParam.setDoc(item);
			item.setDeductionDocItemList(deductionDocItemService.findList(deductionDocItemParam));
			item.setCharge(charge);
		}
		model.addAttribute("charge", charge);
		
		httpSession.setAttribute("chargeId", charge.getId());
		
		httpSession.setAttribute("project", charge.getProject());
		
		return "modules/charge/deductionDocTab";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "payTicketTab")
	public String payTicketTab(Charge charge, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
		
		PayTicket payTicketParam = new PayTicket();
		
		if(charge==null || StringUtils.isBlank(charge.getId())){
			String chargeId = Util.getString(httpSession.getAttribute("chargeId"));
			payTicketParam.setCharge(new Charge(chargeId));
			charge = chargeService.get(chargeId);
		}else{
			payTicketParam.setCharge(charge);
		}

		
		charge.setPayTicketList(this.payTicketService.findList(payTicketParam));
		
		model.addAttribute("charge", charge);
		
		//logger.debug("charge: "+charge.toString());
		
		return "modules/charge/payTicketTab";
	}
	
	private String getChargeId(Charge charge, HttpSession httpSession){
		String chargeId = charge.getId();
		if(StringUtils.isBlank(chargeId)){
			chargeId = Util.getString(httpSession.getAttribute("chargeId"));
		}
		return chargeId;
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "reportSave")
	public String reportSave(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_EDIT);
		String result = this.report(charge, model, redirectAttributes);
		operationLogService.logApprove(charge.getId(), "保存征收申报", "成功");
		return result;
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "reportSubmit")
	public String reportSubmit(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_TO_CALCULATE);
		String result = this.report(charge, model, redirectAttributes);
		operationLogService.logApprove(charge.getId(), "提交征收申报", "成功");
		return result;
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
		
		if(user.isQyUser()){
			return "redirect:"+Global.getAdminPath()+"/charge/charge/enterpriselist?repage";
		}else{
			return "redirect:"+Global.getAdminPath()+"/charge/charge/mylist?repage";
		}
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "calculateReject")
	public String calculateReject(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_REJECT);
		String result = this.calculate(charge, model, redirectAttributes);
		operationLogService.logApprove(charge.getId(), "测算", "退回, "+charge.getCalMemo());
		return result;
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "calculatePass")
	public String calculatePass(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		charge.setStatus(Global.CHARGE_STATUS_TO_APPROVE);
		String result = this.calculate(charge, model, redirectAttributes);
		operationLogService.logApprove(charge.getId(), "测算", "通过");
		return result;
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
		return "redirect:"+Global.getAdminPath()+"/charge/charge/calculatelist?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "approvePass")
	public String approvePass(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setApproveStaff(user);

		charge.setApproveDate(Calendar.getInstance().getTime());
		
		charge.setStatus(Global.CHARGE_STATUS_TO_CONFIRM);
		
		chargeService.updateApprove(charge);
		
		operationLogService.logApprove(charge.getId(), "审核", "通过");
		
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/approvelist?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "approveReject")
	public String approveReject(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		User user = UserUtils.getUser();
		charge.setApproveStaff(user);

		charge.setApproveDate(Calendar.getInstance().getTime());
		
		charge.setStatus(Global.CHARGE_STATUS_REJECT);
		
		chargeService.updateApprove(charge);
		
		operationLogService.logApprove(charge.getId(), "审核", "退回，"+charge.getApproveMemo());
		
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/approvelist?repage";
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
		
		operationLogService.logApprove(charge.getId(), "缴费确认", "");
		
		addMessage(redirectAttributes, "保存征收成功");
		return "redirect:"+Global.getAdminPath()+"/charge/charge/confirmlist?repage";
	}
	
	@RequiresPermissions("charge:charge:edit")
	@RequestMapping(value = "close")
	public String close(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, charge)){
			return form(charge, model);
		}
		
		charge.setStatus(Global.CHARGE_STATUS_CLOSE);
		
		chargeService.updateStatus(charge);
		
		operationLogService.logApprove(charge.getId(), "关闭", "");
		
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
	
	private boolean isChargeEmpty(Charge charge){
		boolean flag = false;
		if(charge==null){
			flag = true;
		}else if(StringUtils.isBlank(charge.getId())){
			flag = true;
		}
		return flag;
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "showSettlementList")
	public String showSettlementList(Charge charge, Model model, RedirectAttributes redirectAttributes) {
		
		SettlementList settementList = chargeService.settle(charge.getId());
		
		model.addAttribute("settementList", settementList);

		return "modules/charge/settlementList";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "exportPDFSettlementList")
	public String exportPDFSettlementList(Charge charge, HttpServletResponse response,  Model model, RedirectAttributes redirectAttributes) {
		
		SettlementList settementList = chargeService.settle(charge.getId());
		
		model.addAttribute("settementList", settementList);
		
		try {
            String fileName = "结算清单"+DateUtils.getDate("yyyyMMddHHmmss")+".pdf";
            ExportSettlementList export = new ExportSettlementList(settementList);
            export.write(response, fileName);
    		return null;
		} catch (Exception e) {
			logger.error("导出失败", e);
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}		

		return "modules/charge/settlementList";
	}
	
	@RequiresPermissions("charge:charge:view")
	@RequestMapping(value = "exportExcelSettlementList")
	public String exportExcelSettlementList(Charge charge, HttpServletResponse response,  Model model, RedirectAttributes redirectAttributes) {
		
		SettlementList settementList = chargeService.settle(charge.getId());
		
		model.addAttribute("settementList", settementList);
		
		try {
            String fileName = "结算清单"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
            ExportExcelFile.write(fileName, settementList, response);
    		return null;
		} catch (Exception e) {
			logger.error("导出失败", e);
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}		
		return "modules/charge/settlementList";
	}

}