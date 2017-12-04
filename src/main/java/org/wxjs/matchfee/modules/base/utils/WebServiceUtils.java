package org.wxjs.matchfee.modules.base.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.utils.XmlHelper;
import org.wxjs.matchfee.modules.charge.entity.Project;
import org.wxjs.matchfee.modules.charge.entity.Project4Xml;

import com.google.common.collect.Lists;

public class WebServiceUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(WebServiceUtils.class);
	
	public static boolean enterpriceLogin(String prjNum, String prjPassword){
		
		boolean flag = false;
		
    	Client c;
		try {
			c = new Client(new URL(Global.getConfig("enterprice.login.validate.wsdl")));
			Object[] results = c.invoke(Global.getConfig("webservice.method.MatchFeeLogin"), new Object[]{prjNum, prjPassword});
			
	    	if(results!=null){
	    		for(Object obj : results){
	    			String objStr = (String)obj;
	    			flag = objStr.trim().startsWith("000");
	    			break;
	    		}
	    	}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
	
	public static Project getProjectInfo(String prjNum){
		
		Project rst = null;
		
		String user = Global.getConfig("webservice.user");
		
		String password = Global.getConfig("webservice.password");
		
    	Client c;
		try {
			c = new Client(new URL(Global.getConfig("enterprice.login.validate.wsdl")));
			Object[] results = c.invoke(Global.getConfig("webservice.method.queryProjectInfo"), new Object[]{user, password, prjNum});
			
			String xmlSrc = (String)results[0];
			
			String xml = xmlSrc.substring(xmlSrc.indexOf("<project>"), xmlSrc.indexOf("</project>")+10);
			
			Project4Xml project4Xml = XmlHelper.toObj(Project4Xml.class, xml);
			
			rst = project4Xml.toProject();
			
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rst;
		
	}
	
	public static List<Project> getProjectList(Project project){
		
		List<Project> rst = Lists.newArrayList();
		
		String prjNum = project.getPrjNum();
		String prjName = project.getPrjName();
		String buildCorpCode = project.getBuildCorpCode();
		String buildCorpName = project.getBuildCorpName();
		String location = project.getPrjAddress();
		
		String user = Global.getConfig("webservice.user");
		
		String password = Global.getConfig("webservice.password");
		
    	Client c;
		try {
			c = new Client(new URL(Global.getConfig("enterprice.login.validate.wsdl")));
			Object[] results = c.invoke(Global.getConfig("webservice.method.queryProjectListEx"), new Object[]{user, password, prjNum, prjName, buildCorpCode, buildCorpName, location});
			
			String xmlSrc = (String)results[0];
			
			xmlSrc = xmlSrc.replaceAll("<row>", "<project>").replaceAll("</row>", "</project>");
			
			int indexPrj1 = xmlSrc.indexOf("<project>");
			int indexPrj2 = xmlSrc.indexOf("</project>", indexPrj1);
			
			while(indexPrj1>0){
				String subStr = xmlSrc.substring(indexPrj1, indexPrj2+10);
				
				logger.debug(subStr);
				
				Project4Xml p4x = XmlHelper.toObj(Project4Xml.class, subStr);
				if(p4x!=null){
					rst.add(p4x.toProject());
				}
				
				indexPrj1 = xmlSrc.indexOf("<project>", indexPrj2);
				indexPrj2 = xmlSrc.indexOf("</project>", indexPrj1);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rst;
		
	}

}
