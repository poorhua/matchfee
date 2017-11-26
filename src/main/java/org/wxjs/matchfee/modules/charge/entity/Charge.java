/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.wxjs.matchfee.common.persistence.DataEntity;

/**
 * 征收Entity
 * @author GLQ
 * @version 2017-11-25
 */
public class Charge extends DataEntity<Charge> {
	
	private static final long serialVersionUID = 1L;
	private String prjNum;		// 项目代码
	private String prjName;		// 项目名称
	private String prjAddress;		// 项目地址
	private String reportStaff;		// 申报人
	private String reportEntity;		// 申报单位
	private Date reportDate;		// 申报时间
	private String calStaff;		// 测算人
	private Date calDate;		// 测算时间
	private String approveStaff;		// 审核人
	private Date approveDate;		// 审核时间
	private String confirmStaff;		// 确认人
	private Date confirmDate;		// 确认时间
	private String payTicketPath;		// 付款凭证保存路径
	private String calMoney;		// 测算金额
	private String payMoney;		// 付款金额
	private String status;		// 状态
	
	public Charge() {
		super();
	}

	public Charge(String id){
		super(id);
	}

	@Length(min=1, max=64, message="项目代码长度必须介于 1 和 64 之间")
	public String getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(String prjNum) {
		this.prjNum = prjNum;
	}
	
	@Length(min=1, max=256, message="项目名称长度必须介于 1 和 256 之间")
	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}
	
	@Length(min=1, max=256, message="项目地址长度必须介于 1 和 256 之间")
	public String getPrjAddress() {
		return prjAddress;
	}

	public void setPrjAddress(String prjAddress) {
		this.prjAddress = prjAddress;
	}
	
	@Length(min=1, max=256, message="申报人长度必须介于 1 和 256 之间")
	public String getReportStaff() {
		return reportStaff;
	}

	public void setReportStaff(String reportStaff) {
		this.reportStaff = reportStaff;
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
	
	@Length(min=0, max=256, message="测算人长度必须介于 0 和 256 之间")
	public String getCalStaff() {
		return calStaff;
	}

	public void setCalStaff(String calStaff) {
		this.calStaff = calStaff;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCalDate() {
		return calDate;
	}

	public void setCalDate(Date calDate) {
		this.calDate = calDate;
	}
	
	@Length(min=0, max=256, message="审核人长度必须介于 0 和 256 之间")
	public String getApproveStaff() {
		return approveStaff;
	}

	public void setApproveStaff(String approveStaff) {
		this.approveStaff = approveStaff;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
	@Length(min=0, max=256, message="确认人长度必须介于 0 和 256 之间")
	public String getConfirmStaff() {
		return confirmStaff;
	}

	public void setConfirmStaff(String confirmStaff) {
		this.confirmStaff = confirmStaff;
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
	
	@Length(min=1, max=8, message="状态长度必须介于 1 和 8 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}