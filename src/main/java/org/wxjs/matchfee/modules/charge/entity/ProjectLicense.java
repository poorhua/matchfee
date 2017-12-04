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
 * 工程许可证Entity
 * @author GLQ
 * @version 2017-12-03
 */
public class ProjectLicense extends DataEntity<ProjectLicense> {
	
	private static final long serialVersionUID = 1L;
	private String chargeId;		// 征收代码
	private String name;		// 名称
	private String prjNum;		// 项目代码
	private String documentNo;		// 文件编号
	private String path;		// 保存路径
	private Date documentDate;		// 文档日期
	private String upArea;		// 地上面积（平米）
	private String downArea;		// 地下面积（平米）
	
	public ProjectLicense() {
		super();
	}

	public ProjectLicense(String id){
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
	
	@Length(min=1, max=256, message="保存路径长度必须介于 1 和 256 之间")
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
	
	public String getUpArea() {
		return upArea;
	}

	public void setUpArea(String upArea) {
		this.upArea = upArea;
	}
	
	public String getDownArea() {
		return downArea;
	}

	public void setDownArea(String downArea) {
		this.downArea = downArea;
	}
	
}