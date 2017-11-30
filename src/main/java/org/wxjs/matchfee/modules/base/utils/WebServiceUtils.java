package org.wxjs.matchfee.modules.base.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.utils.XmlHelper;
import org.wxjs.matchfee.modules.charge.entity.Project;
import org.wxjs.matchfee.modules.charge.entity.Project4Xml;

public class WebServiceUtils {
	
	public static boolean enterpriceLogin(String prjNum, String prjPassword){
		
		boolean flag = false;
		
    	Client c;
		try {
			c = new Client(new URL(Global.getConfig("enterprice.login.validate.wsdl")));
			Object[] results = c.invoke(Global.getConfig("webservice.method.MatchFeeLogin"), new Object[]{prjNum, prjPassword});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rst;
		
	}

}
