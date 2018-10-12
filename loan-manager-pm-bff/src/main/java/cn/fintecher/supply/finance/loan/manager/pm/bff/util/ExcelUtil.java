package cn.fintecher.supply.finance.loan.manager.pm.bff.util;

import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导出exl工具类
 * 
 * @author huanglei
 *
 */
public class ExcelUtil {

	public static XSSFWorkbook getXSSFWorkbook(String sheetName, String[] title, String[][] values, XSSFWorkbook wb) {
		// 第一步，创建一个webbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		XSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		XSSFCell cell = null;
		// 创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 创建内容
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < values[i].length; j++) {
				row.createCell(j).setCellValue(values[i][j]);
			}
		}

		return wb;
	}

	public static XSSFWorkbook getXSSFWorkbookOfRepair(String sheetName, String[] title, String[][] values,
			String[] isRed, XSSFWorkbook wb) {
		// 第一步，创建一个webbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		XSSFRow row = sheet.createRow(0);
		// 创建一个红色的字体样式
		XSSFFont fontRed = wb.createFont();
		fontRed.setColor(HSSFColor.RED.index);
		XSSFCellStyle styleFontRed = wb.createCellStyle();
		styleFontRed.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleFontRed.setFont(fontRed);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		XSSFFont font = wb.createFont();
		style.setFont(font);

		XSSFCell cell = null;
		// 创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i + 1);
			if ("1".equals(isRed[i])) {
				for (int j = 0; j < values[i].length; j++) {
					cell = row.createCell(j);
					cell.setCellValue(values[i][j]);
					cell.setCellStyle(styleFontRed);
				}
			} else {
				for (int j = 0; j < values[i].length; j++) {
					cell = row.createCell(j);
					cell.setCellValue(values[i][j]);
					cell.setCellStyle(style);
				}
			}

		}

		return wb;
	}

	public static XSSFWorkbook getXSSFWorkbookForReportYear(String sheetName, String title1str, String[] beforetitle2,
			String[] latertitle2, String[] latertitle3, String[][] values1, List<String[][]> list,String YearNumber, XSSFWorkbook wb) {
		// 第一步，创建一个webbook，对应一个Excel文件
		if (wb == null) {
			wb = new XSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		XSSFRow row = sheet.createRow(0);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		XSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		// 设置第一行标题
		XSSFCell title1 = sheet.createRow(0).createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 39));
		title1.setCellStyle(style);
		title1.setCellValue(title1str);

		// 设置第二行以及第三行标题前半段
		XSSFCell title2cell = null;
		XSSFRow rowtitle2 = sheet.createRow(1);
		XSSFCellStyle rowtitle2style = wb.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < beforetitle2.length; i++) {
			sheet.addMergedRegion(new CellRangeAddress(1, 2, i, i));
			title2cell = rowtitle2.createCell(i);
			title2cell.setCellValue(beforetitle2[i]);
			title2cell.setCellStyle(style);
		}

		// 设置第二行
		int number = 0;
		XSSFCell title3cell = null;
		for (int i = 0; i < latertitle2.length; i++) {
			number = number + 4;
			sheet.addMergedRegion(new CellRangeAddress(1, 1, number, number + 2));
			title3cell = rowtitle2.createCell(number);
			title3cell.setCellValue(latertitle2[i]);
			title3cell.setCellStyle(style);
			number--;
		}

		// 设置第三行标题后半段
		XSSFRow rowtitle3 = sheet.createRow(2);
		for (int i = 5; i < 41; i++) {
			title3cell = rowtitle3.createCell(i - 1);
			title3cell.setCellValue(latertitle3[i - 5]);
			title3cell.setCellStyle(style);
		}

		// 插入前半段数据
		for (int i = 0; i < values1.length; i++) {
			row = sheet.createRow(i + 3);
			for (int j = 0; j < values1[i].length; j++) {
				row.createCell(j).setCellValue(values1[i][j]);
			}
			String[][] values2 = list.get(i);
			for (int j = 0; j < values2.length; j++) {
				for (int h = 0; h < 3; h++) {
					if ((YearNumber+"-01").equals(values2[j][3])) {
						row.createCell(4 + h).setCellValue(values2[j][h]);
					} else if((YearNumber+"-02").equals(values2[j][3])){
						row.createCell(7 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-03").equals(values2[j][3])){
						row.createCell(10 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-04").equals(values2[j][3])){
						row.createCell(13 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-05").equals(values2[j][3])){
						row.createCell(16 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-06").equals(values2[j][3])){
						row.createCell(19 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-07").equals(values2[j][3])){
						row.createCell(22 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-08").equals(values2[j][3])){
						row.createCell(25 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-09").equals(values2[j][3])){
						row.createCell(28 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-10").equals(values2[j][3])){
						row.createCell(31 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-11").equals(values2[j][3])){
						row.createCell(34 + h).setCellValue(values2[j][h]);
					}else if((YearNumber+"-12").equals(values2[j][3])){
						row.createCell(37 + h).setCellValue(values2[j][h]);
					}
					
				}
			}
		}

		return wb;
	}
}