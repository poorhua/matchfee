package org.wxjs.matchfee.common.utils;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class creditUtil {
	public static void createCreditPDF(String addressName,String companyData, List<String[]> items) {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(addressName));
			document.open();
			// ����
			Paragraph title = new Paragraph("���л�����ʩ���׷ѽ����嵥", new Font(bf, 15, Font.BOLD));
			title.setAlignment(1);
			document.add(title);
			// ��Ŀ��λ������
			Table table = new Table(2);// ���Ų�����ʾ��
			int width[] = { 50, 50 };// ����ÿ�п�ȱ���
			table.setWidths(width);
			table.setWidth(100);// ռҳ���ȱ���
			table.setBorderWidth(0);
			// ��Ŀ��λ
			Paragraph company = new Paragraph("��Ŀ��λ��"+companyData, new Font(bf, 9));
			Cell cellcompany = new Cell(company);
			cellcompany.setHorizontalAlignment(Element.ALIGN_LEFT);// ˮƽ����
			cellcompany.setVerticalAlignment(Element.ALIGN_MIDDLE); // ��ֱ����
			cellcompany.setBorder(0);
			table.addCell(cellcompany);
			// ����
			Paragraph date = new Paragraph("�������ڣ�" + new SimpleDateFormat("yyyy��MM��dd��").format(new Date()),
					new Font(bf, 9));
			Cell celldate = new Cell(date);
			celldate.setHorizontalAlignment(Element.ALIGN_RIGHT);// ˮƽ����
			celldate.setVerticalAlignment(Element.ALIGN_MIDDLE); // ��ֱ����
			celldate.setBorder(0);
			table.addCell(celldate);
			table.setSpacing(10f);
			document.add(table);

			String[] headers={"��Ŀ","�������","Ӧ�ɷ���","�������","�ѽɷ���","��Ƿ����","��ע"};
			float widths[] = { 20, 10, 10, 10, 10, 10, 30 };
			PdfPTable table2 = PdfUtil.generateTable(headers, new Font(bf, 9, Font.BOLD), items, new Font(bf, 9), widths,
					95);
			document.add(table2);

			document.close();
		} catch (Exception e) {
			System.out.println("file create exception");
		}
	}

	public static String formatTosepara(int data) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(data);
	}
}
