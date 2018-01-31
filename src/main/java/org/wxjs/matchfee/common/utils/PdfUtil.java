package org.wxjs.matchfee.common.utils;

import java.awt.Color;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PdfUtil {
	
    private static BaseFont bfChinese;
    
    static{
    	try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static Font getTitle1Font(boolean bold){
    	int fontType = Font.NORMAL;
    	if(bold){
    		fontType = Font.BOLD;
    	}
    	return new Font(bfChinese, 19, fontType);
    }
    
    public static Font getTitle2Font(boolean bold){
    	int fontType = Font.NORMAL;
    	if(bold){
    		fontType = Font.BOLD;
    	}
    	return new Font(bfChinese, 16, fontType);
    }
    
    public static Font getTitle3Font(boolean bold){
    	int fontType = Font.NORMAL;
    	if(bold){
    		fontType = Font.BOLD;
    	}
    	return new Font(bfChinese, 14, fontType);
    }
    
    public static Font getTextFont(boolean bold){
    	int fontType = Font.NORMAL;
    	if(bold){
    		fontType = Font.BOLD;
    	}
    	return new Font(bfChinese, 10, fontType);
    }
    
    public static PdfPTable generateTable(String[] headers, Font headerFont, List<String[]> items, Font rowFont, float[] widths, int tableWidth) throws DocumentException{
    	return generateTable(headers, headerFont, items, rowFont, widths, tableWidth, false);
    }
    
    public static PdfPTable generateTable(String[] headers, Font headerFont, List<String[]> items, Font rowFont, float[] widths, int tableWidth, boolean headerCenter) throws DocumentException{
    	int columns = widths.length;
    	//int rows = items.size()+1;
    	
        PdfPTable table = new PdfPTable(columns);
        table.setWidths(widths);
        table.setWidthPercentage(tableWidth);
        
        Phrase phrase;
        PdfPCell cell;
        
        if(headers!=null){
            //write header
            for(String header: headers){
            	phrase = new Phrase(header, headerFont);
            	cell = new PdfPCell(phrase);
            	cell.setBorderWidth(1);
            	if(headerCenter){
            		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            	}else{
            		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            	}
            	
            	//cell.setBackgroundColor(Color.lightGray);
            	table.addCell(cell);
            }           	
        }
	
        if(items!=null){
        	//write items
        	for(String[] strs: items){
        		for(String str:strs){
                	phrase = new Phrase(str, rowFont);
                	cell = new PdfPCell(phrase);
                	cell.setBorderWidth(1);
                	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                	table.addCell(cell);
        		}
        	}        	
        }
    	
    	return table;
    }
    
    public static PdfPTable generateTable4Padding() throws DocumentException{
        PdfPTable table = new PdfPTable(1);
        
        Phrase phrase;
        PdfPCell cell;
        
    	phrase = new Phrase(" ");
    	cell = new PdfPCell(phrase);
    	cell.setBorderWidth(0);
    	table.addCell(cell); 	

    	return table;
    }
    
    public static String toPlain(String src){
		if(!StringUtils.isBlank(src)){
			return src.replaceAll("<br>", "\n").replace("<BR>", "\n");
		}else{
			return src;
		}
    }

}

