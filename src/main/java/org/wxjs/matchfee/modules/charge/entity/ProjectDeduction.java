/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import org.wxjs.matchfee.common.persistence.DataEntity;

/**
 * 项目抵扣项Entity
 * @author GLQ
 * @version 2017-12-03
 */
public class ProjectDeduction extends DataEntity<ProjectDeduction> {
	
	private static final long serialVersionUID = 1L;
	private String chargeId;		// 征收代码
	private String name;		// 名称
	private String prjNum;		// 项目代码
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

	@Length(min=1, max=11, message="征收代码长度必须介于 1 和 11 之间")
	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	
	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
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
	
	@Length(min=1, max=8, message="文件编号长度必须介于 1 和 8 之间")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	@Length(min=1, max=128, message="保存路径长度必须介于 1 和 128 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getMoney() {
		return money;
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