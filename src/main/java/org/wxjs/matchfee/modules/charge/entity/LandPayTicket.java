/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.common.utils.Util;

/**
 * LandPayTicketEntity
 * @author GLQ
 * @version 2017-12-11
 */
public class LandPayTicket extends DataEntity<LandPayTicket> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String prjNum;		// 项目代码
	private String ticketNo;		// 票据号
	private String path;		// 保存路径
	private String agencyAgreement;		// 代收费协议
	private String area;		// 面积（平米）
	private String money;		// 金额（元）
	private Date payDate;		// 缴费日期
	
	public LandPayTicket() {
		super();
	}

	public LandPayTicket(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="项目代码长度必须介于 1 和 64 之间")
	public String getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(String prjNum) {
		this.prjNum = prjNum;
	}
	
	@Length(min=1, max=64, message="票据号长度必须介于 1 和 64 之间")
	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@Length(min=1, max=512, message="保存路径长度必须介于 1 和 512 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFilename(){
		return Util.getFilename(this.path);
	}
	
	@Length(min=1, max=512, message="代收费协议长度必须介于 1 和 512 之间")
	public String getAgencyAgreement() {
		return agencyAgreement;
	}

	public void setAgencyAgreement(String agencyAgreement) {
		this.agencyAgreement = agencyAgreement;
	}
	
	public String getAgencyAgreementFilename(){
		return Util.getFilename(this.agencyAgreement);
	}
	
	public String getArea() {
		return Util.formatDecimal(area, Global.DecimalFormat);
	}

	public void setArea(String area) {
		this.area = area;
		
	}
	
	public String getMoney() {
		return Util.formatDecimal(money, Global.DecimalFormat);
	}

	public void setMoney(String money) {
		this.money = money;
		
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="缴费日期不能为空")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
}