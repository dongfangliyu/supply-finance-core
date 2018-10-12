package cn.fintecher.supply.finance.loan.manager.pm.bff.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下载文件
 * @author whojinbao
 *
 */
public class DownLoadUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DownLoadUtil.class);





	public static void downLoadByInputStream(HttpServletResponse response, HttpServletRequest request, String fileName,InputStream inStream) throws FileNotFoundException{

		response.reset();
		response.setContentType("application/octet-stream;charset=ISO8859-1");  
		response.setContentType("bin");
		response.addHeader("Pargam", "no-cache"); 
		/* 
		 * 解决各浏览器的中文乱码问题 
		 */ 
		try {
			String userAgent = request.getHeader("User-Agent");  
			byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()  
					: fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题  
			fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码  
			response.setHeader("Content-disposition",  
					String.format("attachment; filename=\"%s\"", fileName));  
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			LOGGER.error("下载文件失败：{}", e);
		}
	}
	public static void downLoadByPath(HttpServletResponse response, HttpServletRequest request, String fileName, String downPath) throws FileNotFoundException{

		// 读到流中
		InputStream inStream = new FileInputStream(downPath);// 文件的存放路径
		
		DownLoadUtil.downLoadByInputStream(response,request,fileName,inStream);
	}
}
