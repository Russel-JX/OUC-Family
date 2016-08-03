package poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(UploadServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String excelPath = null;
		// response.setCharacterEncoding("utf-8");
		try {
			// 创建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			/*
			 * 1:Sets the directory used to temporarily store files that are
			 * larger than the configured size threshold. 2:需要自己创建temp这个目录
			 */
			// factory.setRepository(new File("C:\\Users\\mx801343\\temp"));
			// 创建 fileupload 组件
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding("utf-8");
			// 解析 request
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			PrintWriter writer = response.getWriter();
			// 遍历集合
			for (FileItem fileItem : fileItems) {
				// 判断是否为普通字段
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					writer.print("上传者： " + value + "<br>");
				} else {
					// 上传的文件路径
					String fileName = fileItem.getName();
					System.out.println("上传文件的路径" + fileName);
					writer.print("文件来源: " + fileName + "<br>");
					// 截取出文件名
					/*
					 * 1:lastIndexOf(String str).如果字符串参数str作为一个子字符串在此对象中出现一次或多次，
					 * 则返回最后一个这种子字符串的第一个字符的位置。如果它不作为一个子字符串出现，则返回 -1。[下标从0开始]
					 * 2:substring(int beginIndex) 返回一个新的字符串，它是此字符串的一个子字符串。
					 * 该子字符串从指定索引处的字符开始，直到此字符串末尾。3:转义字符 "\\"代表"\"
					 */
					fileName = fileName
							.substring(fileName.lastIndexOf("\\") + 1);
					writer.print("成功上传的文件: " + fileName + "<br>");
					// 文件名需要唯一
					fileName = UUID.randomUUID().toString() + "_" + fileName;
					// 在服务器创建同名文件
					String webPath = "/upload/" + fileName;
					String path = getServletContext().getRealPath(webPath);
					excelPath = path;
					System.out.println("path" + path);
					// 创建文件
					File file = new File(path);
					file.getParentFile().mkdirs();
					file.createNewFile();
					// 获得上传文件流
					InputStream in = fileItem.getInputStream();
					// 写入文件流
					OutputStream out = new FileOutputStream(file);
					// 流的对拷
					byte[] buffer = new byte[1024];
					int len;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					// 关流
					in.close();
					out.close();
					// 删除临时文件
					fileItem.delete();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		readExcel(excelPath);
		deleteFile(excelPath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void readExcel(String filePath) {
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

	public void deleteFile(String sPath) {
		File file = new File(sPath);
		if (file.isFile() && file.exists()) {
			file.delete();
			logger.info("删除成功");
			
		}
	}

}
