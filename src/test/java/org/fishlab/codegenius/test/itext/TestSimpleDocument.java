package org.fishlab.codegenius.test.itext;
/*
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
*/

/**
	需要添加依赖
	<!--文档支持-->
		<dependency>
		  <groupId>com.lowagie</groupId>
		  <artifactId>iText</artifactId>
		  <version>2.1.7</version>
		</dependency>
		<dependency>
		  <groupId>com.lowagie</groupId>
		  <artifactId>itext-rtf</artifactId>
		  <version>2.1.7</version>
		</dependency>
		
		<dependency>
		  <groupId>com.lowagie</groupId>
		  <artifactId>iTextAsian</artifactId>
		  <version>2.1.7</version>
		</dependency>

 */
public class TestSimpleDocument {
/*
	@Test
	public void doc() throws Exception {

		// 创建word文档,并设置纸张的大小
		Document document = new Document(PageSize.A4);
		try {
			RtfWriter2.getInstance(document, new FileOutputStream(
					"C:\\Users\\Administrator\\Desktop\\word.doc"));
			document.open();

			// 设置合同头
			Paragraph ph = new Paragraph();
//			Font f = new Font();
//			ph.setFont(f);

			Paragraph p = new Paragraph("出口合同2", new Font(Font.NORMAL, 18,
					Font.BOLDITALIC, new Color(0, 0, 0)));
			p.setAlignment(1);
			document.add(p);

			// 设置中文字体
			BaseFont bfFont = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			 Font chinaFont = new Font(bfFont);
			 ph.setFont(chinaFont);
			
			
			// 创建有三列的表格
			 
			Table table = new Table(4);
			document.add(new Paragraph("生成表格2"));
			table.setBorderWidth(1);
			table.setBorderColor(Color.BLACK);
			table.setPadding(0);
			table.setSpacing(0);

			
			// 添加表头的元素
			 
			Cell cell = new Cell("表头");// 单元格
			cell.setHeader(true);
			cell.setColspan(3);// 设置表格为三列
			cell.setRowspan(3);// 设置表格为三行
			table.addCell(cell);
			table.endHeaders();// 表头结束

			// 表格的主体
			cell = new Cell("Example cell 2");
			cell.setRowspan(2);// 当前单元格占两行,纵向跨度
			table.addCell(cell);
			table.addCell("1,1");
			table.addCell("1,2");
			table.addCell("1,3");
			table.addCell("1,4");
			table.addCell("1,5");
			table.addCell(new Paragraph("用java生成的表格1"));
			table.addCell(new Paragraph("用java生成的表格2"));
			table.addCell(new Paragraph("用java生成的表格3"));
			table.addCell(new Paragraph("用java生成的表格4"));
			document.add(new Paragraph("用java生成word文件"));
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
}
