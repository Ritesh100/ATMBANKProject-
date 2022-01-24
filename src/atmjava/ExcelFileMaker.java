package atmjava;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelFileMaker {
	static boolean ExcelMaker(String[][] data, int count, String username) {
		boolean status;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");  
			LocalDateTime now = LocalDateTime.now();
			String fileName ="TransactionHistory"+ username + dtf.format(now).toString();
			String filePathName = "C:\\ATMProject\\"+fileName+".xls";
			
			HSSFWorkbook workbook = new HSSFWorkbook();  
			HSSFSheet sheet = workbook.createSheet("Transaction Record");  
			
			HSSFRow rowhead = sheet.createRow((short)0);  
			
			rowhead.createCell(0).setCellValue("Transaction ID");
			rowhead.createCell(2).setCellValue("Amount");  
			rowhead.createCell(3).setCellValue("Date Time Performed");  
			rowhead.createCell(4).setCellValue("Remarks");
			
			for(int i = 0; i < count; i++) {
				HSSFRow row = sheet.createRow((short)i+1);
				for(int j = 0; j < 4; j++) {
					row.createCell(j).setCellValue(data[i][j]);
				}
			}
		
			FileOutputStream fileOut = new FileOutputStream(filePathName);  
			workbook.write(fileOut);
			
			fileOut.close();  
			workbook.close();
			status = true;
			
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
}
