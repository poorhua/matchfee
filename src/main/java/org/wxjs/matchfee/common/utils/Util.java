package org.wxjs.matchfee.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;


public class Util {
	private static final String BasicCharSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BasicCharSetLen = BasicCharSet.length();

	public static String getRandomStr(int len){
		StringBuffer buffer = new StringBuffer(len);
		for(int i=0;i<len;i++){
			int ra = (int)(Math.random() * BasicCharSetLen);
			buffer.append(BasicCharSet.charAt(ra));
		}
		return buffer.toString();
	}
	
    /**
     * Replaces all occurrence of a specified character in a string with
     * a specified string.
     *
     * @param oldStr     the string to be processed.
     * @param ch         the character to be replaced.
     * @param replaceStr the string that replaces the to-be-replaced
     *                   character.
     *
     * @return           a new string that is derived FROM <code>oldStr</code>
     *                   by replacing every occurrence of <code>ch</code>
     *                   with <code>replaceStr</code>; <code>oldStr</code>
     *                   if <code>ch</code> does not occur in
     *                   <code>oldStr</code>.
     */
    static public String replaceChar(String oldStr, char ch, String replaceStr)
    {
    	if (oldStr == null || oldStr.length() == 0 ||
    	    replaceStr == null || replaceStr.length() == 0){
    		return oldStr;
    	}

    	char b[]  = new char[oldStr.length()];
    	oldStr.getChars(0, oldStr.length(), b, 0);

        char b2[] = new char[replaceStr.length()];
        replaceStr.getChars(0, replaceStr.length(), b2, 0);

        char buffer[] = new char[0];

        boolean found = false;
        int idx = -1, offset=0;;
        for (int i=0; i<b.length; i++) {
            if (b[i] == ch) {
            	if (!found) found = true;
                int size = (i-offset);
                char bb[] = new char[buffer.length + size + b2.length];
	        System.arraycopy(buffer, 0, bb, 0, buffer.length);
	        System.arraycopy(b, offset, bb, buffer.length, size);
	        System.arraycopy(b2, 0, bb, buffer.length + size, b2.length);
	        buffer = bb;
	        offset = i+1;
            }
        }

        if (!found){
        	return oldStr;
        }

        if (offset < b.length) {
            int size = (b.length-offset);
            char bb[] = new char[buffer.length + size];
            System.arraycopy(buffer, 0, bb, 0, buffer.length);
            System.arraycopy(b, offset, bb, buffer.length, size);
	    buffer = bb;
        }

        return new String(buffer);
    }


    /**
     * Replaces all occurrence of a specified string in a string with another
     * specified string.
     *
     * @param oldStr     the string to be processed.
     * @param str        the string to be replaced.
     * @param replaceStr the string that replaces the to-be-replaced string.
     *
     * @return           a new string that is derived FROM <code>oldStr</code>
     *                   by replacing every occurrence of <code>str</code>
     *                   with <code>replaceStr</code>; <code>oldStr</code>
     *                   if <code>str</code> does not occur in
     *                   <code>oldStr</code>.
     */
    static public String replaceString(String oldStr, String str, String replaceStr)
    {
    	if (oldStr == null || oldStr.length() == 0 || str == null || str.length() == 0 ||
    	    replaceStr == null || replaceStr.length() == 0){
    		return oldStr;
    	}

    	int fromIndex=0, idx, len=str.length();
    	StringBuffer sb = new StringBuffer();
    	boolean found = false;
    	while ((idx = oldStr.indexOf(str, fromIndex)) != -1) {
            if (!found){
            	found = true;
            }
            sb.append(oldStr.substring(fromIndex, idx));
            sb.append(replaceStr);
    	    fromIndex = idx + len;
    	}
    	if (!found){
    		return oldStr;
    	}

    	if (fromIndex < oldStr.length()) {
    	    sb.append(oldStr.substring(fromIndex));
    	}

    	return sb.toString();
    }
    
	/**
	 * get String value FROM object
	 * @param obj
	 * @param default_value
	 * @return
	 */
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
    
    /**
     * get String value FROM object
     * @param obj
     * @return
     */
    public static String getString(Object obj){
    	return getString(obj,"");
    }
    
	/**
	 * get Integer value FROM object
	 * @param obj
	 * @param default_value
	 * @return
	 */
	public static int getInteger(Object obj, int default_value){
		int rst = default_value;
		if(obj!=null){
			try{
				rst = Integer.parseInt(getString(obj,""));
			}catch(Exception ex){
			}
		}
		return rst;
	}
	
	/**
	 * get Integer value FROM object
	 * @param obj
	 * @return
	 */
	public static int getInteger(Object obj){
		return getInteger(obj,0);
	}
	
	/**
	 * get Boolean value FROM object
	 * @param obj
	 * @param default_value
	 * @return
	 */
	public static boolean getBoolean(Object obj, boolean default_value){
		boolean rst = default_value;
		if(obj!=null){
			try{
				if(getString(obj,"").equalsIgnoreCase("true")){
					rst = true;
				}else if(getString(obj,"").equalsIgnoreCase("false")){
					rst = false;
				}
			}catch(Exception ex){
			}
		}
		return rst;
	}
	
	/**
	 * get Boolean value FROM object
	 * @param obj
	 * @return
	 */
	public static boolean getBoolean(Object obj){
		return getBoolean(obj,false);
	}
	
	/**
	 * get Long value FROM object
	 * @param obj
	 * @param default_value
	 * @return
	 */
	public static long getLong(Object obj, long default_value){
		long rst = default_value;
		if(obj!=null){
			try{
				rst = Long.parseLong(getString(obj,""));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return rst;
	}
	
	/**
	 * get Long value FROM object
	 * @param obj
	 * @return
	 */
	public static long getLong(Object obj){
		return getLong(obj,0);
	}
	
	/**
	 * get Float value FROM object
	 * @param obj
	 * @param default_value
	 * @return
	 */
	public static float getFloat(Object obj, float default_value){
		float rst = default_value;
		if(obj!=null){
			try{
				rst = Float.parseFloat(getString(obj,""));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return rst;
	}
	
	/**
	 * get Float value FROM object
	 * @param obj
	 * @return
	 */
	public static float getFloat(Object obj){
		return getFloat(obj,0);
	}
	
	/**
	 * get Double value FROM object
	 * @param obj
	 * @param default_value
	 * @return
	 */
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
	
	/**
	 * get Double value FROM object
	 * @param obj
	 * @return
	 */
	public static double getDouble(Object obj){
		return getDouble(obj,0);
	}
	
	public static String getFilename(String path){
		String rst = "";
		if(!StringUtils.isBlank(path)){
			String pathDe = "";
			try {
				pathDe = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int index = pathDe.lastIndexOf("/");
			if(index > 0){
				rst = pathDe.substring(index + 1);
			}
		}
		return rst;
	}
	
	/**
	 * 
	 * @param f
	 * @param pattern sample 0.0
	 * @return
	 */
	public static String formatDecimal(float f, String pattern){
		DecimalFormat df = new DecimalFormat();
		df.applyPattern(pattern);
		return df.format(f);
	}
	
	public static String formatDecimal(String floatStr, String pattern){
		float f = Util.getFloat(floatStr);
		return formatDecimal(f, pattern);
	}
	
	public static String requestParamDecode(String param){
		String rst = "";
		try {
			rst = new String(param.getBytes("iso-8859-1"),"utf-8");
			rst = java.net.URLDecoder.decode(rst,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rst;
		
	}
}

