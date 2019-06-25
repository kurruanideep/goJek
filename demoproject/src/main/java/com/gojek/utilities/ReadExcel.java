package com.gojek.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel extends BaseClass {

	public ReadExcel() {
		super();
	}

	@SuppressWarnings({ "unused", "resource" })
	public static String data(String packagename, String rowname, String columnname) {

		try {
			FileInputStream file = new FileInputStream(new File(Config.getProperty("TestDataExcelPath:")));   
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(packagename);

			if (exe) {
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getStringCellValue().equals(rowname)) {
							Execution++;
							exe = false;
						}
					}
				}
			}
			Iterator<Row> rowIterator = sheet.iterator();
			int column = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().equals(columnname)) {
						column = cell.getColumnIndex();
					}
					Cell cell1 = row.getCell(column);
					if (cell.getStringCellValue().equals(rowname)) {
						for (int i = 1; i < trip; i++)
							row = rowIterator.next();
						cell1 = row.getCell(column);
						if (cell1.getStringCellValue().contains("RandomString")) {
							String dummy = cell1.getStringCellValue();
							int num = Integer.parseInt(dummy.substring(13, 14));
						} else if (cell1.getStringCellValue().contains("RandomNumber")) {  
							String dummy = cell1.getStringCellValue();
							int num = Integer.parseInt(dummy.substring(13, 14));
						} else
							return cell1.getStringCellValue();
					}
				}
			}
			file.reset();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
