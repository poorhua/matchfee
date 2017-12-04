/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;

import org.wxjs.matchfee.common.persistence.DataEntity;

/**
 * 结算清单Entity
 * @author XYM
 * @version 2017-12-04
 */
public class Tchecklist extends DataEntity<Tchecklist> {
	
	private static final long serialVersionUID = 1L;
	private String projectname;		// 项目名称
	private String companyname;		// 公司代码
	private String area;		// 建筑面积
	private String assessment;		// 应缴费用
	private String derate;		// 减免费用
	private String pay;		// 已缴费用
	private String balancedue;		// 结欠费用
	
	public Tchecklist() {
		super();
	}

	public Tchecklist(String id){
		super(id);
	}

	@Length(min=1, max=32, message="项目名称长度必须介于 1 和 32 之间")
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	@Length(min=1, max=32, message="公司代码长度必须介于 1 和 32 之间")
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	@Length(min=0, max=11, message="建筑面积长度必须介于 0 和 11 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=11, message="应缴费用长度必须介于 0 和 11 之间")
	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	
	@Length(min=0, max=11, message="减免费用长度必须介于 0 和 11 之间")
	public String getDerate() {
		return derate;
	}

	public void setDerate(String derate) {
		this.derate = derate;
	}
	
	@Length(min=0, max=11, message="已缴费用长度必须介于 0 和 11 之间")
	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}
	
	@Length(min=0, max=11, message="结欠费用长度必须介于 0 和 11 之间")
	public String getBalancedue() {
		return balancedue;
	}

	public void setBalancedue(String balancedue) {
		this.balancedue = balancedue;
	}
	
}