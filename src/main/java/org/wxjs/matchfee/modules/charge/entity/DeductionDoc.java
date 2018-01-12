/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.charge.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.common.utils.Util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

/**
 * 征收Entity
 * @author GLQ
 * @version 2017-12-05
 */
public class DeductionDoc extends DataEntity<DeductionDoc> {
	
	private static final long serialVersionUID = 1L;
	private Charge charge;		// 征收代码 父类
	private String name;		// 名称
	private String prjNum;		// 项目代码
	private String documentNo;		// 文件编号
	private String documentType;		// 文件类型
	private String path;		// 保存路径
	private Date documentDate;		// 文档日期
	private List<DeductionDocItem> deductionDocItemList = Lists.newArrayList();		// 子表列表
	
	public DeductionDoc() {
		super();
	}

	public DeductionDoc(String id){
		super(id);
	}

	public DeductionDoc(Charge charge){
		this.charge = charge;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(String prjNum) {
		this.prjNum = prjNum;
	}
	
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
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

	public List<DeductionDocItem> getDeductionDocItemList() {
		return deductionDocItemList;
	}

	public void setDeductionDocItemList(List<DeductionDocItem> deductionDocItemList) {
		this.deductionDocItemList = deductionDocItemList;
	}
	
}