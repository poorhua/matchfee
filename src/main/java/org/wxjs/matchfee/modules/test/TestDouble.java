package org.wxjs.matchfee.modules.test;

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

	}

}
