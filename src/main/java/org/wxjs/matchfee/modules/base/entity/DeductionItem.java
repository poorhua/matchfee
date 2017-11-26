/**
 * Copyright &copy; 2012-2016 千里目 All rights reserved.
 */
package org.wxjs.matchfee.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import org.wxjs.matchfee.common.persistence.DataEntity;

/**
 * deductionitemEntity
 * @author GLQ
 * @version 2017-11-24
 */
public class DeductionItem extends DataEntity<DeductionItem> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	
	public DeductionItem() {
		super();
	}

	public DeductionItem(String id){
		super(id);
	}

	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}