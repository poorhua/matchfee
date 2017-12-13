/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.entity;

import org.hibernate.validator.constraints.Length;
import org.wxjs.matchfee.common.persistence.DataEntity;
import org.wxjs.matchfee.common.utils.DateUtils;

/**
 * operationlogEntity
 * @author GLQ
 * @version 2017-12-13
 */
public class OperationLog extends DataEntity<OperationLog> {
	
	private static final long serialVersionUID = 1L;
	private String chargeId;		// 征收代码
	private String content;		// 内容
	
	public OperationLog() {
		super();
	}

	public OperationLog(String id){
		super(id);
	}
	
	public OperationLog(String chargeId, String operator, String operation, String result) {
		super();
		this.chargeId = chargeId;
		this.content = DateUtils.getDate("yyyy-MM-dd HH:mm:ss")
				+" "+operator+" "+operation+" "+result;
	}

	@Length(min=0, max=64, message="征收代码长度必须介于 0 和 64 之间")
	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	
	@Length(min=0, max=256, message="内容长度必须介于 0 和 256 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}