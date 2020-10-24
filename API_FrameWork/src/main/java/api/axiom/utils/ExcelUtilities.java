package api.axiom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	public static XSSFWorkbook initiateWorkbook(String filepath) {
		XSSFWorkbook workbook = null;
		FileInputStream fis;
		try {
			File file = new File(filepath);
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	public static Sheet initiateSheet(XSSFWorkbook workbook, String sheetName) {
		Sheet sheet = null;
		try {
			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public static Row getExcelRow(Sheet sheet, int rowNum) {
		Row row = null;
		try {
			row = sheet.getRow(rowNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	public static String getCellData(Sheet sheet, int rowNum, int columnNum) {
		String cellData = null;
		Cell cell = sheet.getRow(rowNum).getCell(columnNum);
		try {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cellData = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				cellData = cell.getStringCellValue();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellData;
	}

	public static String getKeyValue(String file, String sheetName, String key) {
		List<String> list = new ArrayList<String>();
		Map<String, String> map = null;
		List<String> dataList = null;		
		try {
			Sheet sheet = initiateSheet(initiateWorkbook(file), sheetName);
			map = new LinkedHashMap<String, String>();
			System.out.println("going to get physical no of rows");
			for (int row = 0; row < sheet.getPhysicalNumberOfRows(); row++) {
				System.out.println(row);
				dataList = new LinkedList<String>();
				int columnCount = ExcelUtilities.getExcelRow(sheet, row).getPhysicalNumberOfCells();
				for (int column = 0; column < columnCount; column++) {
					if (ExcelUtilities.getCellData(sheet, row, column) != "")
						dataList.add(ExcelUtilities.getCellData(sheet, row, column));

				}
				map.put(dataList.get(0), dataList.get(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map.get(key);
	}

}
