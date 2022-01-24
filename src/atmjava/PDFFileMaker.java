package atmjava;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFFileMaker {
	static boolean PDFGenerate(String[][] data, int count, String username) {
		boolean status;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");  
		LocalDateTime now = LocalDateTime.now();
		
		String fileName ="TransactionHistory"+ username + dtf.format(now).toString();
		String filePathName = "C:\\ATMProject\\"+fileName+".pdf";
		
		try {
			Document doc = new Document();

			PdfWriter.getInstance(doc, new FileOutputStream(filePathName));
			doc.open();
			Paragraph p = new Paragraph("Transaction Info of: " + username+ " as of " + now);
			doc.add(p);
			
			p = new Paragraph("\n\n");
			doc.add(p);
			
			PdfPTable table = new PdfPTable(4);
			
			PdfPCell c1 = new PdfPCell(new Phrase("Transaction ID"));
			table.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Amount"));
			table.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Date Time Performed"));
			table.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Remarks"));
			table.addCell(c1);
			
			table.setHeaderRows(1);
			
			for(int i = 0; i < count; i++) {
				for(int j = 0; j < 4; j++) {
					table.addCell(data[i][j]);
				}
			}
			
			doc.add(table);
			doc.close();
			status = true;
			
		} catch(Exception e) {
			status = false;
		}
		return status;
	}
}
