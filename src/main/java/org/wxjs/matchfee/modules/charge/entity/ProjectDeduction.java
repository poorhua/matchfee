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
 * 项目抵扣项Entity
 * @author GLQ
 * @version 2017-12-03
 */
public class ProjectDeduction extends DataEntity<ProjectDeduction> {
	
	private static final long serialVersionUID = 1L;
	private Charge charge;		// 征收代码
	private String name;		// 名称
	private Project project;		// 项目代码
	private String documentNo;		// 文件编号
	private String path;		// 保存路径
	private Date documentDate;		// 文档日期
	private String area;		// 面积（平米）
	private String money;		// 金额（元）
	private String deductionType;		// 抵扣方式
	
	public ProjectDeduction() {
		super();
	}

	public ProjectDeduction(String id){
		super(id);
	}
	
	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Length(min=0, max=64, message="文件编号长度必须介于 0 和 64 之间")
	public String getDocumentNo() {
		return documentNo;
	}
	
	public String getDocumentNoDisplay() {
		return EntityUtils.duplicateTag(this.documentNo);
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="文档日期不能为空")
	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
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
	
	@Length(min=1, max=1, message="抵扣方式长度必须介于 1 和 1 之间")
	public String getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	
}