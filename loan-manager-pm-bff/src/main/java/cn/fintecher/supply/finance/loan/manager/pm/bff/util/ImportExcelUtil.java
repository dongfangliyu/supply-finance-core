package cn.fintecher.supply.finance.loan.manager.pm.bff.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;


public class ImportExcelUtil {
	 private final static String excel2003L =".xls";    //2003- 版本的excel  
	 private final static String excel2007U =".xlsx";   //2007+ 版本的excel
	 
	 /** 
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public static Map<String ,Object> getDataByExcel(InputStream in,String fileName) throws Exception{
    	Map<String ,Object> map = new HashMap<String ,Object>();
        List<List<Object>> list = null;  
          
        //创建Excel工作薄  
        Workbook work = getWorkbook(in,fileName);  
        if(null == work){  
            throw new Exception("创建Excel工作薄为空！");  
        }  
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  
          
        list = new ArrayList<List<Object>>();  
        //遍历Excel中所有的sheet  
        sheet = work.getSheetAt(0);//获取第一个sheet
        String sheetName = sheet.getSheetName();
        if(sheet==null){
        	throw new Exception("创建Excel工作薄为空！");
        }
        int countRow = sheet.getLastRowNum();
        if (countRow < 1){
        	throw new Exception("exl导入数据为空！");
        }
        //遍历当前sheet中的所有行
        for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {  
            row = sheet.getRow(j);  
            if(row==null){
            	throw new Exception("exl导入数据为空！");
            }  
            //遍历所有的列  
            List<Object> li = new ArrayList<Object>();  
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                cell = row.getCell(y);  
                li.add(getCellValue(cell));  
            }  
            list.add(li);  
        }  
        in.close();
        map.put("sheetName", sheetName);
        map.put("sheetData", list);
        return map;  
    }  
      
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    private static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
    	Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(excel2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  
  
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    private  static Object getCellValue(Cell cell){ 
    	if (cell == null){
    		return "";
    	}
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM");  //日期格式化  
        DecimalFormat df2 = new DecimalFormat("0");  //格式化数字  
          
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else if(178 == cell.getCellStyle().getDataFormat()){  //处理
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return !ChkUtil.isEmpty(value) ? value.toString().trim() : "";  
    }  
    
}
