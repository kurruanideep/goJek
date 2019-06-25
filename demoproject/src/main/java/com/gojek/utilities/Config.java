package com.gojek.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Config {

	public Config() {
		super();
	}

	@SuppressWarnings("resource")
	public static String getProperty(String RowName) {

		try {
			FileInputStream file = new FileInputStream(new File("Config.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().equalsIgnoreCase(RowName)) {
						Cell cellvalue = row.getCell(1);
						workbook.close();
						return cellvalue.getStringCellValue();
					}
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
