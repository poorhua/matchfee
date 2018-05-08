/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;


import org.apache.commons.collections.ListUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.common.utils.DateUtils;
import org.wxjs.matchfee.common.utils.Util;
import org.wxjs.matchfee.modules.sys.entity.User;
import org.wxjs.matchfee.modules.sys.utils.DictUtils;

/**
 * 征收Entity
 * @author GLQ
 * @version 2017-11-25
 */
public class Charge extends DataEntity<Charge> {
	
	private static final long serialVersionUID = 1L;
	
	public static final double MatchfeeBasis = Util.getDouble(Global.getConfig("matchfee.basis"));
	
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
	private String landPayMoney;    //国土缴费在本期抵扣金额
	private String status;		// 状态
	
	private Date maxPayDate;		// 缴费时间，最后一个缴费单据的日期
	
	private String dateType;    //日期类型， 1：按缴费时间查询， 其他：按申报日期查询
	
	private Date dateFrom;		// 时间 从
	private Date dateTo;		// 时间 到
	
	private List<OpinionBook> opinionBookList;
	
	private List<ProjectLicense> projectLicenseList;
	
	private List<DeductionDoc> deductionDocList;
	
	private List<ProjectDeduction> projectDeductionList;
	
	private List<PayTicket> payTicketList;
	
	private List<LandPayTicket> landPayTicketList;
	
	private List<Charge> historyCharges;
	
	private double previousRemain = 0;
	
	private boolean opinionBookApproved = false;
	
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
	
	public String getReportDateYYYYMMDD() {
		return DateUtils.formatDate(reportDate, "yyyy-MM-dd");
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
	
	@Length(min=0, max=256, message="付款凭证保存路径长度必须介于 0 和 256 之间")
	public String getPayTicketPath() {
		return payTicketPath;
	}

	public void setPayTicketPath(String payTicketPath) {
		this.payTicketPath = payTicketPath;
	}
	
	public String getCalMoney() {
		return Util.formatDecimal(calMoney, Global.DecimalFormat);
	}

	public void setCalMoney(String calMoney) {
		this.calMoney = calMoney;
	}
	
	public String getPayMoney() {
		return Util.formatDecimal(payMoney, Global.DecimalFormat);
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	
	public String getLandPayMoney() {
		return landPayMoney;
	}

	public void setLandPayMoney(String landPayMoney) {
		this.landPayMoney = landPayMoney;
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
		String label = DictUtils.getDictLabel(this.status, "charge_status", "");
		
		if("40".equals(this.status)){
			if(this.getMoneyGap() == 0){
				label = "已缴费（已缴清）";
			}else{
				label = "已缴费（待清算）";
			}
		}
		
		return label;
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
	
	@JsonIgnore
	public String getIsMultipleStatus(){
		return this.isMultiple(this.status)?"1":"0";
	}
	
	@JsonIgnore
	public List<String> getStatuses(){
		List<String> rst = Lists.newArrayList();
		
		if(!this.status.equals("")){
			String[] ids = this.status.split(",");
			for(String id: ids){
				rst.add(id);
			}
		}
		
		return rst;
	}
	
	private boolean isMultiple(String str){
		boolean rst = false;
		if(str!=null && !str.equals("")){
			String[] arr = str.split(",");
			if(arr.length>1){
				rst = true;
			}
		}
		return rst;		
	}
	
	public Date getMaxPayDate() {
		return maxPayDate;
	}

	public void setMaxPayDate(Date maxPayDate) {
		this.maxPayDate = maxPayDate;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
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

	public List<PayTicket> getPayTicketList() {
		return payTicketList;
	}

	public void setPayTicketList(List<PayTicket> payTicketList) {
		this.payTicketList = payTicketList;
	}	
	
	public List<LandPayTicket> getLandPayTicketList() {
		return landPayTicketList;
	}

	public void setLandPayTicketList(List<LandPayTicket> landPayTicketList) {
		this.landPayTicketList = landPayTicketList;
	}

	public double getMoneyGap(){
		double gap = Util.getDouble(this.calMoney) - Util.getDouble(this.payMoney);
		if(gap < 0.01 && gap > -0.01){
			gap = 0;
		}
		return gap;
	}
	
	public String getMoneyGapDisplay(){
		return Util.formatMoneyArea(this.getMoneyGap());
	}

	public double getPreviousRemain() {
		return previousRemain;
	}
	
	public String getPreviousRemainDisplay() {
		return Util.formatMoneyArea(previousRemain);
	}

	public void setPreviousRemain(double previousRemain) {
		this.previousRemain = previousRemain;
	}
	
	public String getChargeSummary(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(DateUtils.formatDate(this.reportDate));
		buffer.append(" ");
		buffer.append(this.getStatusLabel());
		return buffer.toString();
	}
	
	public String getProjectLicenses(){
		StringBuffer buffer = new StringBuffer();
		if(this.projectLicenseList!=null && this.projectLicenseList.size()>0){
			for(ProjectLicense item : this.projectLicenseList){
				buffer.append(",");
				buffer.append(item.getDocumentNoDisplay());
			}
		}
		return buffer.length()>1?buffer.substring(1):"";
	}
	
	public String getCalMoneyLessEqualZero(){
		float f = Util.getFloat(this.calMoney);
		return f<=0 ? "1":"0";
	}

	public List<Charge> getHistoryCharges() {
		return historyCharges;
	}

	public void setHistoryCharges(List<Charge> historyCharges) {
		this.historyCharges = historyCharges;
	}

	public boolean isOpinionBookApproved() {
		return opinionBookApproved;
	}

	public void setOpinionBookApproved(boolean opinionBookApproved) {
		this.opinionBookApproved = opinionBookApproved;
	}
	
}