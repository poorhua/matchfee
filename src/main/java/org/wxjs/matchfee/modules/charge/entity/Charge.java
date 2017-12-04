/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;


import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.modules.sys.entity.User;
import org.wxjs.matchfee.modules.sys.utils.DictUtils;

/**
 * 征收Entity
 * @author GLQ
 * @version 2017-11-25
 */
public class Charge extends DataEntity<Charge> {
	
	private static final long serialVersionUID = 1L;
	private Project project;		// 项目代码
	private User reportStaff;		// 申报人
	private String reportEntity;		// 申报单位
	private Date reportDate;		// 申报时间
	private String reportMemo;		// 申报备注
	private User calStaff;		// 测算人
	private Date calDate;		// 测算时间
	private String calMemo;		// 测算备注
	private User approveStaff;		// 审核人
	private Date approveDate;		// 审核时间
	private String approveMemo;		// 审核备注
	private User confirmStaff;		// 确认人
	private Date confirmDate;		// 确认时间
	private String confirmMemo;		// 确认备注
	private String payTicketPath;		// 付款凭证保存路径
	private String calMoney;		// 测算金额
	private String payMoney;		// 付款金额
	private String status;		// 状态
	
	private Date dateFrom;		// 时间 从
	private Date dateTo;		// 时间 到
	
	private List<OpinionBook> opinionBookList;
	
	private List<ProjectLicense> projectLicenseList;
	
	private List<DeductionDoc> deductionDocList;
	
	private List<ProjectDeduction> projectDeductionList;
	
	public Charge() {
		super();
	}

	public Charge(String id){
		super(id);
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Length(min=1, max=256, message="申报单位长度必须介于 1 和 256 之间")
	public String getReportEntity() {
		return reportEntity;
	}

	public void setReportEntity(String reportEntity) {
		this.reportEntity = reportEntity;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCalDate() {
		return calDate;
	}

	public void setCalDate(Date calDate) {
		this.calDate = calDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	@Length(min=0, max=128, message="付款凭证保存路径长度必须介于 0 和 128 之间")
	public String getPayTicketPath() {
		return payTicketPath;
	}

	public void setPayTicketPath(String payTicketPath) {
		this.payTicketPath = payTicketPath;
	}
	
	public String getCalMoney() {
		return calMoney;
	}

	public void setCalMoney(String calMoney) {
		this.calMoney = calMoney;
	}
	
	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	
	public String getReportMemo() {
		return reportMemo;
	}

	public void setReportMemo(String reportMemo) {
		this.reportMemo = reportMemo;
	}

	public String getCalMemo() {
		return calMemo;
	}

	public void setCalMemo(String calMemo) {
		this.calMemo = calMemo;
	}

	public String getApproveMemo() {
		return approveMemo;
	}

	public void setApproveMemo(String approveMemo) {
		this.approveMemo = approveMemo;
	}

	public String getConfirmMemo() {
		return confirmMemo;
	}

	public void setConfirmMemo(String confirmMemo) {
		this.confirmMemo = confirmMemo;
	}

	@Length(min=1, max=8, message="状态长度必须介于 1 和 8 之间")
	public String getStatus() {
		return status;
	}
	
	public String getStatusLabel() {
		return DictUtils.getDictLabel(this.status, "charge_status", "");
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getReportStaff() {
		return reportStaff;
	}

	public void setReportStaff(User reportStaff) {
		this.reportStaff = reportStaff;
	}

	public User getCalStaff() {
		return calStaff;
	}

	public void setCalStaff(User calStaff) {
		this.calStaff = calStaff;
	}

	public User getApproveStaff() {
		return approveStaff;
	}

	public void setApproveStaff(User approveStaff) {
		this.approveStaff = approveStaff;
	}

	public User getConfirmStaff() {
		return confirmStaff;
	}

	public void setConfirmStaff(User confirmStaff) {
		this.confirmStaff = confirmStaff;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<OpinionBook> getOpinionBookList() {
		return opinionBookList;
	}

	public void setOpinionBookList(List<OpinionBook> opinionBookList) {
		this.opinionBookList = opinionBookList;
	}

	public List<ProjectLicense> getProjectLicenseList() {
		return projectLicenseList;
	}

	public void setProjectLicenseList(List<ProjectLicense> projectLicenseList) {
		this.projectLicenseList = projectLicenseList;
	}

	public List<DeductionDoc> getDeductionDocList() {
		return deductionDocList;
	}

	public void setDeductionDocList(List<DeductionDoc> deductionDocList) {
		this.deductionDocList = deductionDocList;
	}

	public List<ProjectDeduction> getProjectDeductionList() {
		return projectDeductionList;
	}

	public void setProjectDeductionList(List<ProjectDeduction> projectDeductionList) {
		this.projectDeductionList = projectDeductionList;
	}
	
}