package com.hybridFramework.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_reader {

	public static final Logger logger  = Logger.getLogger(Excel_reader.class.getName());
	
	@SuppressWarnings("deprecation")
	public String[][] getExcelData(String excelLocation, String sheetName) {
		
		try {
			logger.info("creating excel object Vamsi: ");
		//	logger.debug("debug message");
			String dataSets[][] = null;
		
		//create a file object as we want to connect to th excel sheet so that we can communicate with that
		FileInputStream file = new FileInputStream(new File(excelLocation));
		
		//create workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		// create an object for the sheet
		XSSFSheet sheet =  workbook.getSheet(sheetName);
		
		// get number of rows
		System.out.println(sheet.getLastRowNum());
		int totalRow = sheet.getLastRowNum()+1;
		System.out.println("Number of Rows in the excel :  "+totalRow);
		
		// get number of columns
		int totalColumn=sheet.getRow(0).getLastCellNum();
		System.out.println("Number of Columns in the excel :  "+totalColumn);
		
		//create array with size equal to number of rows and columns
		dataSets = new String[totalRow-1][totalColumn];		
		// Iterate through each row one by one 
		// iterator is a collection interface ( go through java collection framework video)
		Iterator<Row> rowIterator = sheet.iterator();
		
		int i=0;
		int t = 0;
		
		while(rowIterator.hasNext()) {
			
			Row row = rowIterator.next();
			if(i++ !=0) {
				
				int k= t;
				t++;
				// for each row iterate through all the columns
				// row iterator will have cell Iterator
				Iterator<Cell> cellIterator = row.cellIterator();
				
				int j =0;
					while(cellIterator.hasNext()) {
						// celliterator.next will point to the 1st cell of the row
						Cell cell = cellIterator.next();

						switch(cell.getCellType()) {
						
						case Cell.CELL_TYPE_NUMERIC:
							System.out.print(k+", ");
							System.out.print(j+", ");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getNumericCellValue());
							break;
							
						case Cell.CELL_TYPE_STRING:
							System.out.print(k+", ");
							System.out.print(j+", ");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
							
						case Cell.CELL_TYPE_BOOLEAN:
							System.out.print(k+", ");
							System.out.print(j+", ");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getBooleanCellValue());
							break;
							
						case Cell.CELL_TYPE_FORMULA:
							System.out.print(k+", ");
							System.out.print(j+", ");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						}
					}
					System.out.println("");
			}
			
		}
		
		file.close();
		return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String ExcelLocation = System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/data/TestData.xlsx";
		String sheetName = "LoginTestData";
		Excel_reader excel = new Excel_reader();
		excel.getExcelData(ExcelLocation, sheetName);
		
	}
	
}


