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
import org.wxjs.matchfee.modules.charge.utils.EntityUtils;

/**
 * 缴费凭证Entity
 * @author GLQ
 * @version 2017-12-07
 */
public class PayTicket extends DataEntity<PayTicket> {
	
	private static final long serialVersionUID = 1L;
	private Charge charge;		// 征收代码
	private String prjNum;		// 项目代码
	private String ticketNo;		// 票据号
	private String path;		// 保存路径
	private String money;		// 金额（元）
	private Date payDate;		// 缴费日期
	
	public PayTicket() {
		super();
	}

	public PayTicket(String id){
		super(id);
	}
	
	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}
	
	@Length(min=1, max=64, message="项目代码长度必须介于 1 和 64 之间")
	public String getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(String prjNum) {
		this.prjNum = prjNum;
	}
	
	@Length(min=0, max=64, message="票据号长度必须介于 0和64 之间")
	public String getTicketNo() {
		return ticketNo;
	}
	
	public String getTicketNoDisplay() {
		return EntityUtils.duplicateTag(this.ticketNo, this.duplicateFlag);
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@Length(min=0, max=512, message="保存路径长度必须介于 0 和 512 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFilename(){
		return Util.getFilename(this.path);
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