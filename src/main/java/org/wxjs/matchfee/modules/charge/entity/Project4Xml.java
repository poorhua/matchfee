package org.wxjs.matchfee.modules.charge.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.util.Base64;
import org.wxjs.matchfee.common.persistence.DataEntity;

@XmlRootElement(name = "project")
public class Project4Xml{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="PrjNum") 
	private String prj_num;
	
	@XmlElement(name="PrjName") 
	private String prj_name;

	@XmlElement(name="BuildCorpName") 
	private String build_corp_name;
	
	@XmlElement(name="BuildCorpCode") 
	private String build_corp_code;
	
	@XmlElement(name="programme_address") 
	private String prj_address;
	
	@XmlElement(name="lxr") 
	private String contact;
	
	@XmlElement(name="yddh") 
	private String mobile;
	
	public Project4Xml(){
		
	}
	
	public String getPrj_num() {
		return prj_num;
	}

	public String getPrj_name() {
		return prj_name;
	}

	public String getBuild_corp_name() {
		return build_corp_name;
	}

	public String getBuild_corp_code() {
		return build_corp_code;
	}
	
	public String getPrj_address() {
		return prj_address;
	}

	public String getContact() {
		return contact;
	}

	public String getMobile() {
		return mobile;
	}

	public Project toProject(){
		Project rst = new Project();
		
		rst.setBuildCorpCode(this.decode(this.build_corp_code));
		rst.setBuildCorpName(this.decode(this.build_corp_name));
		rst.setPrjAddress(this.decode(this.prj_address));
		rst.setPrjName(this.decode(this.prj_name));
		rst.setPrjNum(this.decode(this.prj_num));
		rst.setContact(this.decode(this.contact));
		rst.setMobile(this.decode(this.mobile));
		
		return rst;
	}
	
	private String decode(String src){
		String rst = "";
		if(!StringUtils.isBlank(src)){
			byte[] bytes =  Base64.decode(src);
			
			rst = new String(bytes);			
		}
		
		return rst;
	}


}
