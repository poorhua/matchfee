package org.wxjs.matchfee.common.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * bean<->xml互转工具类
 * @author zlg
 * @date 2017年6月20日
 * modify history
 */
public class XmlHelper {
	
	 @SuppressWarnings("unchecked")  
    public static <T> T toObj(Class<T> clazz, String xml) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(new Class[] { clazz });
            InputStream buf = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            return (T) context.createUnmarshaller().unmarshal(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String toXml(T obj) {
        try {
            StringWriter write = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(new Class[] { obj
                    .getClass() });
            context.createMarshaller().marshal(obj, write);
            return write.getBuffer().toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }
}
