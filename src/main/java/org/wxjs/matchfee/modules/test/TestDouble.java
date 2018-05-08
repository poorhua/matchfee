package org.wxjs.matchfee.modules.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringEscapeUtils;
import org.wxjs.matchfee.common.config.Global;
import org.wxjs.matchfee.common.utils.Util;

public class TestDouble {
	
	public static double getDouble(Object obj){
		return getDouble(obj,0);
	}
	
	public static double getDouble(Object obj, double default_value){
		double rst = default_value;
		if(obj!=null){
			try{
				rst = Double.parseDouble(getString(obj,""));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return rst;
	}
	
	public static String getMoney(String money) {
		return Util.formatDecimal(money, Global.DecimalFormat);
	}
	
    public static String getString(Object obj,String default_value){
    	String rst = default_value;
    	if(obj!=null){
    		String str = obj.toString().trim();
    		if(!(str.equals("")||str.equalsIgnoreCase("null"))){
    			rst = str;
    		}
    	}
    	return rst;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(getMoney("141431110.8"));
		
		double d = getDouble("141431110.8");
		
		System.out.println(d);
		
		System.out.println(Util.formatDecimal(d, Global.DecimalFormat));
		
		try {
			String htmlStr = "XDG&mdash;2009&mdash;68号地块开发建设（三期）项目";

			System.out.println(URLDecoder.decode(htmlStr,"UTF-8"));
			System.out.println(StringEscapeUtils.unescapeHtml4(htmlStr));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
