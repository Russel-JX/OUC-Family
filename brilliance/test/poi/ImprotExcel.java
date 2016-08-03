package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImprotExcel {

	private static final Log logger = LogFactory.getLog(ImprotExcel.class);

	public void ReadExcel(String filePath) {
		// 创建对Excel工作簿文件
		// HSSFWorkbook wookbook= new HSSFWorkbook(new
		// FileInputStream(filePath));
		// 在Excel文档中，第一张工作表的缺省索引是0，
		// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
		// HSSFSheet sheet = wookbook.getSheet("Sheet1");
		try {
			Workbook workbook = null;
			try {
				// SSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
				workbook = new XSSFWorkbook(new FileInputStream(filePath));
			} catch (Exception e) {
				// HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
				workbook = new HSSFWorkbook(new FileInputStream(filePath));
			}

			Sheet sheet = workbook.getSheetAt(0);
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			logger.info("PhysicalNumberOfRows" + rows);
			// int rows = sheet.getLastRowNum();
			// logger.info("LastRowNum" + rows);
			// 遍历行
			for (int i = 1; i < rows; i++) {
				// 读取左上端单元格
				Row row = sheet.getRow(i);
				if (row != null) {
					// 获取到Excel文件中的所有的列
					// int cells = row.getPhysicalNumberOfCells();
					// logger.info("cells" + cells);
					int cells = row.getLastCellNum();
					logger.info("LastCellNum" + cells);
					String value = "";
					for (int j = 0; j < cells; j++) {
						Cell cell = row.getCell(j);
						if (null == cell
								|| cell.getCellType() == Cell.CELL_TYPE_BLANK) {
							value += "" + ",";
						} else {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								// value += String.valueOf((int)cell
								// .getNumericCellValue()) + ",";
								value += (int) cell.getNumericCellValue() + ",";
								break;
							case Cell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + ",";
								break;
							// case Cell.CELL_TYPE_BLANK:
							// value += "" + ",";
							// break;
							case Cell.CELL_TYPE_FORMULA:
								break;
							default:
								break;
							}
						}
					}
					logger.info(value);
					String[] val = value.split(",");
					for (String str : val) {
						System.out.println(str);
					}

				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ImprotExcel improtExcel = new ImprotExcel();
		improtExcel.ReadExcel("E:\\货运公司数据样本.xlsx");
		
	}

}
